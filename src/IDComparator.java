/* This class is the IDComparator class which implements the Comparator
interface of type Media. Overrides the compare method, and if two objects
have the same average rating, then they can be sorted by ID.
 */
import java.util.Comparator;

public class IDComparator implements Comparator<Media> {

    @Override
    public int compare (Media object1, Media object2) {
        if (object1.getAverageRating() == object2.getAverageRating()) {
            return Integer.compare(object1.getID(), object2.getID());
        } else {
            return 0;
        }
    }
}

