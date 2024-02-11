package User;

import Client.Client;
import Library.*;

import java.util.Scanner;

// This is the main driver class for the application
public class Driver {

    // Main method which is the entry point of the application
    public static void main(String[] args) {

        // Create a new Scanner object for user input
        Scanner scn = new Scanner(System.in);
        int choice, index = 0;

        // Print the welcome message and menu options
        System.out.println("------------------------\n" +
                "Welcome to FunReadings Library's program!" +
                "\nWould you prefer to: \n" +
                "\t 1. Enter the menu page \n" +
                "\t 2. Run a predefined scenario");

        // Get the user's choice
        choice = scn.nextInt();

        // Validate the user's choice
        while (choice != 1 && choice != 2) {
            System.out.println("Enter a valid output, either 1 or 2");
            choice = scn.nextInt();
        }

        // Process the user's choice
        switch (choice) {
            case 1:
                break;
            case 2:
                // If the user chose option 2, run the predefined scenario

                // Create an array to hold all items
                Items[] arrayAllItems = new Items[100];

                // Create some clients
                Client client1 = new Client();
                Client client2 = new Client("Alpha", 6005, "alpha@email.com");
                Client client3 = new Client(client2);
                Client client4 = new Client("Beta", 43880, "beta@email.com");
                Client[] arrayClient = {client1, client2, client3, client4};

                // Create some books
                Books[] arrayBooks = new Books[10];
                Books book1 = new Books();
                arrayBooks[index++] = book1;
                Books book2 = new Books("13 reasons why", "John Doe", 1998, 350);
                arrayBooks[index++] = book2;
                Books book3 = new Books(book2);
                arrayBooks[index++] = book3;
                Books book4 = new Books("Geronimo Stilton", "Jane Doe", 2005, 150);
                arrayBooks[index++] = book4;
                index = 0;

                // Create some media
                Media media1 = new Media();
                Media media2 = new Media("Ocean's eight", "Jonny Depp", 1979, "movie");
                Media media3 = new Media(media2);
                Media media4 = new Media("Almond", "tree", 1200, "plant");
                Media[] arrayMedia = {media1, media2, media3, media4};

                // Create some journals
                Journals journals1 = new Journals();
                Journals journals2 = new Journals("Spirou", "Robert Velter",
                        1938, 30);
                Journals journals3 = new Journals(journals2);
                Journals journals4 = new Journals("Tintin", "Hergé", 1940, 20);
                Journals[] arrayJournals = {journals1, journals2, journals3, journals4};

                // Print all clients
                for (Client client : arrayClient) {
                    if (client != null) {
                        System.out.println(client.toString());
                    }
                }
                System.out.println();

                // Print all books
                for (Books book : arrayBooks) {
                    if (book != null) {
                        System.out.println(book.toString());
                    }
                }
                System.out.println();

                // Print all media
                for (Media media : arrayMedia) {
                    if (media != null) {
                        System.out.println(media.toString());
                    }
                }
                System.out.println();

                // Print all journals
                for (Journals journal : arrayJournals) {
                    if (journal != null) {
                        System.out.println(journal.toString());
                    }
                }

                // Add books to arrayAllItems
                for (Books book : arrayBooks) {
                    if (book != null) {
                        arrayAllItems[index++] = book;
                    }
                }

                // Add media to arrayAllItems
                for (Media media : arrayMedia) {
                    if (media != null) {
                        arrayAllItems[index++] = media;
                    }
                }

                // Add journals to arrayAllItems
                for (Journals journal : arrayJournals) {
                    if (journal != null) {
                        arrayAllItems[index++] = journal;
                    }
                }

                // Print the book with the most pages
                System.out.println("\nThe book with the most pages is " + getBiggestBook(arrayAllItems) +"\n");

                // Make a deep copy of the array of books
                Books[] copiedArray = copyBooks(arrayBooks);
                System.out.println("The array printed under is a copied array: \n");

                // Print the copied array
                for (Books book : copiedArray) {
                    if (book != null) {
                        System.out.println(book.toString());
                    }
                }
                break;
        }
    }

    // Method to find the book with the most pages from an array of Books
    public static Books getBiggestBook(Books[] books) {
        Books biggestBook = null;
        for (Books book : books) {
            if (biggestBook == null || book.getNbOfPages() >= biggestBook.getNbOfPages()) {
                biggestBook = book;
            }
        }
        return biggestBook;
    }

    // Method to find the book with the most pages from an array of Items
    public static Books getBiggestBook(Items[] items) {
        Books biggestBook = null;
        for (Items item : items) {
            if (item instanceof Books book) {
                if (biggestBook == null || book.getNbOfPages() >= biggestBook.getNbOfPages()) {
                    biggestBook = book;
                }
            }
        }
        return biggestBook;
    }

    // Method to make a deep copy of an array of Books
    public static Books[] copyBooks(Books[] books) {
        Books[] copiedArray = new Books[books.length];
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                copiedArray[i] = new Books(books[i]);
            }
        }
        return copiedArray;
    }
}