import java.util.Comparator;

/**
 * A manhattan distance sorter, provided the current Citizen
 */
public class SortByDistance  implements Comparator<Cell> {

    private int x;
    private int y;

    /**
     * Constructor, requires a target CitizenCell for distance measurement
     * @param currentCitizen Target Citizen for distance measurement
     */
    public SortByDistance(CitizenCell currentCitizen){
        x = currentCitizen.getX();
        y = currentCitizen.getY();
    }

    @Override
    public int compare(Cell o1, Cell o2) {
        return Math.abs(o1.getX() - x) + Math.abs(o1.getY() - y) -
                Math.abs(o2.getX() - x) - Math.abs(o2.getY() - y);
    }
}
