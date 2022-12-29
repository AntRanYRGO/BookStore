package se.yrgo.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service("purchasingService")
public class PurchasingServiceImpl implements PurchasingService {

    @Autowired
    private AccountService accounts;
    @Autowired
    private BookService books;

    public void setAccountService(AccountService accounts) {
        this.accounts = accounts;
    }

    public void setBookService(BookService books) {
        this.books = books;
    }

    @Override
    @Transactional(rollbackFor = {CustomerCreditExceededException.class, BookNotFoundException.class})
    public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException {
        Book book = books.getBookByIsbn(isbn);
        books.deleteFromStock(book);
        accounts.raiseInvoice(book);
    }
}
