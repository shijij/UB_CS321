package dev.shiji.bishops.cs321.hw3;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * The runnable main class basically for testing
 *
 * @author Shiji Jiang
 */
public class Main {
    public static void main(String[] args) {
        // Demo for grader: default arrival time and processing time
        FishMarket f1 = new FishMarket();

        // Demo for grader: custom arrival time and default processing time
        FishMarket f2 = new FishMarket(120);

        // Demo for grader: custom arrival time and custom processing time
        // [interArrivalmean, atm_mean, shipping_mean, checkout_mean]
        FishMarket f3 = new FishMarket(120, 30, 120, 30);

        // Run the simulation with 100 customers
        //f1.run();

        // Run with custom # of customers
        f3.run(200);

        // Run with custom collection of customers
        //f3.run(case2());
    }


    // Expected all zeros
    private static Collection<Customer> case1() {
        Collection<Customer> customers = new PriorityQueue<>();
        customers.add(new Customer(0, 0));
        customers.add(new Customer(10000, 0));
        customers.add(new Customer(20000, 0));
        customers.add(new Customer(30000, 0));
        return customers;
    }


    private static Collection<Customer> case2() {
        Collection<Customer> customers = new PriorityQueue<>();
        customers.add(new Customer(0, 10));
        customers.add(new Customer(0, 10));
        customers.add(new Customer(0, 10));
        customers.add(new Customer(0, 10));
        return customers;
    }

}
