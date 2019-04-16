package dev.shiji.bishops.cs321.hw3;

/**
 * Simulates the Shopping phase of the market
 */
public class Shopping extends Phase {

    /**
     * Shopping with custom average processing time
     *
     * @param averageServingTime average processing time
     */
    public Shopping(int averageServingTime) {
        super(averageServingTime);
    }

    /**
     * Shopping with default average time
     */
    public Shopping() {
        super(-1);
    }

    /**
     * Finishing State: fish bought > 0
     *
     * @param customer customer
     *
     * @return
     */
    @Override
    public boolean isFinished(Customer customer) {
        return customer.fishBought > 0;
    }

    /**
     * Average time: # of fish x 2 mins (120s)
     *
     * @param customer customer
     *
     * @return
     */
    @Override
    public int getServiceTime(Customer customer) {
        int avgTime = averageServingTime > 0 ?
                averageServingTime :
                (customer.money / 10 * averageServingTime);
        return getRandomExp(avgTime);
    }

    /**
     * Customer got fishes in this phase
     *
     * @param customer customer
     */
    @Override
    public void serve(Customer customer) {
        customer.fishBought += customer.money / 10;
    }
}
