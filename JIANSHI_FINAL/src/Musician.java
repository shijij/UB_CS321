
public class Musician {
	
	static int auto_increment;
	
	String name;
	int id;
	String instrument;
	int age;
	String music;
	
	static{
		auto_increment = 1;
	}
	
	Musician(String name, String instrument, int age, String music){
		this.name = name;
		this.instrument = instrument;
		this.age = age;
		this.music = music;
		id = auto_increment ++;
	}
	
	void play(){
		System.out.print(music);
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(o == this){
			return true;
		}
		if(o instanceof Musician){
			Musician p = (Musician) o;
			return p.name == name;
		}
		return false;
	}
}
