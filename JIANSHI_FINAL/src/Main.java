
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

interface Predicate<T> {

	public boolean test(T myT);
}

class Semaphore {
	private int counter;

	public Semaphore() {
		this(0);
	}

	public Semaphore(int i) {
		if (i < 0)
			throw new IllegalArgumentException(i + " < 0");
		counter = i;
	}

	public synchronized void V() {
		if (counter == 0) {
			this.notify();
		}
		counter++;
	}

	public synchronized void P() throws InterruptedException {
		while (counter == 0) {
			this.wait();
		}
		counter--;
	}
}

// This is a template for one of the Question 6 the Chocolate factory
// You have to write the other two classes. They are all similar
// As they are they will NOT WORK properly because they are un-synchronized
// FIX that

class MoldPlacer extends Thread {
	private Semaphore n; // next step
	private Semaphore c; // current step

	MoldPlacer(Semaphore current, Semaphore next) {
		c = current;
		n = next;
	}

	public void run() {
		for (int k = 1; k < 6; k++) {
			try {
				c.P();
				System.out.println(" ... MoldPlacer START: loop No." + k);
				Thread.sleep((int) (Math.random() * 2000));
				System.out.println(" ... MoldPlacer END loop No." + k);
				n.V();
			} catch (Exception ex) {
			}
		}
	}
}

class ChocoFiller extends Thread {
	private Semaphore n; // next step
	private Semaphore c; // current step

	ChocoFiller(Semaphore current, Semaphore next) {
		c = current;
		n = next;
	}

	public void run() {

		for (int k = 1; k < 6; k++) {
			try {
				c.P();
				System.out.println(" ------ ChocoFiller START: loop No." + k);
				Thread.sleep((int) (Math.random() * 2000));
				System.out.println(" ------ ChocoFiller END loop No." + k);
				n.V();

			} catch (Exception ex) {
			}
		}
	}
}

class NozzleReleaser extends Thread {
	private Semaphore n; // next step
	private Semaphore c; // current step

	NozzleReleaser(Semaphore current, Semaphore next) {
		c = current;
		n = next;
	}

	public void run() {
		for (int k = 1; k < 6; k++) {
			try {
				c.P();
				System.out.println(" ^^^^^^^^^ NozzleReleaser START: loop No." + k);
				Thread.sleep((int) (Math.random() * 2000));
				System.out.println(" ^^^^^^^^^ NozzleReleaser END loop No." + k);
				n.V();
			} catch (Exception ex) {
			}
		}
	}
}

public class Main {

	// This is where I chose to place my Semaphore declarations
	// so that the classes in Question 6 Choco Factory can access
	// them.
	// If you have a different architecture, you can put them elsewhere

	static Semaphore moldPlacer;
	static Semaphore chocoFiller;
	static Semaphore nozzleReleaser;

	// this is where you put the code for QUESTION 2 method
	//
	static List<Musician> merge(Orchestra A, Orchestra B) {
		List<Musician> result = new ArrayList<>();
		for (String x : A.names) {
			if (B.names.contains(x)) {
				for (Musician m : A.musicians) {
					if (m.name.equals(x)) {
						result.add(m);
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("CS321 Final exam\n" + "\n*** YOUR NAME SHOULD APPEAR HERE ***\n"
				+ "\nYou can insert new code in this" + "\nprogram, such as class and variable definitions"
				+ "\nas long as you do not modify the testing" + "\nstatements themselves\n");

		//
		// Question 1: Musician and Orchestra
		//
		// JOptionPane.showMessageDialog(null, "Press OK to START TESTING " );

		System.out.println("\n---------TESTING QUESTION 1.  Musicians Orchestra");

		Musician m1 = new Musician("YoYo Ma", "cello", 32, "-ZZ-");
		Musician m2 = new Musician("YoYo Ma", "cello", 32, "-ZZ-");
		Musician m3 = new Musician("Ax", "piano", 30, ".plk.");
		Musician m4 = new Musician("simon Rattle", "piano", 62, ".pan.");

		Orchestra MSO = new Orchestra();
		Orchestra OSS = new Orchestra();

		MSO.add(m1);
		MSO.add(m2);
		MSO.add(m3);
		MSO.add(m4);
		MSO.add(m4);

		OSS.add(m2);
		OSS.add(new Musician("Queyras", "cello", 32, "=Zz="));
		OSS.add(new Musician("Jones", "violin", 22, "^VV^"));
		OSS.add(new Musician("Judy", "viola", 29, "*VV*"));
		OSS.add(new Musician("simon Rattle", "violin", 32, "^Vc^"));
		OSS.add(new Musician("Jones", "violin", 22, "^Vj^"));

		System.out.println("Answer should be:  -ZZ-=Zz=^VV^*VV*^Vc^");
		System.out.print("Actual Answer      ");
		OSS.play();

		System.out.println("\n\nAnswer should be:  -ZZ-=ZZ=^VV^*VV*^VC^");
		OSS.tuneUp();
		System.out.print("Actual Answer      ");
		OSS.play();

		//
		// Question 2 : Orchestra merge()
		//

		System.out.println("\n\n-------TESTING QUESTION 2.  Orchestra Merge()");

		List<Musician> commonMusicians = merge(OSS, MSO);

		for (Musician m : commonMusicians)
			System.out.println(m.name);

		//
		// Question 3 : method choose()
		//

		System.out.println("\n\n-------TESTING QUESTION 3. Choosing musicians");
		System.out.println("\nTest 3.1:   listing the over 30 yr olds in OSS");

		List<Musician> oldies = OSS.choose(m -> m.age > 30);

		for (Musician m : oldies)
			System.out.println(m.name);

		System.out.println("\nTest 3.2:   listing the young violinists in OSS");

		for (Musician m : (OSS.choose(m -> m.instrument.equals("violin") && m.age < 30)))
			System.out.println(m.name);

		//
		// Question 4 : Executor class
		//

		System.out.println("\nTESTING QUESTION 4.  Executor class");

		Runnable[] myRuns = { () -> System.out.print("@"), () -> System.out.print("^"), () -> System.out.print("+"),
				() -> System.out.print("#"), () -> {
					try {
						Thread.sleep(500);
						System.out.print("X");
					} catch (Exception ex) {
					}
				} };

		System.out.println("\nTest 4.1:   thread output from executor run()");
		Executor E = new Executor(myRuns);
		E.run();
		try {
			Thread.sleep(1500);
		} catch (Exception ex) {
		}

		System.out.println("\nTest 4.2:   adding another Runnable");
		E.addRunnable(() -> System.out.print("$"));
		E.run();

		//
		// Question 6 : Choco Threads
		//

		JOptionPane.showMessageDialog(null, "Press OK to TEST QUESTION 6 CHOCOLATE THREADS");
		System.out.println("\n-----------TESTING QUE. 6 : CHOCOLATE FACTORY\n");

		chocoFiller = new Semaphore(0);
		nozzleReleaser = new Semaphore(0);
		moldPlacer = new Semaphore(1);

		MoldPlacer Judy = new MoldPlacer(moldPlacer, chocoFiller);
		ChocoFiller Larry = new ChocoFiller(chocoFiller, nozzleReleaser);
		NozzleReleaser Nat = new NozzleReleaser(nozzleReleaser, moldPlacer);

		Nat.start();
		Larry.start();
		Judy.start();

		//
		// Question 5 : CrazyFrame
		//

		JOptionPane.showMessageDialog(null,
				"WAIT FOR Chocolate Factory to end\n" + " Then Press OK to test QUESTION 5 CRAZYFRAME");
		System.out.println("\n-----------TESTING QUESTION  5:   CrazyFrame.");

		ActionListener[] listeners = { e -> System.out.println("ZERO behaviour"),
				e -> System.out.println("FIRST behaviour "), e -> System.out.println("SECOND  behaviour"),
				e -> System.out.println("THIRD  behaviour"), e -> System.out.println("FOURTH behaviour"),
				e -> System.out.println("FIFTH behaviour"), };

		CrazyFrame f = new CrazyFrame("This is a test", listeners);
		// Added just for terminating the problem when the X is clicked
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
}
