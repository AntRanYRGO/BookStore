package se.yrgo.spring.data;


import org.springframework.jdbc.core.JdbcTemplate;
import se.yrgo.spring.domain.Book;

import java.util.List;

public class BookDaoSpringJdbcImpl implements BookDao{
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_BOOK_SQL =
            "INSERT INTO BOOK(ISBN, TITLE, AUTHOR, PRICE)" +
                    "VALUES (?, ?, ?, ?)";

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE BOOK(ISBN VARCHAR(20),TITLE VARCHAR(50),AUTHOR VARCHAR(50), PRICE DOUBLE)";


    public BookDaoSpringJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Book> allBooks() {
        return null;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return null;
    }

    @Override
    public void create(Book newBook) {
        jdbcTemplate.update(INSERT_BOOK_SQL, newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPrice());
    }

    @Override
    public void delete(Book redundantBook) {
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return null;
    }
}
