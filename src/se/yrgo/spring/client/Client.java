package se.yrgo.spring.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.services.BookService;
import se.yrgo.spring.services.PurchasingService;

import java.util.List;

public class Client {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext container
				= new ClassPathXmlApplicationContext("application.xml");

		try {
			PurchasingService purchasingService = container.getBean(PurchasingService.class);
			BookService bookService = container.getBean(BookService.class);
			bookService.registerNewBook(new Book("0123456789", "Spring" , "Author", 20.00) );
			try {
				purchasingService.buyBook("0123456789");
			} catch (BookNotFoundException e) {
				System.err.println("Sorry, this book doesn't exist.");
			}
		} finally {
			container.close();
		}
	}
}
