package dev.shiji.bishops.cs321.hw3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A Phase simulates the Events in the Waiting-line Model
 * <p>
 * Each member class only has to take care of the serving process and the duration.
 */
public abstract class Phase {

    protected final int averageServingTime;
    protected int serverClock;
    protected int serverClockStarts;
    protected int totalWaitingTime;
    protected int customerServed;
    private Queue<Customer> queue;          // in Queue
    private Collection<Customer> out;       // Out (Departure Zone)


    public Phase(int averageServingTime) {
        this.averageServingTime = averageServingTime;
        reset();
    }

    /**
     * Reset the stat
     */
    protected void reset() {
        serverClock = 0;
        serverClockStarts = -1;
        queue = new PriorityQueue<>();
        out = new ArrayList<>();
        totalWaitingTime = 0;
        customerServed = 0;
    }

    /**
     * Random Expo number generator
     *
     * @param average mean
     *
     * @return random value
     */
    public static int getRandomExp(int average) {
        return (int) (Math.log(1 - Math.random()) * -average);
    }

    /**
     * Add all customers to the queue
     *
     * @param customers a collection of customers
     */
    public void arriveAll(Collection<Customer> customers) {
        for (Customer c : customers) {
            arrive(c);
        }
    }

    /**
     * Add a single customer to the queue
     *
     * @param customer customer
     */
    public void arrive(Customer customer) {
        queue.add(customer);
    }

    /**
     * Process all customers for the current phase and handle the time
     */
    public void serveAll() {

        while (!queue.isEmpty()) {
            Customer c = queue.peek();

            // Record the time of serving the first customer
            if (serverClockStarts < 0) {
                serverClockStarts = c.time;
            }

            // If customer arrived when the server is idle
            // Then move the server clock to arrival time
            if (c.time > serverClock) {
                serverClock = c.time;
            }

            // The While loop is for the ATM process.
            // As Prof DV explained, if a customer doesnt have at least $20 after
            // the first round of the ATM, he will use the ATM again.
            // If the amount of money < 20 after the first round of ATM, go again.
            while (!isFinished(c)) {

                // Waiting time  = current serving time - in queue time
                // note: in queue time = departure time from last phase
                int waitingTime = serverClock - c.time;
                c.waitingTime += waitingTime;
                totalWaitingTime += waitingTime;

                // Serve the customer, and update the server time
                serverClock += getServiceTime(c);
                serve(c);
                customerServed++;

                // Set(Sync) customer's current time (departure time)
                c.time = serverClock;
            }

            // remove from the queue and move to departure zone
            out.add(c);
            queue.remove();

        }
        System.out.println("Server Finished @ " + serverClock);
        System.out.println("AVG Waiting time:  " + getAvgWaitingTime());
        System.out.println("AVG Queue Length:  " + getAvgQueueLength() + "\n");
    }

    /**
     * The condition indicating the customer is served and ready to move on
     *
     * @param customer customer
     *
     * @return true if the customer has finished the process / can skip the process
     */
    public boolean isFinished(Customer customer) {
        return true;
    }

    /**
     * return the value of the processing time
     *
     * @param customer customer
     *
     * @return processing time
     */
    public abstract int getServiceTime(Customer customer);

    /**
     * Define the process of the phase
     *
     * @param customer customer
     */
    public abstract void serve(Customer customer);

    /**
     * Helper Function for average waiting time
     *
     * @return average waiting time
     */
    public double getAvgWaitingTime() {
        return totalWaitingTime * 1.0 / customerServed;
    }

    /**
     * Helper function for average weighted queue length
     *
     * @return average weighted queue length
     */
    public double getAvgQueueLength() {
        return totalWaitingTime * 1.0 / (serverClock - serverClockStarts);
    }

    /**
     * Return the Collection of all customers who are done with the current phase It can
     * only be called once!
     *
     * @return the output
     */
    public Collection<Customer> getFinishedCustomers() {
        Collection<Customer> c = new ArrayList<>(out);
        reset();
        return c;
    }
}
