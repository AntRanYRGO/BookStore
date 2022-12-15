package se.yrgo.spring.services;

import se.yrgo.spring.data.BookDao;
import se.yrgo.spring.domain.Book;

import java.util.List;

public class BookServiceProductionImpl implements BookService{
    private BookDao dao;
    public BookServiceProductionImpl(BookDao dao){
        this.dao = dao;
    }

    @Override
    public List<Book> getAllBooksByAuthor(String author) {
        return dao.findBooksByAuthor(author);
    }

    @Override
    public List<Book> getAllRecommendedBooks(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return dao.findByIsbn(isbn);
    }

    @Override
    public List<Book> getEntireCatalogue() {
        return dao.allBooks();
    }

    @Override
    public void registerNewBook(Book newBook) {
        dao.create(newBook);

    }
}
