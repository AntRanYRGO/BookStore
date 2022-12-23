package se.yrgo.spring.services;

import se.yrgo.spring.data.CustomerCreditExceededException;
import se.yrgo.spring.domain.Book;

public interface AccountService
{
    public void raiseInvoice(Book requiredBook)throws CustomerCreditExceededException;
}