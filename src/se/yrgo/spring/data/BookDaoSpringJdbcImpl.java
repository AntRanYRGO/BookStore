package se.yrgo.spring.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import se.yrgo.spring.domain.Book;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
public class BookDaoSpringJdbcImpl implements BookDao{
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_BOOK_SQL =
            "INSERT INTO BOOK(ISBN, TITLE, AUTHOR, PRICE) VALUES (?, ?, ?, ?)";

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE BOOK(ISBN VARCHAR(20),TITLE VARCHAR(50),AUTHOR VARCHAR(50), PRICE DOUBLE)";
    private static final String GET_ALL_BOOKS_SQL = "SELECT * FROM BOOK";


    @Autowired
    public BookDaoSpringJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

    }
    @PostConstruct
    private void createTables(){
        try{
            jdbcTemplate.update(CREATE_TABLE_SQL);
        }catch (Exception e){
            System.err.println("Table already exist");
        }
    }

    @Override
    public List<Book> allBooks() {
        return jdbcTemplate.query(GET_ALL_BOOKS_SQL, new BookMapper());
    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT DISTINCT * FROM BOOK WHERE ISBN = ?", new BookMapper(), isbn);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException();
        }
    }

    @Override
    public void create(Book newBook) {
        jdbcTemplate.update(INSERT_BOOK_SQL, newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPrice());
    }

    @Override
    public void delete(Book redundantBook) {
        jdbcTemplate.update("DELETE FROM BOOK WHERE ISBN = ?", redundantBook.getIsbn());
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE AUTHOR = ?",
                new BookMapper(), author);
    }

    class BookMapper implements RowMapper<Book>{
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            String isbn = rs.getString("ISBN");
            String title = rs.getString("TITLE");
            String author = rs.getString("AUTHOR");
            Double price = rs.getDouble("PRICE");
            return new Book(isbn, title, author, price);
        }
    }

}
