public class Sheep {

    private static int sheepCreated = 1;

    int id;
    String name;


    public Sheep(String name, int id){
        this.name = name;
        this.id = id;

    }

    public Sheep(){
        this("noname-"+sheepCreated, sheepCreated);
        sheepCreated ++;
    }

    @Override
    public String toString(){
        return "["+name+", ID:" + id + "]";
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }
        if(!(o instanceof  Sheep)){
            return false;
        }
        Sheep temp = (Sheep) o;
        return id == temp.id && name == temp.name;
    }

}
