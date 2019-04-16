/**
 * Movie Class Part 1 B
 */
public class Movie {
    public String name;
    public double rating;
    public int year;

    public Movie(String name, double rating, int year){
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    @Override
    public String toString(){
        return "Name: "+name+"\nYear: " + year + "\nRating: " + rating + "\n";
    }

    /**
     *  Part 1 - B - 2
     * @param o
     * @return
     */
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof  Movie)){
            return false;
        }
        Movie m = (Movie) o;
        return this.rating == m.rating
                && this.name == m.name
                && this.year == m.year;
    }

}
