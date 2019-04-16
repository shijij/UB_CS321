import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[][] arr = {{1,2,3,4,5},{4,5,6,7,8},{9,99,999},{0,5,55,555,95}};

        sum(arr);

        /**
         * The value is not always 50, because if thread A copy the value
         * while another thread is executing, one more more of the "adding" are omitted
         */
        potOfGold();
    }

    public static void sum(int[][] arr) throws InterruptedException {
        Set<RowAdder> ra = new HashSet<>();
        Set<Thread> thread = new HashSet<>();


        int x = arr.length;
        if(x == 0){
            throw new IllegalArgumentException();
        }


        long result = 0;
        for(int[] subArr : arr){
            RowAdder adder = new RowAdder(subArr);
            Thread t = new Thread(adder);
            t.start();
            ra.add(adder);
            thread.add(t);
        }

        for(Thread t : thread){
            t.join();
        }

        for(RowAdder adder : ra){
            System.out.println(result);
            result += adder.getResult();

        }
        System.out.println(result);
    }


    public static void potOfGold() throws InterruptedException {
        Set<Thread> threads = new HashSet<>();


        for(int i = 0; i < 50; i ++){
            Thread t = new Thread(new GoldWorker());
            threads.add(t);
            t.start();
        }

        for(Thread t : threads){
            t.join();
        }
        System.out.println();
        System.out.println(GoldWorker.potOfGold);
    }
}
