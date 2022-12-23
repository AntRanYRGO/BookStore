package se.yrgo.spring.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.data.CustomerCreditExceededException;
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
    @Transactional(rollbackFor = {CustomerCreditExceededException.class, BookNotFoundException.class})
    public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException {
        Book book = books.getBookByIsbn(isbn);
        books.deleteFromStock(book);
        try{
            accounts.raiseInvoice(book);
        }catch (CustomerCreditExceededException c){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw c;
        }
    }
}
