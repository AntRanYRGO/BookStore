package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

public class BookServiceTimingProxy implements BookService {

    private BookService originalBookService;

    public void setOriginalBookService (BookService original) {
        this.originalBookService= original;
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> getAllRecommendedBooks(String userId) {
        return null;
    }

    @Override
    public Book getBookByIsbn(String isbn) throws BookNotFoundException {
        //start the clock
        long timeThen = System.nanoTime();

        //do the work
        try {
            Book foundBook = originalBookService.getBookByIsbn(isbn);
            return foundBook;
        } finally {
            //stop the clock
            long timeNow = System.nanoTime();
            long timeTaken = timeNow - timeThen;
            System.out.println("getBookByIsbn took " + timeTaken /
                    1000000 + " milliseconds" );
        }
    }

    @Override
    public List<Book> getEntireCatalogue() {
        //start the clock
        long timeThen = System.nanoTime();

        //do the work
        List<Book> allBooks = originalBookService.getEntireCatalogue();

        //stop the clock
        long timeNow = System.nanoTime();
        long timeTaken = timeNow - timeThen;
        System.out.println("getEntireCatalogue took " + timeTaken /
                1000000 + " milliseconds" );
        return allBooks;
    }

    @Override
    public void registerNewBook(Book newBook) {
        //start the clock
        long timeThen = System.nanoTime();

        //do the work
        originalBookService.registerNewBook(newBook);

        //stop the clock
        long timeNow = System.nanoTime();
        long timeTaken = timeNow - timeThen;
        System.out.println("registerNewBook took " + timeTaken /
                1000000 + " milliseconds" );
    }
}