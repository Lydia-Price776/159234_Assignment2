//This class runs the tasks for the assignment. It is my main class

import java.io.*;

public class RunCode {

    public static void main (String[] args) {
        displayInfo();
        File infile = new File("library.txt");
        Library library = new Library();
        Library.readFile(infile);
        library.printLibraryUnsorted();
        System.out.println();
        library.simulateLibrary();
    }

    private static void displayInfo () {
        System.out.println("----------------------------------------------");
        System.out.println("Assignment 2, 159.234, Semester 1 2022");
        System.out.println("Submitted by: Lydia Price 20004521");
        System.out.println("----------------------------------------------");
        System.out.println();
    }

}