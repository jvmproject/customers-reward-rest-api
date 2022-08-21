package com.customer.customersrewardrestapi.service;


import com.customer.customersrewardrestapi.entity.Customer;
import com.customer.customersrewardrestapi.entity.Transaction;
import com.customer.customersrewardrestapi.exception.NotFoundException;
import com.customer.customersrewardrestapi.repositery.CustomerRepo;
import com.customer.customersrewardrestapi.repositery.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class TrasactionServiceImpl {


    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    TransactionRepo transactionRepo;


    public Transaction addCustomerTrasaction(Transaction transaction, Long cid) {
        Transaction trans = null;
        if (cid == null || cid == 0) {
            throw new NotFoundException("customer id not found.");
        } else {
            if (transaction != null && transaction.getTotal() > 0) {

                Optional<Customer> customerRepoById = customerRepo.findById(cid);
                if (customerRepoById.isPresent()) {
                    transaction.setCustomer(customerRepoById.get());
                    transaction.setDateTime(LocalDateTime.now());
                    trans = transactionRepo.save(transaction);
                } else {
                    throw new NotFoundException("customer id not found.");
                }
            }
        }
        return trans;
    }
}
