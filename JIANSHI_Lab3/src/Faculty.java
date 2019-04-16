/**
 * @author  Shiji Jiang
 */
public class Faculty extends Instructor{
    public Faculty(int age, int eccentricities){
        super(age, eccentricities);
    }

    @Override
    public int respect(){
        int result = getAge() + getEccentricities();
        return (result > 0)? result : 0;
    }

    @Override
    public void cope(){
        super.showCopeInfo();
        setEccentricities(getEccentricities() + 30);
    }
}
