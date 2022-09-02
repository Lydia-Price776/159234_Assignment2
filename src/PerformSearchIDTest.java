/* This class tests the performsSearchID from the Library class.
It takes into account 3 different scenarios: One when the ID is null,
one when the ID exists in the Library and one where the ID doesn't exist
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerformSearchIDTest {
    private Library library;
    private final Media input = new Media("Book", 231, "How to Make Money", 1987);


    @BeforeEach
    public void setUp() {
        library = new Library();
        Library.setAllItems(input);
    }

    @AfterEach
    public void tearDown() {
        library.clearAllItems();
    }

    @Test
    void performSearchByIdNull () {
        assertNull(library.performSearchById(null));
    }

    @Test
    void performSearchByIdExists () {
        assertEquals(input, library.performSearchById("231"));
    }

    @Test
    void performSearchByIdDoesntExist () {
        assertNull(library.performSearchById("360"));
    }



}
