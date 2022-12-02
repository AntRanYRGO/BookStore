package se.yrgo.spring.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.services.BookService;
import se.yrgo.spring.services.PurchasingService;

import java.util.List;

public class Client {
	public static void main(String[] args){
		System.out.println("Testing buying book...");
		String isbn = "ISBN1";

		ClassPathXmlApplicationContext container
				= new ClassPathXmlApplicationContext("application.xml");

		PurchasingService service = container.getBean(PurchasingService.class);

		service.buyBook(isbn);
		container.close();

	}
}
