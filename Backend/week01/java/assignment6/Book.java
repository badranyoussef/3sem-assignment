package assignment6;

import utility.Print;
import utility.Printer;

import java.util.*;
import java.util.stream.Collectors;


public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double rating;
    private int pages;

    public Book(String title, String author, int publicationYear, double rating, int pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rating = rating;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public double getRating() {
        return rating;
    }

    public int getPages() {
        return pages;
    }


}

class StreamProcessing {
    public static void main(String[] args) {
        // Create a list of books
        List<Book> listOfBooks = List.of(
                new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, 4.5, 354),
        new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, 4.7, 1226),
        new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997, 4.9, 317),
        new Book("The Hunger Games", "Suzanne Collins", 2008, 4.6, 384),
        new Book("1984", "George Orwell", 1949, 4.4, 328),
        new Book("Catch-22", "Joseph Heller", 1961, 4.2, 447),
        new Book("To Kill a Mockingbird", "Harper Lee", 1960, 4.8, 386),
        new Book("The Great Gatsby1", "F. Scott Fitzgerald", 1925, 3.9, 180),
        new Book("The Great Gatsby2", "F. Scott Fitzgerald", 1925, 2.1, 180),
        new Book("The Great Gatsby3", "F. Scott Fitzgerald", 1925, 4.7, 180),
        new Book("The Great Gatsby4", "F. Scott Fitzgerald", 1925, 5.098, 180),
        new Book("The Great Gatsby5", "F. Scott Fitzgerald", 1925, 4.2, 180),
        new Book("Pride and Prejudice", "Jane Austen", 1813, 4.5, 392)
    );

        // Calculate the average rating of all books
        OptionalDouble averageRating = listOfBooks.stream().mapToDouble(Book::getRating).average();
        System.out.println(averageRating.getAsDouble());
        Print.aBreak();

        // Filter and display books published after a specific year
        listOfBooks.stream().filter(book -> book.getPublicationYear() < 1970)
                .sorted(Comparator.comparing(Book::getPublicationYear))
                .map(Book::getTitle)
                .forEach(System.out::println);
        Print.aBreak();

        // Sort books by rating in descending order
        listOfBooks.stream().sorted(Comparator.comparing(Book::getRating).reversed()).map(book -> book).forEach( book -> {
            System.out.println(book.getTitle() + " - " + book.getRating());
        });
        Print.aBreak();


        // Find and display the title of the highest-rated book
        Optional<Book> bestRatedBook = listOfBooks.stream().max(Comparator.comparing(Book::getRating));

        Printer.text("The best rated book is:");
        Printer.result(bestRatedBook.get().getTitle() + bestRatedBook.get().getRating());

        // Group books by author and calculate average rating for each author
        Map<String, Double> ratings = listOfBooks.stream()
                                                //KEY               VALUE
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.averagingDouble(Book::getRating)));

        ratings.forEach((author, rating) -> Printer.text(author + " - avg rating:" + rating));
        Printer.aBreak();

        // Calculate the total number of pages for all books

        int totalpages = listOfBooks.stream().mapToInt(Book::getPages).sum();
        Printer.result(totalpages);
    }
}
