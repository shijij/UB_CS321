public class GoldWorker implements Runnable {
    public static int potOfGold;
    public int myPotOfGold;

    static {
        potOfGold = 0;
    }

    public GoldWorker(){
        myPotOfGold = 0;
    }


    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myPotOfGold = potOfGold;
        myPotOfGold ++;
        potOfGold = myPotOfGold;
    }
}
