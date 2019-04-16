package dev.shiji.bishops.cs321.hw3;


/**
 * Simulates the ATM phase of the market
 */
public class ATM extends Phase {

    /**
     * ATM with custom mean
     *
     * @param averageServingTime
     */
    public ATM(int averageServingTime) {
        super(averageServingTime);
    }

    /**
     * ATM with default mean
     */
    public ATM() {
        super(30);
    }

    @Override
    public void serve(Customer customer) {
        customer.money += (Math.random() * (30 + 1)) + 10;
    }

    @Override
    public boolean isFinished(Customer customer) {
        return customer.money >= 20;
    }

    @Override
    public int getServiceTime(Customer customer) {
        return getRandomExp(averageServingTime);
    }
}
