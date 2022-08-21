package com.customer.customersrewardrestapi.service;

import com.customer.customersrewardrestapi.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomersRewardService {

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(Long id);

    public Customer addCustomer(Customer cust);


}
