/* This class is the Journal class.
It is a child class of the Media parent class, and
holds information specific to Journals including volume and number.
 */

public class Journal extends Media {

    private int volume; //Stores the volume of the journal

    private int number; //Store the number of the journal

    public Journal (String type,int ID, String title, int year, int volume, int number){
        super (type, ID, title, year);
        super.setMaxBorrowTime(14);//Sets the max borrow time to 14 days when a Journal object is created
        setVolume(volume);
        setNumber(number);
    }

    public int getVolume () {
        return volume;
    }

    public void setVolume (int volume) {
        this.volume = volume;
    }

    public int getNumber () {
        return number;
    }

    public void setNumber (int number) {
        this.number = number;
    }
}
