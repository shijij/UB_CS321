import java.util.Observable;
import java.util.Observer;

public class Secretary extends Observable implements Observer {

    private int room;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("\nSecretary have been notified of a midterm by prof "
        + ((Prof) o).getName() + " Midterm @" + ((Prof) o).getMidterm());
        System.out.println(((Prof) o).getName() + " says " + arg);
        room = (int) (Math.random() * 10) + 1;
        setChanged();
        notifyRoom();
    }

    public void notifyRoom(){
        notifyObservers("Room: "+ room);
    }


}
