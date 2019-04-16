import java.util.Comparator;

/**
 * Comparator, by year of the movie
 */
public class YearComparator implements Comparator {
    public int compare(Object a, Object b){
        Movie x = (Movie) a;
        Movie y = (Movie) b;
        return x.year - y.year;
    }
}
