/* This class is the Library class. It has all the methods
 related to simulating the running of the Library.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Library {
    private static ArrayList<Media> allItems = new ArrayList<>(); // Array list used to store all items in library
    private static ArrayList<Movie> movies = new ArrayList<>();//Array list used to store all movies in library
    private static ArrayList<Book> books = new ArrayList<>();//Array list used to store all books in library
    private static ArrayList<Journal> journals = new ArrayList<>();//Array list used to store all journals in library
    DecimalFormat df = new DecimalFormat("0.00"); // Used to format the output of average rating


    //This method finds the index number of an item in the allItems
    // array list and returns the index
    private int findIndexNumber (Media toBeChecked) {
        int i;
        for (i = 0; i < allItems.size(); i++) {
            if (toBeChecked.equals(allItems.get(i))) {
                break;
            }
        }
        return i;
    }

    // This method prints the library in the order it is read in from the text file
    public void printLibraryUnsorted () {
        System.out.println("List of all items in the library:");
        for (Media allItem : allItems) {
            String space = " ".repeat(Math.max(0, (10 - allItem.getType().length())));
            System.out.println("ID: " + allItem.getID() +
                    "   Type: " + allItem.getType() + space +
                    "Title: " + allItem.getTitle());

        }
    }

    //This method checks a given string and returns true if it is an integer and false if not
    public static boolean isInteger (String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //This method checks a given string and returns true if it is a floating point number and false if not
    public static boolean isFloat (String s) {
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //This method sorts the library first by Average rating and then by ID
    public void sortLibrary () {

        allItems.sort(new AverageRatingComparator());
        allItems.sort(new IDComparator());
        printSortedLibrary();
    }

    //This method prints the sorted library
    public void printSortedLibrary () {
        System.out.println("List of all items in the library: ");
        for (Media allItem : allItems) {
            String space = " ".repeat(Math.max(0, (10 - allItem.getType().length())));
            System.out.println("Average Rating: " + df.format(allItem.getAverageRating()) +
                    "    Number of Reviews: " + allItem.getReviewNumber() +
                    "    ID: " + allItem.getID() +
                    "    Type: " + allItem.getType() +
                    space + "Title: " + allItem.getTitle());
        }
    }

    //This method simulates searching by ID. It takes user input where needed,
    // and accounts for input which is invalid
    public void searchById () {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ID to start search, or 'b' to go back to choose search method ");
        String idString = input.next();
        Media found;
        String command = "";

        do {
            if (isInteger(idString)) {
                found = performSearchById(idString);
                if (found == null) {
                    System.out.println("ID not found. Please try again.");
                } else {
                    System.out.println("ID: " + found.getID() + "  Type: " + found.getType() + "  Title: " + found.getTitle());
                    System.out.println();
                    System.out.println("Enter 'i' to search other item by ID, or enter any other key to select this item");
                    System.out.println();
                    command = input.next();
                    if (!command.equals("i")) {
                        selectItem(found);
                    }
                }
            } else if (!idString.equals("b")) {
                System.out.println("Invalid input. Please try again");
            } else {
                break;
            }
            System.out.println("Enter ID to start search, or 'b' to go back to choose search method ");
            idString = input.next();
        } while (!command.equals("b"));
    }

    //This method searches for an ID in the allItems array list
    // and returns a Media object of this if found, or returns null if not
    public Media performSearchById (String idString) {
        if (idString != null && !idString.equals("")) {
            int ID = Integer.parseInt(idString);
            for (Media allItem : allItems) {
                if (allItem.getID() == ID) {
                    return allItem;
                }
            }
        }
        return null;
    }

    // This method simulates searching by phrase. It takes user input where needed,
    // and accounts for input which is invalid or not found
    public void searchByPhrase () {
        String itemToSelect;
        int selectedItemNum;
        String phraseToSearch;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Enter phrase in title to start search, or 'b' to go back to choose search method ");
            phraseToSearch = input.nextLine().toLowerCase();
            if (phraseToSearch.equals("b")) {
                break;
            }
            ArrayList<Media> matched = new ArrayList<>();
            matched = performSearchPhrase(phraseToSearch, matched);
            printMatched(matched);
            if (matched.isEmpty()) {
                System.out.println("Phrase not found");
                break;
            }
            do {
                System.out.println("Enter item number to select item, or enter any other key to continue searching");
                itemToSelect = input.next();
                input.nextLine();
                if (isInteger(itemToSelect)) {
                    selectedItemNum = Integer.parseInt(itemToSelect);
                    if ((selectedItemNum > matched.size()) || (selectedItemNum < 1)) {
                        System.out.println("Invalid input, please try again");
                    } else {
                        Media selected = matched.get(selectedItemNum - 1);
                        selectItem(selected);
                    }
                } else {
                    break;
                }

            } while (!isInteger(itemToSelect) || Integer.parseInt(itemToSelect) >= matched.size() || Integer.parseInt(itemToSelect) < 1);
        } while (!isInteger(itemToSelect));
    }

    // This method performs the search for a phrase in a title in the all
    // items array list and returns the matched Media array list
    public ArrayList<Media> performSearchPhrase (String phraseToSearch, ArrayList<Media> matched) {
        for (Media allItem : allItems) {
            String title = allItem.getTitle().toLowerCase();
            if (title.contains(phraseToSearch)) {
                matched.add(allItem);
            }
        }
        return matched;
    }

    // This method prints the matched array list
    public void printMatched (ArrayList<Media> matched) {
        for (int i = 0; i < matched.size(); i++) {
            System.out.println("* " + (i + 1) + ":");
            System.out.println("ID: " + matched.get(i).getID() + "  Type: " + matched.get(i).getType() + "  Title: " + matched.get(i).getTitle());
            System.out.println();
        }
    }

    //This method simulates selecting an item, gives different options
    //based on the selected item and takes user input for further action
    public void selectItem (Media selected) {
        Scanner input = new Scanner(System.in);
        String command;
        int i = findIndexNumber(selected);
        printSingleItem(allItems.get(i));
        do {
            i = findIndexNumber(selected);
            if (allItems.get(i).getIsAvailable()) {
                System.out.println("Enter 'b' to borrow, enter 'a' to rate this item or any other key to restart");
                command = input.next();
                if (command.equals("b")) {
                    borrowItem(allItems.get(i));
                } else if (command.equals("a")) {
                    rateItem(allItems.get(i));
                    sortLibrary();
                } else {
                    simulateLibrary();
                    break;
                }
            } else {
                System.out.println("Enter 'r' to return, enter 'a' to rate this item or any other key to restart");
                command = input.next();
                if (command.equals("r")) {
                    returnItem(allItems.get(i));
                } else if (command.equals("a")) {
                    rateItem(allItems.get(i));
                    sortLibrary();
                } else {
                    simulateLibrary();
                    break;
                }
            }
        } while (true);
    }

    // This method takes a user input rating and accounts for invalid input.
    // If the input is valid, then the average rating is updated
    public void rateItem (Media itemToRate) {
        int i = findIndexNumber(itemToRate);
        System.out.println("Please enter your rating 0 - 10");
        Scanner input = new Scanner(System.in);
        String rating = input.next();
        if (isFloat(rating)) {
            float ratingFloat = Float.parseFloat(rating);
            if (ratingFloat >= 0 && ratingFloat <= 10) {
                allItems.get(i).setAverageRating(ratingFloat);
                System.out.println("This items new average rating is " + df.format(allItems.get(i).getAverageRating()));
                printSingleItem(allItems.get(i));
            } else {
                System.out.println("Rating is outside of the given range, please try again");
                rateItem(allItems.get(i));
            }
        } else {
            System.out.println("Input is not a number. Please try again");
            rateItem(allItems.get(i));
        }
    }

    // This method borrows an item from the library and prints
    // the due date which is current date plus the max borrow time
    public void borrowItem (Media itemToBorrow) {
        int i = findIndexNumber(itemToBorrow);
        int max = allItems.get(i).getMaxBorrowTime();
        LocalDate dueDate = LocalDate.now().plusDays(max);
        System.out.println("This items Due date is " + dueDate);
        allItems.get(i).setAvailable(false);
        printSingleItem(allItems.get(i));
    }

    // This method returns an item to the library
    public void returnItem (Media itemToReturn) {
        int i = findIndexNumber(itemToReturn);
        System.out.println("This item is returned");
        allItems.get(i).setAvailable(true);
        printSingleItem(allItems.get(i));
    }

    // This method prints all details associated with a single item
    public void printSingleItem (Media itemToPrint) {
        System.out.println();
        System.out.println("The selected item is:");
        System.out.println("Type: " + itemToPrint.getType());
        System.out.println("Title: " + itemToPrint.getTitle());
        System.out.println("ID: " + itemToPrint.getID());
        System.out.println("Year: " + itemToPrint.getYear());
        System.out.println("Average Rating: " + df.format(itemToPrint.getAverageRating()));
        System.out.println("Number of Reviews: " + itemToPrint.getReviewNumber());

        if (itemToPrint.getIsAvailable()) {
            System.out.println("Status: available");
        } else {
            System.out.println("Status: on loan");
        }

        if (itemToPrint.getType().equals("Movie")) {
            for (Movie movie : movies) {
                if (movie.getID() == itemToPrint.getID()) {
                    System.out.println("Director: " + movie.getDirector());
                    System.out.println("Max Days for Borrowing: " + movie.getMaxBorrowTime());
                    break;
                }
            }
        } else if (itemToPrint.getType().equals("Book")) {
            for (Book book : books) {
                if (book.getID() == book.getID()) {
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Number of Pages: " + book.getPageNumber());
                    System.out.println("Max Days for Borrowing: " + book.getMaxBorrowTime());
                    break;
                }
            }
        } else {
            for (Journal journal : journals) {
                if (journal.getID() == itemToPrint.getID()) {
                    System.out.println("Volume: " + journal.getVolume());
                    System.out.println("Number: " + journal.getNumber());
                    System.out.println("Max Days for Borrowing: " + journal.getMaxBorrowTime());
                    break;
                }
            }
        }
        System.out.println();
    }

    // This method simulates the running of the library
    public void simulateLibrary () {
        Scanner input = new Scanner(System.in);
        printCommands();
        String command = input.next();

        while (true) {
            switch (command) {
                case "q" -> exit(10);
                case "s" -> sortLibrary();
                case "i" -> searchById();
                default -> searchByPhrase();
            }
            System.out.println();
            printCommands();
            command = input.next();
        }
    }

    //This method prints the library commands
    public void printCommands () {
        System.out.println("Enter 'q' to quit");
        System.out.println("or enter 's' to sort (first by average rating and then by ID) and display all items");
        System.out.println("or enter 'i' to search by ID");
        System.out.println("or enter any other key to search by phrase in title");
    }

    // This method reads in the library file and assigns items to the correct
    // arraylist: Movie, Journal or Book and also adds them to the allItems array list
    public static void readFile (File infile) {
        String type, title, director, author;
        int ID, year, volume, pageNum, number;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(infile)))) {
            while (scanner.hasNextLine()) {
                Scanner lineScanner = new Scanner(scanner.nextLine());
                while (lineScanner.hasNext()) {
                    lineScanner.useDelimiter(",");
                    type = lineScanner.next();
                    ID = lineScanner.nextInt();
                    title = lineScanner.next();
                    year = lineScanner.nextInt();

                    if (type.equals("Movie")) {
                        director = lineScanner.next();
                        movies.add(new Movie(type, ID, title, year, director));
                        allItems.add(new Movie(type, ID, title, year, director));
                    }
                    if (type.equals("Book")) {
                        author = lineScanner.next();
                        pageNum = lineScanner.nextInt();
                        books.add(new Book(type, ID, title, year, author, pageNum));
                        allItems.add(new Book(type, ID, title, year, author, pageNum));
                    }
                    if (type.equals("Journal")) {
                        volume = lineScanner.nextInt();
                        number = lineScanner.nextInt();
                        journals.add(new Journal(type, ID, title, year, volume, number));
                        allItems.add(new Journal(type, ID, title, year, volume, number));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //This method adds an item to the allItems array list
    public static void setAllItems (Media addItem) {
        allItems.add(addItem);
    }

    // This method clears all items from the allItems array list
    public void clearAllItems () {
        allItems.clear();
    }
}

