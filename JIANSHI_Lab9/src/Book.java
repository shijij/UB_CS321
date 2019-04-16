import java.util.Arrays;

public class Book {
    protected String name;
    protected double price;

    public Book(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Name: "+name + " Price: " + price;
    }


    public static void main(String[] args){
        Book[] books = {
                new Book("D", 3.1),
                new Book("B", 1.8),
                new Book("C", 7.2),
                new Book("A", 10.6),
                new Book("E", 5.3)
        };

        // Name ASC Method 1
        Arrays.sort(books, (a,b) -> a.name.compareTo(b.name));
        System.out.println(Arrays.toString(books));

        // Price ASC
        Arrays.sort(books, (a,b) -> (int) (a.price - b.price));
        System.out.println(Arrays.toString(books));

        Hello[] hellos = {
                new Hello("AA"),
                new Hello("BB"),
                new Hello("CC"),
                new Hello("DD")};

        Arrays.stream(hellos).forEach(b -> (new Thread(b)).start());

    }


}
