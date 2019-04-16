import java.util.Scanner;

/**
 * Main class if HW1
 *
 * It will generate a kingdom with parameters by user inputs and simulate the moving
 * process
 *
 * @author Shiji Jiang
 */
public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("The size of a kingdom is defined by NxN, what's N?: ");
        int n = reader.nextInt();
        System.out.println("What's the percentage of satisfaction [0-100]: ");
        int satisfaction = reader.nextInt();
        System.out.println("What's the percentage of X-type citizen? [0-100]");
        int x = reader.nextInt();
        System.out.println("What's the percentage of O-type citizen? [0-100]");
        int o = reader.nextInt();

        Kingdom k = new Kingdom(n, satisfaction);
        k.seed(x, o);
        System.out.println("\n Initializing...");
        System.out.println(k);
        k.simulation();

    }
}
