package se.yrgo.spring.services;

import org.springframework.transaction.annotation.Transactional;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

@Transactional
public class PurchasingServiceImpl implements PurchasingService{

    private AccountService accounts;
    private BookService books;

    public void setAccountService(AccountService accounts){
        this.accounts = accounts;
    }

    public void setBookService(BookService books){
        this.books = books;
    }

    @Override
    public void buyBook(String isbn) throws BookNotFoundException {
        Book book = books.getBookByIsbn(isbn);
        accounts.raiseInvoice(book);
    }
}
