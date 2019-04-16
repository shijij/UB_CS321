import java.util.Comparator;

public class SheepIDSorter implements Comparator<Sheep> {

    @Override
    public int compare(Sheep o1, Sheep o2) {
        return o1.id - o2.id;
    }
}
