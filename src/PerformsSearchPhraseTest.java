/* This class tests the performsSearchPhrase method from the Library class.
It takes into account 4 different scenarios: One when the phrase is null,
one when one match of the phrase in the title exists in the Library, one
where two match of the phrase in the title exists in the Library and one
where the phrase doesn't exist in any title in the library
 */


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformsSearchPhraseTest {
    private Library library;
    private final Media input1 = new Media("Movie", 210, "Going for the Touchdown", 1984);
    private final Media input2 = new Media("Book", 202, "The Haunted House Mystery", 1996);

    @BeforeEach
    public void setUp () {
        library = new Library();
        Library.setAllItems(input1);
        Library.setAllItems(input2);
    }


    @AfterEach
    public void tearDown () {
        library.clearAllItems();
    }

    @Test
    void performSearchPhraseIsNull () {
        ArrayList<Media> matched = new ArrayList<>();
        ArrayList<Media> empty = new ArrayList<>();
        library.clearAllItems();
        assertEquals(empty, library.performSearchPhrase(null, matched));
    }

    @Test
    void performSearchPhraseOneMatch () {
        ArrayList<Media> matched = new ArrayList<>();
        ArrayList<Media> expected = new ArrayList<>();
        expected.add(input2);
        assertEquals(expected, library.performSearchPhrase("house mystery", matched));

    }

    @Test
    void performSearchPhraseTwoMatches () {
        ArrayList<Media> matched = new ArrayList<>();
        ArrayList<Media> expected = new ArrayList<>();
        expected.add(input1);
        expected.add(input2);
        assertEquals(expected, library.performSearchPhrase("the", matched));

    }

    @Test
    void performSearchPhraseNoMatches () {
        ArrayList<Media> matched = new ArrayList<>();
        ArrayList<Media> output = new ArrayList<>();
        assertEquals(output, library.performSearchPhrase("hello", matched));

    }
}
