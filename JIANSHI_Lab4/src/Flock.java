import java.util.*;

public class Flock{

    String name;
    int num;
    private List<Sheep> sheep_pen;

    public Flock(String name){
        this.name = name;
        this.sheep_pen = new ArrayList<>(20);
        this.num = 0;
    }

    public Flock(){
        this("nonameFlock");
    }

    public void change_pen(List<Sheep> s){
        List<Sheep> temp = new ArrayList<>();
        for (Sheep t : s){
            temp.add(new Sheep(t.name, t.id));
        }
        this.sheep_pen = temp;
        num = sheep_pen.size();
    }

    public void insert(Sheep s){
        sheep_pen.add(s);
        num ++;
    }

    public Iterator iterator(){
        return sheep_pen.iterator();
    }

    public boolean contains(Sheep s){
        return sheep_pen.contains(s);
    }

    public void sort(Comparator<Sheep> c){
        sheep_pen.sort(c);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append("\nSize:");
        sb.append(num);
        sb.append("\n");
        sb.append(sheep_pen);
        return sb.toString();
    }
}
