package se.yrgo.spring.services;

import se.yrgo.spring.domain.Book;

public class PurchasingServiceImpl implements PurchasingService{
    private AccountService accounts;
    private BookService books;

    public void setAccounts(AccountService accounts){
        this.accounts = accounts;
    }

    public void setBooks(BookService books){
        this.books = books;
    }

    @Override
    public void buyBook(String isbn) {
        Book book = books.getBookByIsbn(isbn);
        accounts.raiseInvoice(book);
    }
}
