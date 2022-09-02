/* This class is the IDComparator class which implements the Comparator
interface of type Media. It uses the compare method from the interface
to sort by Average rating
 */

import java.util.Comparator;

public class AverageRatingComparator implements Comparator<Media> {

    public int compare(Media object1, Media object2) {

        return Float.compare(object2.getAverageRating(), object1.getAverageRating());
    }
}
