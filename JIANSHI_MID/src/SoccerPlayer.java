/**
 * 
 * @author sjiang18
 *
 */
public class SoccerPlayer extends Player{
	protected int x;
	protected int y;
	
	public SoccerPlayer(String name, int pos, int x, int y){
		super(name, pos);
		this.x = x;
		this.y = y;
	}
	
	public void move(){
		int change = 1;
		if(Math.random() >= 5){
			change = -1;
		}
		if(Math.random() >= 5){
			x += change;
		} else {
			y += change;
		}
	}
	
	
}
