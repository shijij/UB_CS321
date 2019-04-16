package dev.shiji.bishops.cs321.hw3;

import java.util.Collection;


/**
 * Representing a Customer (Client) in the Waiting-line Model
 *
 * @author Shiji Jiang
 */
public class Customer implements Comparable<Customer> {
    // Constants for generating random values
    public static final int MONEY_MAX = 40;
    public static final int MONEY_MIN = 0;
    public static final int ARRIVAL_MEAN = 120;

    private static int auto_increment;          // Auto generate Customer ID

    // Initialize Static Variable
    static {
        auto_increment = 0;
    }

    protected final int id;                     // Customer ID
    protected final int arrivalTime;            // Customer Arrival Time
    protected int waitingTime;                  // Total Waiting Time
    protected int time;                         // Customer time
    protected int money;                        // Money
    protected int fishBought;                   // Fish Bought

    /**
     * Returns a default Customer object with random arrival time and money
     */
    public Customer() {
        this(getRandomArrivalTime(ARRIVAL_MEAN), getRandomMoney());
    }

    /**
     * Returns a Customer with custom arrival time and money
     *
     * @param arrival_time arrival time
     * @param money        money
     */
    public Customer(int arrival_time, int money) {
        this.arrivalTime = arrival_time;
        this.money = money;
        this.id = auto_increment++;
        this.time = this.arrivalTime;
        this.waitingTime = 0;
        this.fishBought = 0;
    }

    public static int getRandomArrivalTime(int average) {
        return (int) (Math.log(1 - Math.random()) * -average);
    }

    /**
     * Get a random money value in the range
     *
     * @return random money value in the range
     */
    public static int getRandomMoney() {
        return (int) (Math.random() * (MONEY_MAX - MONEY_MIN + 1)) + MONEY_MIN;
    }

    /**
     * Returns a Customer with custom arrival time and random money
     *
     * @param arrival_time arrivale time
     */
    public Customer(int arrival_time) {
        this(arrival_time, getRandomMoney());
    }

    /**
     * Returns the average time spent for a Collection of Customers
     *
     * @param customers customers
     *
     * @return average time spent
     */
    public static double getAvgTimeSpent(Collection<Customer> customers) {
        if (customers.isEmpty()) {
            return 0;
        }

        double result = 0;
        for (Customer c : customers) {
            result += (c.time - c.arrivalTime);
        }
        return result / customers.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Customer) {
            Customer c = (Customer) o;
            return c.id == this.id;
        }
        return false;
    }

    /**
     * Return a Srting representing the current state of the Customer
     *
     * @return
     */
    @Override
    public String toString() {
        return "Customer #" + id + "   \t[$" + money + "] Arrival@ " + arrivalTime
                + " \tCurTime: " + time + "\t WaitTime: " + waitingTime + " \tTime Spent: " +
                (time - arrivalTime) + " \tFish Bought: " + fishBought + "\n";
    }

    /**
     * First come first served. If coming at the same time, the one with less money first
     * to optimise the waiting time
     *
     * @param o
     *
     * @return
     */
    @Override
    public int compareTo(Customer o) {
        if (this.time == o.time) {
            return this.money - o.money;
        }
        return this.time - o.time;
    }
}
