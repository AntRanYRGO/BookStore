package se.yrgo.spring.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.yrgo.spring.domain.Book;

@Service("accountService")
public class AccountServiceMockImpl implements AccountService {
    public void raiseInvoice(Book requiredBook) throws CustomerCreditExceededException {

        System.out.println("Raised the invoice for " + requiredBook);
//        throw new CustomerCreditExceededException();
    }
}