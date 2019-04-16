/**
 * 
 * @author sjiang18
 *
 */
public abstract class Player {
	protected String name;
	protected int pos;
	
	public Player(){
		this("noname", 0);
	}
	
	public Player(String name, int pos){
		this.name = name;
		this.pos = pos;
	}
	
	public abstract void move();
	@Override
	public String toString(){
		return "Name"+this.name+" Pos: "+pos;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(o == this){
			return true;
		}
		if(o instanceof Player){
			Player p = (Player) o;
			return p.name == name && p.pos == pos;
		}
		return false;
	}
}
