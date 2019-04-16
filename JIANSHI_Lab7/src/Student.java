/**
 *
 */
class Student implements java.util.Observer {
    String name = null;
    Course course = null; //assuming one course for simplicity sake

    public Student(String aName) {
        super();
        name = aName;
    }

    String getName() {
        return name;
    }

    /**
     * Each time a student registers a course, he/she also subscribes for the prof's
     * notifications related to that course.
     */
    void registerCourse(Course aCourse, Secretary s) {
        course = aCourse;
        course.getProf().addObserver(this);
        course.getProf().addObserver(s);
        s.addObserver(this);
    }

    /**
     * This method is called whenever the observed object is changed. An application calls
     * an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code> method.
     */
    public void update(java.util.Observable o, Object arg) {
        System.out.println("\nStudent " + name + " says  ..Doh got it dude!");
        if(o instanceof Prof){
            Prof p = (Prof) o;
            System.out.println(p.getName() + " says " + arg);
            p.willTakeMidterm(this);
        }

        if(o instanceof Secretary){
            System.out.println("Secretary says " + arg);
        }

    }

    @Override
    public String toString(){
        return this.name;
    }

}
