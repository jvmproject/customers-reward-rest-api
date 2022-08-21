package com.customer.customersrewardrestapi.controller;

import com.customer.customersrewardrestapi.entity.Customer;
import com.customer.customersrewardrestapi.service.CustomersRewardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomersRewardController {

    @Autowired
    CustomersRewardServiceImpl  serviceImpl;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(serviceImpl.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@Valid @NotNull @Range(min = 1, max = 999999999) @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(serviceImpl.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer cust) {
        return new ResponseEntity<>(serviceImpl.addCustomer(cust), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/bymonth/{monthNumber}")
    public ResponseEntity<Customer> getCustomerPointsByMonth(@Valid @NotNull @Range(min = 1, max = 999999999) @PathVariable Long id, @Valid @NotNull @Range(min = 1, max = 12) @PathVariable int monthNumber) {
        return new ResponseEntity<>(serviceImpl.getCustomerPointByMonth(id, monthNumber), HttpStatus.OK);
    }
}