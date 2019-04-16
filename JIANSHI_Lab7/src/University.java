import java.util.Date;

/**
 * A Prof sets midterms, posts assignments...
 */

public class University {

    /**
     * Test... each student will display their name and "Doh.. got it dude!" as a
     * result...
     */
    public static void main(String[] args) throws Exception {
        // a prof
        Prof p = new Prof("DZimitri");

        // a course
        Course c = new Course("CSC328", p);

        Secretary s = new Secretary();

        // and four students
        Student s1 = new Student("Kramer");
        Student s2 = new Student("Elaine");
        Student s3 = new Student("Jerry");
        Student s4 = new Student("George");

        // register them with the Observable ( the prof )
        s1.registerCourse(c, s);
        s2.registerCourse(c, s);
        s3.registerCourse(c, s);
        s4.registerCourse(c, s);

        // prof sets the midterm date and miraculously all are notified
        p.setMidterm(new Date());
        p.midtermTakerList();
        // System.in.read();
    }

}
