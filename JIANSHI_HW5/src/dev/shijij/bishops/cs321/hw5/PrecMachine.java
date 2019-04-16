package dev.shijij.bishops.cs321.hw5;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The PrecMachine is a directed graph that Simulate doing tasks with prerequisites based
 * on semaphores
 * <p>
 * As defined, nodes are tasks, and the edges are prerequisites.
 * <p>
 * Built and tested on JDK 11
 *
 * @author Shiji Jiang
 */
public class PrecMachine {

    // Key: Node; Value: Set of next destinations (next nodes pointing to)
    HashMap<Integer, HashSet<Integer>> next;

    // Key: Node; Value: Set of origins (prev nodes pointing from)
    HashMap<Integer, HashSet<Integer>> prev;

    // Semaphores for each edge, noted as point(m,n)
    HashMap<Point, Semaphore> sems;

    Runnable[] tasks;

    /**
     * Construct a new PrecMachine
     *
     * @param precs 2D array of nodes and directed edges
     * @param jobs  Array of Tasks as Runnables
     */
    public PrecMachine(boolean[][] precs, Runnable[] jobs) {
        // Validate input for the first round
        if (precs.length == 0
                || precs.length != precs[0].length
                || jobs.length != precs.length
        ) {
            throw new IllegalArgumentException("Invalid Matrix and/or jobs!");
        }

        // AtomicInteger is used for Auto Increment task ID in Lambda Expression
        AtomicInteger index = new AtomicInteger(0);
        Arrays.stream(jobs).forEach((t) -> ((Task) t).id = index.getAndIncrement());

        // Initialize HashMaps
        next = new HashMap<>();
        prev = new HashMap<>();
        sems = new HashMap<>();
        tasks = jobs;

        // Build the HashMap,
        // next(i) is a set of edges pointing away from  i
        // prev(i) is a set of edges pointing to node i
        for (int i = 0; i < precs.length; i++) {
            // initialize set for each key
            next.put(i, new HashSet<>());

            for (int j = 0; j < precs.length; j++) {
                // initialize set for each key
                if (!prev.containsKey(j)) {
                    prev.put(j, new HashSet<>());
                }

                if (precs[i][j]) {
                    // Check for self-loop
                    if (i == j) {
                        throw new IllegalArgumentException("Loop Detected!");
                    }

                    // Update prev and next
                    prev.get(j).add(i);
                    next.get(i).add(j);

                    // Create new Semaphore
                    sems.put(new Point(i, j), new Semaphore(0));
                }
            }
        }

        System.out.println("Next: "+ next);
        System.out.println("Prev: "+ prev);


        // Check for cycles for each starting nodes (nodes with no prerequisite)
        prev.entrySet()
                .stream()
                .filter(k -> k.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .forEach(this::hasCycle);


        // List All Tasks
        System.out.println("Here's the List of Tasks");
        Arrays.stream(tasks).forEach((c) -> System.out.println(((Task) c).toString()));

    }

    /**
     * Run the precMachine.
     * <p>
     * Basic Idea using lambda expression: 1. For each task generate a thread, which
     * includes a. wait 'prev' semaphores b. sleep and execute c. release 'next'
     * semaphores 2. Start all of those threads all at once
     */
    public void run() {
        System.out.println("\nMaching Running...");

        // Generate Thread for each tasks
        Arrays.stream(tasks).map(t -> new Thread(() -> {
            // Get Task ID
            int i = ((Task) t).id;

            System.out.println("[T" + i + "]\t Initialized");

            // Wait for required semaphores
            prev.get(i).forEach(p -> {
                try {
                    sems.get(new Point(p, i)).acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("[T" + i + "]\t Running..");

            // Random Sleep
            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Use run because there's no need to create a child thread for
            // each thread just for showing a msg in console
            t.run();

            // Release/Signal Next semaphores
            next.get(i).forEach(p -> sems.get(new Point(i, p)).release());

            System.out.println("[T" + i + "]\t Finished");

        })).forEach(Thread::start); // And start those threads
    }

    /**
     * Check for cycle from the starting loop
     *
     * @param a starting node
     *
     * @return false
     *
     * @throws IllegalArgumentException when cycle found
     */
    public boolean hasCycle(Integer a) {
        System.out.println("\nRunning Circle Check..");
        if (hasCycleHelper(a, new HashSet<>())) {
            throw new IllegalArgumentException("Cycle Detected!");
        }
        System.out.println("Circle Check Passed\n");
        return false;
    }

    /**
     * Cycle Check helper with DFS
     *
     * @param a       Starting Node
     * @param visited Set for all visited nodes
     *
     * @return true if cycle exist
     */
    private boolean hasCycleHelper(Integer a, HashSet<Integer> visited) {
        // If current node within visited node, cycle detected
        if (visited.contains(a)) {
            System.out.println("[Err]\tCycle Detected for path involving " + visited);
            return true;
        }
        // Add current node to visited
        visited.add(a);

        // next is empty indicates this is the last node of a path
        if (next.get(a).isEmpty()) {
            System.out.println("[OK]\tNo Cycle for path involving " + visited);
        }

        // The check child nodes
        for (Integer i : next.get(a)) {
            // If loop found in any of the child nodes, exit early
            if (hasCycleHelper(i, visited)) {
                return true;
            }
        }
        // remove current node from visited, after all child nodes checked.
        visited.remove(a);

        return false;
    }

    /**
     * A runnable Task with a simple description The ID is assigned by the lambda
     * expression in PrecMachine.
     *
     * @see PrecMachine
     */
    public static class Task implements Runnable {
        String task;
        Integer id;

        // Create a new task by definition
        Task(String desc) {
            task = desc;
            id = null;
        }

        @Override
        public void run() {
            System.out.println(toString());
        }

        @Override
        public String toString() {
            return "Task " + id + ": " + task;
        }
    }


    /**
     * Main Class for Testing
     *
     * @param args input arguments
     */
    public static void main(String[] args) {
        boolean[][] precs = {
                {false, true, false, false, false, false},
                {false, false, true, true, true, false},
                {false, false, false, false, false, false},
                {false, false, false, false, true, true},
                {false, false, false, false, false, false},
                {false, false, false, false, true, false}
        };
        Runnable[] jobs = {
                new Task("A"),
                new Task("B"),
                new Task("C"),
                new Task("D"),
                new Task("E"),
                new Task("F")
        };

        PrecMachine test = new PrecMachine(precs, jobs);
        test.run();
    }
}
