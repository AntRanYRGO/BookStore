package se.yrgo.spring.services;

import se.yrgo.spring.domain.Book;

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
    public void buyBook(String isbn) {
        Book book = books.getBookByIsbn(isbn);
        accounts.raiseInvoice(book);
    }
}
