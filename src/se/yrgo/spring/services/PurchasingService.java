package se.yrgo.spring.services;

import se.yrgo.spring.data.BookNotFoundException;
import se.yrgo.spring.data.CustomerCreditExceededException;

public interface PurchasingService {

    public void buyBook(String isbn) throws BookNotFoundException, CustomerCreditExceededException;

}
