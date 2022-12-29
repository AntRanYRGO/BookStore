package se.yrgo.spring.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.domain.Book;
@Transactional(propagation= Propagation.REQUIRES_NEW)
public class PurchasingServiceImpl implements PurchasingService{

    private AccountService accounts;
    private BookService books;

    public PurchasingServiceImpl(BookServiceProductionImpl bookServiceProduction,
                                 AccountServiceMockImpl accountServiceMock) {
        this.books = bookServiceProduction;
        this.accounts = accountServiceMock;
    }

    public void setAccountService(AccountService accounts){
        this.accounts = accounts;
    }

    public void setBookService(BookService books){
        this.books = books;
    }

    @Override
    public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException {
        Book book = books.getBookByIsbn(isbn);
        books.deleteFromStock(book);


       try {
            accounts.raiseInvoice(book);
       }catch(CustomerCreditExceededException e){
           //Tell Spring to rollback
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           //Throw the exception back to the client
           throw e;
       }
    }
}
