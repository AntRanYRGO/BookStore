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

		BookService bookService = container.getBean(BookService.class);
		bookService.registerNewBook(new Book("1234596896812", "Birds",
				"Bird Lover", 100.00));
		List<Book> allBooks = bookService.getEntireCatalogue();
		for (Book book:allBooks) {
		System.out.println(book);
	}

		try {
			Book findBook=bookService.getBookByIsbn("1234596896812");
			System.out.println(findBook);
		}catch (BookNotFoundException e) {
			System.err.println("Sorry, that book does not exist");
		}
		container.close();

	}
}
