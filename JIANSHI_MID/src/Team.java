import java.util.*;
/**
 * 
 * @author sjiang18
 *
 */
public class Team<P extends Player> {
	private String name;
	private ArrayList<P> members;
	public final static int MAX_MEMBER = 17;
	
	public Team(String name){
		this.name = name;
		members = new ArrayList<>(5);
	}
	
	public Team(){
		this("noname");
	}
	
	
	public void insert(P player){
		// not thread safe
		if(members.size() < 17){
			members.add(player);
		}
	}
	
	public Iterator<P> iterator(){
		return members.iterator();
	}
	
	public boolean contains(P player){
		return members.contains(player);
	}
	public void move(){
		Iterator<P> i = iterator();
		while(i.hasNext()){
			i.next().move();
		}
	}
	
	public String toString(){
		return "Team: "+name+"\n"+members.toString();
	}
	
	public static <P extends Player> Collection<P> intersection(Team<P> team1, Team<P> team2){
		Collection<P> result = new ArrayList<>();
		for(P player1 : team1.members){
			for(P player2 : team2.members){
				if(player1.equals(player2)){
					result.add(player1);
				}
			}
		}
		return result;
	}
	
	public void sort(Comparator<P> c){
		members.sort(c);
	}
		
	
}
