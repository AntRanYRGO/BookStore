package se.yrgo.spring.services;

import java.util.List;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

public interface BookService {
    public List<Book> getAllBooksByAuthor(String author);

    public List<Book> getAllRecommendedBooks(String userId);

    public Book getBookByIsbn(String isbn) throws BookNotFoundException;

    public List<Book> getEntireCatalogue();

    public void registerNewBook(Book newBook);

    public void deleteFromStock(Book oldBook);
}
