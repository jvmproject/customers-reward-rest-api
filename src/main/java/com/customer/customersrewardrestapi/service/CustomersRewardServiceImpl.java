package com.customer.customersrewardrestapi.service;

import com.customer.customersrewardrestapi.entity.Customer;
import com.customer.customersrewardrestapi.entity.Transaction;
import com.customer.customersrewardrestapi.exception.BadRequestException;
import com.customer.customersrewardrestapi.exception.NotFoundException;
import com.customer.customersrewardrestapi.repositery.CustomerRepo;
import com.customer.customersrewardrestapi.repositery.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class CustomersRewardServiceImpl {

    @Autowired
    CustomerRepo repo;

    @Autowired
    TransactionRepo transactionRepo;


    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }


    public Customer getCustomerById(Long id) throws Exception {
        if (id == 0 || id == null) {
            throw new BadRequestException("invalid customer id provided.");
        } else {
            Optional<Customer> custObjOptional = repo.findById(id);
            if (custObjOptional.isPresent()) {
                return repo.findById(id).get();
            } else {
                throw new NotFoundException("customer data not found");
            }
        }
    }


    public Customer addCustomer(Customer cust) {
        if (cust != null) {
            if (!CollectionUtils.isEmpty(cust.getTransactions())) {
                throw new BadRequestException("you can not add transactions while adding the customer.");
            }
            return repo.save(cust);
        } else {
            throw new BadRequestException("invalid customer data provided.");
        }

    }


    public Customer getCustomerPointByMonth(Long id, Integer bymonth) {
        if ((id == 0 || id == null) && ((bymonth == 0 || bymonth == null))) {
            throw new BadRequestException("invalid customer id and month number provided.");
        } else {
            Customer cust = null;
            Optional<Customer> listC = repo.findById(id);
            if (listC.isPresent()) {

                Date dd = new Date();
                Calendar cc = Calendar.getInstance();
                cc.setTime(dd);
                cc.add(Calendar.MONTH, -bymonth);
                Date lastMonth = cc.getTime();
                LocalDateTime startDate = convertToLocalDateTimeViaInstant(lastMonth);
                LocalDateTime endDate = LocalDateTime.now();

                System.out.println(startDate);
                System.out.println(endDate);

                cust = listC.get();

                Set<Transaction> transactionList = transactionRepo.getCustomerPointByMonth(cust.getCid(), startDate, endDate);

                cust.setTransactions(transactionList);
            } else {
                throw new NotFoundException("customer data not found");
            }

            return cust;
        }
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
