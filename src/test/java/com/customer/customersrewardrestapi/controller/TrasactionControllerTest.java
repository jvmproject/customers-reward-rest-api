package com.customer.customersrewardrestapi.controller;

import com.customer.customersrewardrestapi.entity.Transaction;
import com.customer.customersrewardrestapi.exception.CustomerExceptionHandler;
import com.customer.customersrewardrestapi.service.TrasactionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TrasactionControllerTest {


    @InjectMocks
    TrasactionController trasactionController;

    @InjectMocks
    private CustomerExceptionHandler customerExceptionHandler;

    @Mock
    TrasactionServiceImpl serviceImpl;


    @DisplayName("test for test addCustomerTrasaction method ")
    @Test
    public void test_addCustomerTrasaction() {

        LocalDateTime ltd = LocalDateTime.now();
        Transaction transaction = new Transaction();
        transaction.setTotal(10d);
        transaction.setDateTime(ltd);
        transaction.setDescription("xyz");

        when(serviceImpl.addCustomerTrasaction(transaction, 1l)).thenReturn(transaction);

        ResponseEntity<Transaction> result =trasactionController.addCustomerTrasaction(transaction, 1l);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getTotal()).isEqualTo(10d);

    }

}