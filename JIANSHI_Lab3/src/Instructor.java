import java.util.Random;
public abstract class Instructor {

    private int unreadMail;
    private int age;
    private int eccentricities;


    public int getUnreadMail() {
        return unreadMail;
    }

    public void setUnreadMail(int unreadMail) {
        this.unreadMail = unreadMail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getEccentricities() {
        return eccentricities;
    }

    public void setEccentricities(int eccentricities) {
        this.eccentricities = eccentricities;
    }



    public Instructor(int age, int eccentricities){
        this.age = age;
        this.eccentricities = eccentricities;
        this.unreadMail = 0;
    }


    public int stress(){
        return (unreadMail > 1000)? 1000 : unreadMail;
    }

    public int respect(){
        int result = age - eccentricities;
        return (result > 0)? result : 0;
    }

    public void getMail(int amount){
        unreadMail += amount;
        Random rand = new Random();
        int random = rand.nextInt(10);
        if (random == 0){
            eccentricities += 2;
        } else if(random == 1){
            eccentricities -= 2;
        }
        if(this.isUnhappy()){
            this.cope();
        }
    }

    public boolean isUnhappy(){
        return this.stress() > this.respect();
    }

    public abstract void cope();

    public void showCopeInfo(){
        System.out.println(this.getClass().getSimpleName()+" is COPING");
        System.out.println("UnreadMails: "+ unreadMail
                + ", Age: " + age
                + ", Eccentricities: " + eccentricities);
        System.out.println("Stress: "+ this.stress()
                + ", Respect: " + this.respect() + "\n");
    }

}
