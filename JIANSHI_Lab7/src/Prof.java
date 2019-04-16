import java.util.*;

/**
 * A Prof sets midterms, posts assignments...
 *
 * @author: Babak Esfandiari
 */
class Prof extends Observable {
    private String name = null;
    private Date midterm = null; // a bit artifical admittedly
    private List<Student> midtermTakers;

    public Prof(String aName) {
        super();
        name = aName;
        midtermTakers = new ArrayList<>();
    }

    public Date getMidterm() {
        return midterm;
    }

    public void setMidterm(Date date) {
        midterm = date;
        // see why it is useful to have getters and setters!
        // we can now notify observers of the change
        setChanged();
        notifyObservers(date);
    }

    public String getName() {
        return name;
    }

    public void willTakeMidterm(Observer ob){
        midtermTakers.add((Student) ob);
    }

    public void midtermTakerList(){
        System.out.println("\nMidterm Takers: ");
        for(Student s : midtermTakers){
            System.out.println(s);
        }
    }


}
