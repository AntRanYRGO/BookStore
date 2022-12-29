package se.yrgo.spring.data;

import java.util.List;

import se.yrgo.spring.domain.Book;

public interface BookDao {
    List<Book> allBooks();
    Book findByIsbn(String isbn) throws BookNotFoundException;
    void create(Book newBook);
    void delete(Book redundantBook);
    List<Book> findBooksByAuthor(String author);
}