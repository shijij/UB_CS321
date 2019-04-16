import java.util.Comparator;

public class CompareByPos<T extends Player> implements Comparator<T> {

	@Override
	public int compare(T arg0, T arg1) {
		return arg0.pos - arg1.pos;
	}
	
}
