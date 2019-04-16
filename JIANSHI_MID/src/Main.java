/**
 * 
 * @author sjiang18
 *
 */
public class Main {

	public static void main(String[] args) {
		SoccerPlayer a1 = new SoccerPlayer("Zack", 5, 3,2);
		SoccerPlayer a5 = new SoccerPlayer("Alice", 1, 0,0);
		SoccerPlayer a2 = new SoccerPlayer("Ann", 2, 2,3);
		SoccerPlayer a3 = new SoccerPlayer("Evan", 3, 6,1);
		SoccerPlayer a4 = new SoccerPlayer("Evan", 4, 3,2);
		Team<SoccerPlayer> t = new Team<>();
		t.insert(a1);
		t.insert(a2);
		t.insert(a3);
		t.insert(a4);
		t.insert(a5);
		System.out.println(t);
		t.sort(new CompareByPos<SoccerPlayer>());
		System.out.println(t);
		
		Team<SoccerPlayer> t2 = new Team<>();
		t2.insert(a1);
		t2.insert(new SoccerPlayer("Other", 1, 0,0));
		System.out.println(Team.intersection(t, t2));
	}

}
