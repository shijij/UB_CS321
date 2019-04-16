import java.util.*;

public class Executor {
	List<Runnable> store;
	
	Executor(Runnable[] rs){
		store = new ArrayList<>();
		store.addAll(Arrays.asList(rs));
		
	}
	
	void addRunnable(Runnable x){
		store.add(x);
	}
	
	void run(){
		store.stream().map(s -> new Thread(s)).forEach(s -> s.start());
	}
	
}
