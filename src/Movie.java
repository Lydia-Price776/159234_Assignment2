/* This class is the Movie class.
It is a child class of the Media parent class, and
holds information specific to Movies including director.
 */

public class Movie extends Media {

    private String director; // Stores the directors name of a specific movie

    public Movie ( String type,int ID, String title, int year, String director){
        super(type,ID, title, year);
        super.setMaxBorrowTime(7);//Sets the max borrow time to 7 days when a Movie object is created
        setDirector(director);
    }

    public String getDirector () {
        return director;
    }

    public void setDirector (String director) {
        this.director = director;
    }

    public int compareTo(Movie otherMovie){
        return Integer.compare(getID(), otherMovie.getID());


    }


    }


