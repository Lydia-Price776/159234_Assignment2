/* This class is the Book class.
It is a child class of the Media parent class, and
holds information specific to Books including author and page number.
 */

public class Book extends Media  {

    private String author; // Stores the author of the book

    private int pageNumber; //Store the number of pages of the book

    public Book (String type, int ID, String title, int year, String author, int pageNumber){
        super (type, ID, title, year);
        super.setMaxBorrowTime(28); //Sets the max borrow time to 28 days when a Book object is created
        setAuthor(author);
        setPageNumber(pageNumber);
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public int getPageNumber () {
        return pageNumber;
    }

    public void setPageNumber (int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
