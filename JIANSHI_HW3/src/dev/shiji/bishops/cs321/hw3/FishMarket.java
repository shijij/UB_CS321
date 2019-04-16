package dev.shiji.bishops.cs321.hw3;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

public class FishMarket {
    private Phase[] phases;
    private int interArrival;

    /**
     * Create a FishMarket with default mean arrival time and processing time
     */
    public FishMarket() {
        phases = new Phase[]{
                new ATM(),
                new Shopping(),
                new Checkout()
        };
        interArrival = Customer.ARRIVAL_MEAN;
    }

    /**
     * Create a FishMarket with custom interArrival mean
     *
     * @param interArrival inter arrival time
     */
    public FishMarket(int interArrival) {
        phases = new Phase[]{
                new ATM(),
                new Shopping(),
                new Checkout()
        };
        this.interArrival = interArrival;
    }

    /**
     * Create a Fishmarket with custom arrival time and custom average processing time
     *
     * @param interArrival mean InterArrival
     * @param atm          atm average time
     * @param shopping     shopping average time
     * @param checkout     checkout averae time
     */
    public FishMarket(int interArrival, int atm, int shopping, int checkout) {
        phases = new Phase[]{
                new ATM(atm),
                new Shopping(shopping),
                new Checkout(checkout)
        };
        this.interArrival = interArrival;
    }

    /**
     * Run the simulation with 100 customers
     */
    public void run() {
        run(seed(100));
    }

    /**
     * Run the simulation is a custom collection of customers And show stat info
     *
     * @param customers
     */
    public void run(Collection<Customer> customers) {

        System.out.println("\n\nInit State:");
        System.out.println(customers);

        for (int i = 0; i < phases.length; i++) {

            phases[i].arriveAll(customers);
            phases[i].serveAll();
            customers = phases[i].getFinishedCustomers();
            // Uncomment the next line for detailed info
            //System.out.println(customers);

        }
        System.out.println(customers);
        System.out.println("Average Time Spent per Customer: "
                + Customer.getAvgTimeSpent(customers));

    }

    /**
     * Generate a Random Collection of Customers
     *
     * @return a Random Collection of Customers
     */
    public Queue<Customer> seed(int number) {
        Queue<Customer> c = new PriorityQueue<>();
        for (int i = 0; i < number; i++) {
            c.add(new Customer(Customer.getRandomArrivalTime(interArrival)));
        }
        return c;
    }

    /**
     * Run the simulation with custom number of customers
     *
     * @param numberCustomers number of customers
     */
    public void run(int numberCustomers) {
        run(seed(numberCustomers));
    }


}
