package se.yrgo.spring.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.yrgo.spring.domain.Book;

public class BookServiceMockImpl implements BookService{
    private Map<String, Book> testBooks = new HashMap<String, Book>();

    public BookServiceMockImpl() {
        Book book1 = new Book("ISBN1", "Forecasting Rain", "Phil Don", 10.99);
        Book book2 = new Book("ISBN2", "The Tulip", "Anna Pavord", 14.99);
        Book book3 = new Book("ISBN3", "Enough", "Bill McKibben", 16.99);
        testBooks.put("ISBN1", book1);
        testBooks.put("ISBN2", book2);
        testBooks.put("ISBN3", book3);
    }

	public Book getBookByIsbn(String isbn) {
		return testBooks.get(isbn);
	}

	public List<Book> getEntireCatalogue() 	{
		return new ArrayList<Book>(testBooks.values());
	}

	public void registerNewBook(Book newBook) {
		testBooks.put(newBook.getIsbn(), newBook);
	}

	public List<Book> getAllBooksByAuthor(String author) {
		return null;
	}

	public List<Book> getAllRecommendedBooks(String userId) {
		return null;
	}
}
