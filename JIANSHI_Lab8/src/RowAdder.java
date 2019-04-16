public class RowAdder implements Runnable {
    private int[] arr;
    private long sum;

    public RowAdder(int[] arr){
        sum = 0;
        this.arr = arr;
    }
    @Override
    public void run() {
        System.out.println("Thread Running...");
        try {
            Thread.sleep((int) (Math.random()*500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int a : arr){
            sum += a;
        }
    }

    public long getResult(){
        return sum;
    }
}
