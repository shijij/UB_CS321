import java.util.Comparator;

/**
 * Comparator, by rating of the movie
 */
public class RatingComparator implements Comparator {
    public int compare(Object a, Object b){
        Movie x = (Movie) a;
        Movie y = (Movie) b;
        return Double.compare(x.rating, y.rating);
    }
}
