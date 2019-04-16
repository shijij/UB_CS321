package dev.shiji.bishops.cs321.hw3;

/**
 * Simulates the Checkout phase of the market
 */
public class Checkout extends Phase {

    /**
     * Checkout with custom average time
     *
     * @param averageServingTime average time
     */
    public Checkout(int averageServingTime) {
        super(averageServingTime);
    }

    /**
     * Checkout with default time
     */
    public Checkout() {
        super(-1);
    }

    @Override
    public boolean isFinished(Customer customer) {
        return customer.money < 10;
    }

    @Override
    public int getServiceTime(Customer customer) {
        int avgTime = averageServingTime > 0 ?
                averageServingTime :
                (customer.money / 10 * averageServingTime);
        return getRandomExp(avgTime);
    }

    @Override
    public void serve(Customer customer) {
        customer.money %= 10;
    }
}
