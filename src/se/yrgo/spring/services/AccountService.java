package se.yrgo.spring.services;

import se.yrgo.spring.domain.Book;

public interface AccountService
{
    void raiseInvoice(Book requiredBook)throws CustomerCreditExceededException;
}