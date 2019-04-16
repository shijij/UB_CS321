import java.util.Random;

/**
 * @author Shiji Jiang
 */
public class Grad extends Instructor {


    public Grad(int age, int eccentricities){
        super(age, eccentricities);
    }

    @Override
    public int stress(){
        return (getUnreadMail() > 1500)? 1500 : getUnreadMail();
    }

    @Override
    public void cope(){
        super.showCopeInfo();
        Random rand = new Random();
        boolean random = rand.nextBoolean();
        setEccentricities(random? getEccentricities() + 3: getEccentricities() - 3);
    }
}
