import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Shiji Jiang
 */
public class Main {
    public static final String GREEN = "\u001B[34m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        // Part 1 - B
        showSection("1 - B");
        ArrayList<Movie> theatre = new ArrayList<Movie>();
        theatre.add(new Movie("Fargo", 8.1, 1996));
        theatre.add(new Movie("The Shawshank Redemption", 9.3, 1993));
        theatre.add(new Movie("Interstellar", 8.6, 2014));
        theatre.add(new Movie("Forest Gump", 8.8, 1994));

        // Part 1 - B - 1
        showSection("1 - B - 1");
        printTheatre(theatre);

        // Part 1 - B - 2
        showSection("1 - B - 2");
        Movie m = new Movie("Fargo", 8.1, 1996);
        if (theatre.contains(m)) {
            System.out.println("ArrayList contains it");
        } else {
            System.out.println("ArrayList doesn't contain it");
        }

        // Part 1 - B - 3
        showSection("1 - B - 3");
        Integer index = 0;
        for (Movie a : theatre) {
            if (a.name == "Interstellar") {
                System.out.println("Found! index is " + index);
                break;
            }
            index++;
        }

        // Part 1 - B - 4
        showSection("1 - B - 4");
        for (Movie a : theatre) {
            a.rating /= 2.0;
        }

        // Part 1 - C
        showSection("1 - C");
        Collections.sort(theatre, new YearComparator());
        printTheatre(theatre);

        Collections.sort(theatre, new RatingComparator());
        printTheatre(theatre);


        // Part 2
        showSection("2");
        Faculty  DrX = new Faculty (34, 10);
        Lecturer MrY = new Lecturer (27, 4);
        Grad     Joe = new Grad (23, 2);

        Instructor [] University = { new Faculty (34, 10),
                new Lecturer (27, 4),
                new Grad (23, 2) };

        for (int k = 0; k<10;k++){
            System.out.println(  "\n===========  Day "
                    + k
                    + "  ============-\n");
            int mail = k*10 + 50;

            for ( Instructor aTeacher : University){
                aTeacher.getMail(mail);
            }
        }



    }

    /**
     * Print Current List of Theatre
     * @param theatre
     */
    private static void printTheatre(ArrayList<Movie> theatre){
        for (Movie a : theatre) {
            System.out.println(a);
        }
    }

    /**
     * Devide System output into sections
     *
     * @param s section name
     */
    public static void showSection(String s) {
        System.out.println(GREEN + "\n### ### ### ### ###" + RESET);
        System.out.println(GREEN + "###  " + s + "  ###" + RESET);
        System.out.println(GREEN + "### ### ### ### ### \n" + RESET);
    }
}