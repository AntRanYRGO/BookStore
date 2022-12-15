package se.yrgo.spring.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import se.yrgo.spring.domain.Book;

public class BookDaoJdbcImpl implements BookDao{
    // For HSQLDB, username is "sa" (System Administrator) with no password
    private static final String PASSWORD = "";
    private static final String USERNAME = "sa";

    // SQL
    private static final String INSERT_BOOK_SQL = "insert into BOOK (ISBN, TITLE, AUTHOR,PRICE) values (?, ?, ?, ?) ";
    private static final String CREATE_TABLE_SQL = "create table BOOK(ISBN VARCHAR(20), TITLE VARCHAR(50), AUTHOR VARCHAR(50), PRICE DOUBLE)";
    private static final String GET_ALL_BOOKS_SQL = "select * from BOOK";

    // Driver and Database URL
    private static final String DRIVER_NAME = "org.hsqldb.jdbcDriver";
    private static final String DATABASE_URL = "jdbc:hsqldb:file:database.dat;shutdown=true";

    /**
     * Instantiates the DAO and if necessary creates the table in the database.
     */
    public BookDaoJdbcImpl(){
        try{
            Class.forName(DRIVER_NAME);
            createTables();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void createTables(){
        try {
            Connection con = null;
            try {
                con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                Statement stmt = con.createStatement();
                stmt.executeUpdate(CREATE_TABLE_SQL);
                System.out.println("Created new table BOOK");
            } finally {
                if (con!= null) con.close();
            }
        }catch (SQLException e){
            if (e.getErrorCode() == 134){
                System.out.println("Assuming that the BOOK table has already been created...");
            } else{
                // raise another error
            }
        }

    }

    public void create(Book newBook){
        try {
            Connection con = null;
            PreparedStatement insertBook = null;

            try {
                con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                insertBook = con.prepareStatement(INSERT_BOOK_SQL);
                insertBook.setString(1, newBook.getIsbn());
                insertBook.setString(2, newBook.getTitle());
                insertBook.setString(3, newBook.getAuthor());
                insertBook.setDouble(4, newBook.getPrice());
                insertBook.executeUpdate();
            }finally {
                if (insertBook != null)
                    insertBook.close();
                if (con != null)
                    con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Book redundantBook) {
        // TODO When I can face it!
        throw new java.lang.UnsupportedOperationException();
    }

    public List findBooksByAuthor(String author) {
        throw new java.lang.UnsupportedOperationException();
    }

    public Book findByIsbn(String isbn) {
        throw new java.lang.UnsupportedOperationException();
    }

    public List allBooks() {
        try {
            Connection con = null;
            PreparedStatement getBooks = null;
            List results = new ArrayList();
            ResultSet rs = null;

            try{
                con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                getBooks = con.prepareStatement(GET_ALL_BOOKS_SQL);
                rs = getBooks.executeQuery();
                while(rs.next()){
                    String isbn = rs.getString(1);
                    String title = rs.getString(2);
                    String author = rs.getString(3);
                    double price = rs.getDouble(4);
                    Book nextBook = new Book(isbn, title, author, price);
                    results.add(nextBook);
                }
                return results;
            }	finally	{
                if (rs != null)
                    rs.close();
                if (getBooks != null)
                    getBooks.close();
                if (con != null)
                    con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}