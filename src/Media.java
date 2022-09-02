/* This class is the Media class.
It is the parent class of Journal, Movie and Book, and it holds the information
 relevant to these three different types of media including its type, title,
 availability, year, average rating, number of reviews and maximum borrow time.

 */

public class Media {
    private String type; // Stores the type of media

    private int ID;// Stores the ID of media

    private String title; // Stores the title of media

    private boolean isAvailable; // Stores the status of media - if true, the media is available, if false, the media is on loan

    private int year; //Store the year of the media

    private float averageRating; // Store the average rating of the media

    private int reviewNumber; // Stores the number of reviews of the media

    private int maxBorrowTime; // Stores the max borrow time of the media

    private float totalRatings; //Used to calculate the average rating

    public Media (String type, int ID, String title, int year) {
        setType(type);
        setID(ID);
        setTitle(title);
        setYear(year);
        setAverageRating(0);
        setReviewNumber(0);
        setAvailable(true); // Sets availability status to true hence it is available
        setTotalRatings(0);

    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public float getTotalRatings () {
        return totalRatings;
    }

    public void setTotalRatings (float totalRatings) {
        this.totalRatings = totalRatings;
    }

    public int getID () {
        return ID;
    }

    public void setID (int ID) {
        this.ID = ID;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public int getYear () {
        return year;
    }

    public void setYear (int year) {
        this.year = year;
    }

    public float getAverageRating () {
        return averageRating;
    }
/* The set average rating method takes the new rating from the user,
adds this to the total rating, increments the reviewNumber by one
and then evaluates the new average rating
 */
    public void setAverageRating (float newRating) {
        totalRatings = totalRatings + newRating;
        setReviewNumber(reviewNumber + 1);
        averageRating = totalRatings / reviewNumber;
    }

    public int getReviewNumber () {
        return reviewNumber;
    }

    public void setReviewNumber (int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public int getMaxBorrowTime () {
        return maxBorrowTime;
    }

    public void setMaxBorrowTime (int maxBorrowTime) {
        this.maxBorrowTime = maxBorrowTime;
    }

    public boolean getIsAvailable () {
        return isAvailable;
    }

    public void setAvailable (boolean available) {
        isAvailable = available;
    }
}
