//-----------------------------------------
// Assignment 1
// Part 2
// Written by: Étienne Beaumier, 40211362
//-----------------------------------------

/*
Driver class: This class contains the main method which is the
entry point of the application.
 */

package User;

import Library.Books;
import Library.Items;

public class Driver {
    public static void main(String[] args) {


    }

    public Books getBiggestBook(Books[] books) {
        int currentHighest = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getNbOfPages() >= books[currentHighest].getNbOfPages()) {
                currentHighest = i;
            }
        }
        return books[currentHighest];
    }

    public Books getBiggestBook(Items[] items) {
        int currentHighest = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof Books) {
                if (((Books) items[i]).getNbOfPages() >= ((Books) items[currentHighest]).getNbOfPages()) {
                    currentHighest = i;
                }
            }
        }
        return (Books) items[currentHighest];
    }

    public Books[] copyBooks(Books[] books) {
        Books[] copiedBooks = new Books[books.length];
        for (int i = 0; i < books.length; i++) {
            copiedBooks[i] = new Books(books[i]);
        }
        return copiedBooks;
    }

}