package assortmentManagement;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private String description;

    public Book(int bookID, String title, String author, LocalDate publicationDate, String description) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookID == book.bookID && author == book.author && Objects.equals(title, book.title) && Objects.equals(publicationDate, book.publicationDate) && Objects.equals(description, book.description);
    }


    @Override
    public String toString() {
        return "Book: " +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", publicationDate='" + publicationDate + '\'' +
                ", description='" + description;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
