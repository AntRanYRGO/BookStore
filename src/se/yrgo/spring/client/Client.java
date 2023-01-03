package se.yrgo.spring.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.services.BookService;
import se.yrgo.spring.services.CustomerCreditExceededException;
import se.yrgo.spring.services.PurchasingService;

public class Client {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext container = new
                ClassPathXmlApplicationContext("application.xml");


        try {
            PurchasingService purchasing =
                    container.getBean(PurchasingService.class);
            BookService bookService =
                    container.getBean(BookService.class);

            bookService.registerNewBook(new Book("9876543232",
                    "Java", "JavaAuthor", 200.00));

            List<Book> allBooks = bookService.getEntireCatalogue();
            for (Book book:allBooks) {
                System.out.println(book);
            }
        } finally {
            container.close();
        }
    }
}