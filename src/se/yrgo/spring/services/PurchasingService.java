package se.yrgo.spring.services;

import se.yrgo.spring.data.BookNotFoundException;

public interface PurchasingService {

    public void buyBook(String isbn) throws BookNotFoundException;

}
