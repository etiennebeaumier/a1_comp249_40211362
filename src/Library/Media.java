//-----------------------------------------
// Assignment 1
// Part 2
// Written by: Étienne Beaumier, 40211362
//-----------------------------------------

/*
Media class: This class, which extends the Items class, represents a media item with a specific type. It has a static
counter that increments each time a new media object is created, providing a unique ID for each media item. It includes
constructors, getters, setters, and methods to represent the media item as a string and to compare two media items.
 */

package Library;

public class Media extends Items {

    // Counter to generate unique IDs
    private static int counter;
    // Type of the media
    private String type;

    // Default constructor
    public Media() {
        // Call the constructor of the superclass
        super();
        // Increment the counter
        counter++;
        // Set the type to "NA"
        this.type = "NA";
        // Set the ID with a prefix "M"
        setID("M" + counter);
    }

    // Constructor with parameters
    public Media(String title, String author, int publicationYear, String type) {
        // Call the constructor of the superclass with parameters
        super(title, author, publicationYear);
        // Increment the counter
        counter++;
        // Set the type
        this.type = type;
        // Set the ID with a prefix "M"
        setID("M" + counter);
    }

    // Copy constructor
    public Media(Media media) {
        // Call the constructor of the superclass with parameters
        super(media.getTitle(), media.getAuthor(), media.getPublicationYear());
        // Increment the counter
        counter++;
        // Copy the type
        this.type = media.type;
        // Set the ID with a prefix "M"
        setID("M" + counter);
    }

    // Getter and setter for the type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Method to represent the media as a string
    @Override
    public String toString() {
        return "Media " + "ID='" + getID() + '\'' +
                ", the title is '" + getTitle() + '\'' +
                ", the author is'" + getAuthor() + '\'' +
                ", the publication year is " + getPublicationYear() +
                ", and the type is " + type;
    }

    // Method to compare two media
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        } else if (getClass() != otherObject.getClass()) {
            return false;
        } else {
            Media otherMedia = (Media) otherObject;
            return (getTitle().equals(otherMedia.getTitle())
                    && getAuthor().equals(otherMedia.getAuthor())
                    && getPublicationYear() == otherMedia.getPublicationYear()
                    && type.equals(otherMedia.type));
        }
    }
}