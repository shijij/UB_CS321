/**
 * @author Shiji Jiang
 */
public class Lecturer extends Instructor {
    public Lecturer(int age, int eccentricities){
        super(age, eccentricities);
    }

    @Override
    public void cope(){
        super.showCopeInfo();
        setUnreadMail((int) Math.round(getUnreadMail() * 0.4));
    }
}
