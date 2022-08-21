package com.customer.customersrewardrestapi.controller;

import com.customer.customersrewardrestapi.entity.Transaction;
import com.customer.customersrewardrestapi.service.TrasactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/transaction")
public class TrasactionController {

    @Autowired
    TrasactionServiceImpl serviceImpl;

    @PostMapping("/customerid/{cid}")
    public ResponseEntity<Transaction> addCustomerTrasaction(@RequestBody @Valid Transaction transaction, @PathVariable @Valid @NotNull Long cid) {
        return new ResponseEntity<> (serviceImpl.addCustomerTrasaction(transaction, cid), HttpStatus.OK);
    }
}