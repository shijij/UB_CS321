public class Hello implements Runnable {
    String name;
    public Hello(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= 4) {
            System.out.println("Hello I am " + name + " " + i);
            i++;
        }
    }
}
