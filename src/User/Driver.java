package User;

import Client.Client;
import Library.*;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int choice;

        // Initialize arrays
        Items[] itemsMenu = new Items[1000];
        Client[] clientMenu = new Client[100];
        Books[] bookMenu = new Books[100];
        Media[] mediaMenu = new Media[100];
        Journals[] journalsMenu = new Journals[100];

        while (true) {
            System.out.println("------------------------\n" +
                    "Welcome to FunReadings Library's program!" +
                    "\nWould you prefer to: \n" +
                    "\t 1. Enter the menu page \n" +
                    "\t 2. Run a predefined scenario \n" +
                    "\t 3. Quit");

            choice = scn.nextInt();
            scn.nextLine();

            while (choice < 1 || choice > 3) {
                System.out.println("Enter a valid output, either 1, 2 or 3");
                choice = scn.nextInt();
            }


            switch (choice) {
                case 1:
                    displayMenu();
                    choice = scn.nextInt();
                    handleMenuChoice(choice, scn, itemsMenu, clientMenu, bookMenu, mediaMenu, journalsMenu);
                    break;
                case 2:
                    runPredefinedScenario();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }

        }
    }

    private static void handleMenuChoice(int choice, Scanner scn, Items[] itemsMenu, Client[] clientMenu, Books[] bookMenu, Media[] mediaMenu, Journals[] journalsMenu) {
        switch (choice) {
            case 1:
                System.out.println("You chose to manage items. Please select an option:");
                System.out.println("\t1. Add item");
                System.out.println("\t2. Delete item");
                System.out.println("\t3. Modify item");
                System.out.println("\t4. List items");
                System.out.println("\t5. Print all items from all categories");

                int itemChoice = scn.nextInt();
                scn.nextLine();

                while (itemChoice < 1 || itemChoice > 5) {
                    System.out.println("Enter a valid output between 1 and 5");
                    itemChoice = scn.nextInt();
                }

                switch (itemChoice) {
                    case 1:
                        choice = returnChoice();
                        addItem(itemsMenu, bookMenu, mediaMenu, journalsMenu, choice);
                        break;
                    case 2:
                        choice = returnChoice();
                        System.out.println("Enter the ID of the item you want to delete:");
                        String itemID = scn.nextLine();
                        switch (choice) {
                            case 1:
                                bookMenu = deleteItem(bookMenu, itemID);
                                itemsMenu = deleteItem(itemsMenu, itemID);
                                break;
                            case 2:
                                journalsMenu = deleteItem(journalsMenu, itemID);
                                itemsMenu = deleteItem(itemsMenu, itemID);
                                break;
                            case 3:
                                mediaMenu = deleteItem(mediaMenu, itemID);
                                itemsMenu = deleteItem(itemsMenu, itemID);
                                break;
                        }
                        break;
                    case 3:
                        choice = returnChoice();
                        System.out.println("Enter the ID of the item you want to change:");
                        itemID = scn.nextLine();
                        if(itemID.startsWith("B")) {
                            change(bookMenu, itemID);
                            change(itemsMenu, itemID, bookMenu);
                        }
                        else if(itemID.startsWith("J")) {
                            change(journalsMenu, itemID);
                            change(itemsMenu, itemID, journalsMenu);
                        }
                        else if(itemID.startsWith("M")) {
                            change(mediaMenu, itemID);
                            change(itemsMenu, itemID, mediaMenu);
                        } else{
                            System.out.println("Invalid ID");
                        }
                        break;
                    case 4:
                        choice = returnChoice();
                        if(choice == 1) {
                            for (Books book : bookMenu) {
                                if (book != null) {
                                    System.out.println(book.toString());
                                }
                            }
                        } else if (choice == 2) {
                            for (Journals journal : journalsMenu) {
                                if (journal != null) {
                                    System.out.println(journal.toString());
                                }
                            }
                        } else if (choice == 3) {
                            for (Media medium : mediaMenu) {
                                if (medium != null) {
                                    System.out.println(medium.toString());
                                }
                            }
                        }
                        break;
                    case 5:

                        System.out.println("You chose to print all items from all categories.");
                        for(Items item : itemsMenu) {
                            if (item != null) {
                                System.out.println(item.toString());
                            }
                        }
                        break;
                }
                break;
            case 2:
                System.out.println("You chose to manage clients. Please select an option:");
                System.out.println("\t1. Add client");
                System.out.println("\t2. Edit client");
                System.out.println("\t3. Delete client");

                int clientChoice = scn.nextInt();

                while (clientChoice < 1 || clientChoice > 3) {
                    System.out.println("Enter a valid output between 1 and 3");
                    clientChoice = scn.nextInt();
                }

                switch (clientChoice) {
                    case 1:
                        addClient(clientMenu);
                        break;
                    case 2:
                        System.out.println("You chose to edit a client. Please enter the client's ID:");
                        String clientID = scn.nextLine();
                        editClient(clientMenu, clientID);
                        break;
                    case 3:
                        System.out.println("You chose to delete a client. Please enter the client's ID:");
                        clientID = scn.nextLine();
                        scn.nextLine();
                        clientMenu = deleteClient(clientMenu, clientID);
                        break;
                }
                break;
            case 3:
                System.out.println("You chose to lease or return an item. Please select an option:");
                System.out.println("\t1. Lease an item");
                System.out.println("\t2. Return an item");

                int leaseReturnChoice = scn.nextInt();
                scn.nextLine();

                while (leaseReturnChoice < 1 || leaseReturnChoice > 2) {
                    System.out.println("Enter a valid output, either 1 or 2");
                    leaseReturnChoice = scn.nextInt();
                }

                switch (leaseReturnChoice) {
                    case 1:
                        System.out.println("You chose to lease an item. Please enter the client's ID:");
                        int clientID = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Please enter the item's ID:");
                        String itemID = scn.nextLine();
                        leaseItem(clientID, itemID, clientMenu, itemsMenu);
                        break;
                    case 2:
                        System.out.println("You chose to return an item. Please enter the client's ID:");
                        clientID = scn.nextInt();
                        scn.nextLine();
                        System.out.println("Please enter the item's ID:");
                        itemID = scn.nextLine();
                        returnItem(clientID, itemID, clientMenu, itemsMenu);
                        break;
                }
                break;
            case 4:
                System.out.println("You chose to show all items leased by a client. Please enter the client's ID:");
                int clientId = scn.nextInt();
                // Call a method to get all items leased by the client
                // This method should return an array of items which you can then print
                // For example:
                Items[] itemsLeasedByClient = getItemsLeasedByClient(clientId, clientMenu, itemsMenu);
                boolean clientHasLeasedItems = false;
                for (Items item : itemsLeasedByClient) {
                    if (item != null) {
                        System.out.println(item.toString());
                        clientHasLeasedItems = true;
                    }
                }
                if (!clientHasLeasedItems) {
                    System.out.println("This client has not leased any items.");
                }
                break;
            case 5:
                System.out.println("You chose to show all items leased by all clients.");
                // Call a method to get all items leased by all clients
                // This method should return a 2D array where the first dimension is client IDs and the second dimension is items
                // For example:
                Items[][] itemsLeasedByAllClients = getItemsLeasedByAllClients(clientMenu, itemsMenu);
                boolean itemsHaveBeenLeased = false;
                for (int i = 0; i < itemsLeasedByAllClients.length; i++) {
                    for (Items item : itemsLeasedByAllClients[i]) {
                        if (item != null) {
                            System.out.println("Client " + i + ": " + item.toString());
                            itemsHaveBeenLeased = true;
                        }
                    }
                }
                if (!itemsHaveBeenLeased) {
                    System.out.println("No items have been leased.");
                }
                break;
            case 6:
                // Logic for displaying the biggest book
                System.out.println("The biggest book in the library is: " + getBiggestBook(bookMenu));
                break;

            case 7:
                // Logic for making a copy of the books array
                Books[] copiedBooksMenu = copyBooks(bookMenu);
                System.out.println("Copied books array:");
                for (Books book : copiedBooksMenu) {
                    if (book != null) {
                        System.out.println(book.toString());
                    }
                }
                break;

            case 8:
                // Quitting the program
                System.out.println("Goodbye!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                displayMenu();
                choice = scn.nextInt();
                handleMenuChoice(choice, scn, itemsMenu, clientMenu, bookMenu, mediaMenu, journalsMenu);
        }
    }


    private static void runPredefinedScenario() {
        // Initialize variables and arrays for the scenario
        int index = 0;
        Items[] arrayAllItems = new Items[100];

        // Create some clients
        Client client1 = new Client();
        Client client2 = new Client("Alpha", 6005, "alpha@email.com");
        Client client3 = new Client(client2); // Assuming Client has a copy constructor
        Client client4 = new Client("Beta", 43880, "beta@email.com");
        Client[] arrayClient = {client1, client2, client3, client4};

        // Create some books
        Books[] arrayBooks = new Books[10];
        Books book1 = new Books(); // Assuming default constructor sets some properties
        arrayBooks[index++] = book1;
        Books book2 = new Books("13 reasons why", "John Doe", 1998, 350);
        arrayBooks[index++] = book2;
        Books book3 = new Books(book2); // Assuming Books has a copy constructor
        arrayBooks[index++] = book3;
        Books book4 = new Books("Geronimo Stilton", "Jane Doe", 2005, 150);
        arrayBooks[index++] = book4;
        index = 0; // Reset index for reuse

        // Create some media
        Media media1 = new Media();
        Media media2 = new Media("Ocean's eight", "Jonny Depp", 1979, "movie");
        Media media3 = new Media(media2); // Assuming Media has a copy constructor
        Media media4 = new Media("Almond", "tree", 1200, "plant");
        Media[] arrayMedia = {media1, media2, media3, media4};

        // Create some journals
        Journals journals1 = new Journals();
        Journals journals2 = new Journals("Spirou", "Robert Velter", 1938, 30);
        Journals journals3 = new Journals(journals2); // Assuming Journals has a copy constructor
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

        // Add books, media, and journals to arrayAllItems
        for (Books book : arrayBooks) {
            if (book != null) {
                arrayAllItems[index++] = book;
            }
        }
        for (Media media : arrayMedia) {
            if (media != null) {
                arrayAllItems[index++] = media;
            }
        }
        for (Journals journal : arrayJournals) {
            if (journal != null) {
                arrayAllItems[index++] = journal;
            }
        }

        // Assuming getBiggestBook can also handle Items[] as input
        System.out.println("\nThe book with the most pages is " + getBiggestBook(arrayBooks) + "\n");

        // Make a deep copy of the array of books and print the copied array
        Items[] copiedArray = copyBooks(arrayMedia);
        System.out.println("The array printed under is a copied array: \n");
        for (Items items : copiedArray) {
            if (items != null) {
                System.out.println(items.toString());
            }
        }
    }


    public static Books getBiggestBook(Books[] books) {
        Books biggestBook = null;
        for (Books book : books) {
            if (biggestBook == null || (book != null && book.getNbOfPages() > biggestBook.getNbOfPages())) {
                biggestBook = book;
            }
        }
        return biggestBook;
    }

    public static Items[] copyBooks(Items[] it) {
        Items[] copiedArray = new Items[it.length];
        for (int i = 0; i < it.length; i++) {
            if (it[i] != null) {
                copiedArray[i] = new Items(it[i]); // Ensure Books class has a copy constructor
            }
        }
        return copiedArray;
    }

    public static Books[] copyBooks(Books[] books) {
        Books[] copiedBooks = new Books[books.length];
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                copiedBooks[i] = new Books(books[i]);
            }
        }
        return copiedBooks;
    }
    private static void displayMenu() {
        System.out.println("""
                FunReadings's Library Menu
                \t 1. Manage items (Add, Delete, Modify, List, Print)
                \t 2. Manage clients (Add, Edit, Delete)
                \t 3. Lease or return an item
                \t 4. Show all items leased by a client
                \t 5. Show all items leased by all clients
                \t 6. Display the biggest book in the library
                \t 7. Make a copy of the books array
                \t 8. Exit the program.""");
    }

    public static int returnChoice() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Would you like to \n" +
                "\t 1. Do the action on a book \n" +
                "\t 2. Do the action on a journal \n" +
                "\t 3. Do the action on a media");

        int choice = scn.nextInt();

        // Validate the user's choice
        while (choice < 1 || choice > 3) {
            System.out.println("Enter a valid output between 1 and 3");
            choice = scn.nextInt();
        }
        return choice;
    }

    public static void addItem(Items[] itemsMenu, Books[] books, Media[] medias, Journals[] journals,
                               int choice) {
        Scanner scn = new Scanner(System.in);
        int index = getFirstNullIndex(itemsMenu);
        int indexBooks = getFirstNullIndex(books);
        int indexJournals = getFirstNullIndex(journals);
        int indexMedia = getFirstNullIndex(medias);
        if (index == -1) {
            System.out.println("No space left to add more items.");
            return;
        }
        if (indexBooks == -1) {
            System.out.println("No space left to add more items.");
            return;
        }
        if (indexJournals == -1) {
            System.out.println("No space left to add more items.");
            return;
        }
        if (indexMedia == -1) {
            System.out.println("No space left to add more items.");
            return;
        }
        switch (choice) {
            case 1:
                System.out.println("You chose to add a book. Please enter the title, author, publication year, and number of pages:");
                String title = scn.nextLine();
                String author = scn.nextLine();
                int publicationYear = scn.nextInt();
                scn.nextLine();
                int nbOfPages = scn.nextInt();
                scn.nextLine();
                Books book = new Books(title, author, publicationYear, nbOfPages);
                itemsMenu[index] = book;
                books[indexBooks] = book;
                System.out.println(book.toString());
                break;
            case 2:
                System.out.println("You chose to add a journal. Please enter the title, author, publication year, and volume number:");
                title = scn.nextLine();
                author = scn.nextLine();
                publicationYear = scn.nextInt();
                scn.nextLine();
                int volumeNumber = scn.nextInt();
                scn.nextLine();
                Journals journal = new Journals(title, author, publicationYear, volumeNumber);
                itemsMenu[index] = journal;
                journals[indexJournals] = journal;
                System.out.println(journal.toString());
                break;
            case 3:
                System.out.println("You chose to add a media. Please enter the title, author, publication year, and type:");
                title = scn.nextLine();
                author = scn.nextLine();
                publicationYear = scn.nextInt();
                scn.nextLine();
                String type = scn.nextLine();
                Media media = new Media(title, author, publicationYear, type);
                itemsMenu[index] = media;
                medias[indexMedia] = media;
                System.out.println(media.toString());
                break;
        }
    }

    public static Books[] deleteItem(Books[] books, String itemID) {
        int nullIndex = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && books[i].getID().equals(itemID)) {
                books[i] = null;
                nullIndex = i;
                break;
            }
        }
        if (nullIndex != -1) {
            Books[] newBooks = new Books[books.length - 1];
            for (int i = 0, j = 0; i < books.length; i++) {
                if (i != nullIndex) {
                    newBooks[j++] = books[i];
                }
            }
            return newBooks;
        }
        return books;
    }

    public static Journals[] deleteItem(Journals[] journals, String itemID) {
        int nullIndex = -1;
        for (int i = 0; i < journals.length; i++) {
            if (journals[i] != null && journals[i].getID().equals(itemID)) {
                journals[i] = null;
                nullIndex = i;
                break;
            }
        }
        if (nullIndex != -1) {
            Journals[] newJournals = new Journals[journals.length - 1];
            for (int i = 0, j = 0; i < journals.length; i++) {
                if (i != nullIndex) {
                    newJournals[j++] = journals[i];
                }
            }
            return newJournals;
        }
        return journals;
    }

    public static Media[] deleteItem(Media[] media, String itemID) {
        int nullIndex = -1;
        for (int i = 0; i < media.length; i++) {
            if (media[i] != null && media[i].getID().equals(itemID)) {
                media[i] = null;
                nullIndex = i;
                break;
            }
        }
        if (nullIndex != -1) {
            Media[] newMedia = new Media[media.length - 1];
            for (int i = 0, j = 0; i < media.length; i++) {
                if (i != nullIndex) {
                    newMedia[j++] = media[i];
                }
            }
            return newMedia;
        }
        return media;
    }
    public static Items[] deleteItem(Items[] items, String itemID) {
        int nullIndex = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getID().equals(itemID)) {
                items[i] = null;
                nullIndex = i;
                break;
            }
        }
        if (nullIndex != -1) {
            Items[] newItems = new Items[items.length - 1];
            for (int i = 0, j = 0; i < items.length; i++) {
                if (i != nullIndex) {
                    newItems[j++] = items[i];
                }
            }
            return newItems;
        }
        return items;
    }

    private static Books change(Books[] books, String itemID) {
        Scanner scn = new Scanner(System.in);
        for (Books book : books) {
            if (book != null && book.getID().equals(itemID)) {
                System.out.println("Enter the new title, author, publication year, and number of pages:");
                String title = scn.nextLine();
                String author = scn.nextLine();
                int publicationYear = scn.nextInt();
                int nbOfPages = scn.nextInt();
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublicationYear(publicationYear);
                book.setNbOfPages(nbOfPages);
                return book;
            }
        }
        return null;
    }

    private static Journals change(Journals[] journals, String itemID) {
        Scanner scn = new Scanner(System.in);
        for (Journals journal : journals) {
            if (journal != null && journal.getID().equals(itemID)) {
                System.out.println("Enter the new title, author, publication year, and volume number:");
                String title = scn.nextLine();
                String author = scn.nextLine();
                int publicationYear = scn.nextInt();
                int volumeNumber = scn.nextInt();
                journal.setTitle(title);
                journal.setAuthor(author);
                journal.setPublicationYear(publicationYear);
                journal.setVolumeNumber(volumeNumber);
                return journal;
            }
        }
        return null;
    }

    private static Media change(Media[] media, String itemID) {
        Scanner scn = new Scanner(System.in);
        for (Media medium : media) {
            if (medium != null && medium.getID().equals(itemID)) {
                System.out.println("Enter the new title, author, publication year, and type:");
                String title = scn.nextLine();
                String author = scn.nextLine();
                int publicationYear = scn.nextInt();
                String type = scn.nextLine();
                medium.setTitle(title);
                medium.setAuthor(author);
                medium.setPublicationYear(publicationYear);
                medium.setType(type);
                return medium;
            }
        }
        return null;
    }

    private static void change(Items[] items, String itemID, Books[] books) {
        Books bookToChange = null;

        // Find the book in the books array
        for (Books book : books) {
            if (book != null && book.getID().equals(itemID)) {
                bookToChange = book;
                break;
            }
        }

        // If the book is not found, return
        if (bookToChange == null) {
            return;
        }

        // Find the item in the items array and update its information
        for (Items item : items) {
            if (item != null && item.getID().equals(itemID)) {
                item.setTitle(bookToChange.getTitle());
                item.setAuthor(bookToChange.getAuthor());
                item.setPublicationYear(bookToChange.getPublicationYear());
                if (item instanceof Books) {
                    ((Books) item).setNbOfPages(bookToChange.getNbOfPages());
                }
                break;
            }
        }
    }
    private static void change(Items[] items, String itemID, Journals[] journals) {
        Journals journalToChange = null;

        // Find the journal in the journals array
        for (Journals journal : journals) {
            if (journal != null && journal.getID().equals(itemID)) {
                journalToChange = journal;
                break;
            }
        }

        // If the journal is not found, return
        if (journalToChange == null) {
            return;
        }

        // Find the item in the items array and update its information
        for (Items item : items) {
            if (item != null && item.getID().equals(itemID)) {
                item.setTitle(journalToChange.getTitle());
                item.setAuthor(journalToChange.getAuthor());
                item.setPublicationYear(journalToChange.getPublicationYear());
                if (item instanceof Journals) {
                    ((Journals) item).setVolumeNumber(journalToChange.getVolumeNumber());
                }
                break;
            }
        }
    }

    private static void change(Items[] items, String itemID, Media[] media) {
        Media mediaToChange = null;

        // Find the media in the media array
        for (Media medium : media) {
            if (medium != null && medium.getID().equals(itemID)) {
                mediaToChange = medium;
                break;
            }
        }

        // If the media is not found, return
        if (mediaToChange == null) {
            return;
        }

        // Find the item in the items array and update its information
        for (Items item : items) {
            if (item != null && item.getID().equals(itemID)) {
                item.setTitle(mediaToChange.getTitle());
                item.setAuthor(mediaToChange.getAuthor());
                item.setPublicationYear(mediaToChange.getPublicationYear());
                if (item instanceof Media) {
                    ((Media) item).setType(mediaToChange.getType());
                }
                break;
            }
        }
    }

    public static void addClient(Client[] clientM) {
        int index = getFirstNullIndex(clientM);
        if (index == -1) {
            System.out.println("No space left to add more clients.");
            return;
        }
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the client's name, phone number, and email:");
        String name = scn.nextLine();
        long phoneNumber = scn.nextLong();
        scn.nextLine();
        String email = scn.nextLine();
        Client client = new Client(name, phoneNumber, email);
        clientM[index] = client;

        for(Client clientC: clientM){
            if(clientC != null){
                System.out.println(clientC.toString());
            }
        }
    }

    public static void editClient(Client[] clientArray, String clientID){
        if(clientArray == null){
            System.out.println("No clients to edit");
            return;
        }

        for(Client clientB : clientArray){
            if (clientB != null && clientB.getID().equals(clientID)){
                Scanner scn = new Scanner(System.in);
                System.out.println("Enter the new name, phone number, and email:");
                String name = scn.nextLine();
                long phoneNumber = scn.nextLong();
                scn.nextLine();
                String email = scn.nextLine();
                clientB.setEmail(email);
                clientB.setName(name);
                clientB.setPhoneNumber(phoneNumber);
                return;
            }
        }
    }

    private static Client[] deleteClient(Client[] clientMenu, String clientID) {
        int nullIndex = -1;
        for (int i = 0; i < clientMenu.length; i++) {
            if (clientMenu[i] != null && clientMenu[i].getID().equals(clientID)) {
                clientMenu[i] = null;
                nullIndex = i;
                break;
            }
        }
        if (nullIndex != -1) {
            Client[] newClientMenu = new Client[clientMenu.length - 1];
            for (int i = 0, j = 0; i < clientMenu.length; i++) {
                if (i != nullIndex) {
                    newClientMenu[j++] = clientMenu[i];
                }
            }
            return newClientMenu;
        }
        return clientMenu;
    }

    private static void leaseItem(int clientID, String itemID, Client[] clientMenu, Items[] itemsMenu){
        // Find the client in the client array
        String clientIdString = Integer.toString(clientID);

        for (Client client : clientMenu) {
            if (client != null && client.getID().equals(clientIdString)) {
                // Find the item in the items array
                for (Items item : itemsMenu) {
                    if (item != null && item.getID().equals(itemID)) {
                        // Add the item to the client's items array
                        int index = getFirstNullIndex(client.getItems());
                        if (index != -1) {
                            client.getItems()[index] = item;
                            System.out.println("The item has been leased to the client.");
                        } else {
                            System.out.println("The client has leased the maximum number of items.");
                        }
                        return;
                    }
                }
                System.out.println("The item does not exist.");
                return;
            }
        }
        System.out.println("The client does not exist.");
    }

    private static void returnItem(int clientID, String itemID, Client[] clientMenu, Items[] itemsMenu) {
        // Find the client in the client array
        String clientIdString = Integer.toString(clientID);

        for (Client client : clientMenu) {
            if (client != null && client.getID().equals(clientIdString)) {
                // Find the item in the client's items array
                for (int i = 0; i < client.getItems().length; i++) {
                    if (client.getItems()[i] != null && client.getItems()[i].getID().equals(itemID)) {
                        // Create a new items array without the returned item
                        Items[] newItems = new Items[client.getItems().length - 1];
                        for (int j = 0, k = 0; j < client.getItems().length; j++) {
                            if (j != i) {
                                newItems[k++] = client.getItems()[j];
                            }
                        }
                        // Set the client's items array to the new array
                        client.setItems(newItems);
                        System.out.println("The item has been returned.");
                        return;
                    }
                }
                System.out.println("The client has not leased the item.");
                return;
            }
        }
        System.out.println("The client does not exist.");
    }

    private static Items[][] getItemsLeasedByAllClients(Client[] clientMenu, Items[] itemsMenu) {
        Items[][] itemsLeasedByAllClients = new Items[clientMenu.length][itemsMenu.length];
        for (int i = 0; i < clientMenu.length; i++) {
            if (clientMenu[i] != null) {
                itemsLeasedByAllClients[i] = clientMenu[i].getItems();
            }
        }
        return itemsLeasedByAllClients;
    }

    private static Items[] getItemsLeasedByClient(int clientId, Client[] clientMenu, Items[] itemsMenu) {
        for (Client client : clientMenu) {
            if (client != null && client.getID().equals(String.valueOf(clientId))) {
                if (client.getItems() == null || client.getItems().length == 0) {
                    return null;
                }
                return client.getItems();
            }
        }
        return null;
    }

    public static int getFirstNullIndex(Items[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                return i;
            }
        }
        return -1;
    }
    public static int getFirstNullIndex(Books[] books) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static int getFirstNullIndex(Journals[] journals) {
        for (int i = 0; i < journals.length; i++) {
            if (journals[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static int getFirstNullIndex(Media[] media) {
        for (int i = 0; i < media.length; i++) {
            if (media[i] == null) {
                return i;
            }
        }
        return -1;
    }
    public static int getFirstNullIndex(Client[] client) {
        for (int i = 0; i < client.length; i++) {
            if (client[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
