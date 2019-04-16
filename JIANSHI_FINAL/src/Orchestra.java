
import java.util.*;

/**
 * Some of my comments,
 * If HashSet is used, then the order won't be guaranteed.
 * 
 * @author sjiang18
 *
 */
public class Orchestra {
	List<Musician> musicians;
	Set<String> names;
	
	Orchestra(){
		musicians = new ArrayList<>();
		names = new HashSet<>();
	}
	
	void add(Musician m){
		if(! names.contains(m.name)){
			musicians.add(m);
			names.add(m.name);
		}
	}
	
	void play(){
		musicians.forEach(m-> m.play());
	}
	
	void tuneUp(){
		musicians.forEach(m -> m.music = m.music.toUpperCase());
	}
	
	public List<Musician> choose(Predicate<Musician> p){
		List<Musician> results = new ArrayList<>();
		for(Musician m : musicians){
			if(p.test(m)){
				results.add(m);
			}
		}
		return results;
	}
}
