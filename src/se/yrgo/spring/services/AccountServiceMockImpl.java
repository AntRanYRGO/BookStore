package se.yrgo.spring.services;

import se.yrgo.spring.data.CustomerCreditExceededException;
import se.yrgo.spring.domain.Book;

public class AccountServiceMockImpl implements AccountService
{
    public void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException
    {
        // a very basic implementation for testing
        System.out.println("Raised the invoice for " + requiredBook);
//        throw new CustomerCreditExceededException();
    }
}