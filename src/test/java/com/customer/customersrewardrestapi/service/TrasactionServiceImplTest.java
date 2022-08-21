package com.customer.customersrewardrestapi.service;

import com.customer.customersrewardrestapi.entity.Transaction;
import com.customer.customersrewardrestapi.exception.NotFoundException;
import com.customer.customersrewardrestapi.repositery.CustomerRepo;
import com.customer.customersrewardrestapi.repositery.TransactionRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TrasactionServiceImplTest {

    @InjectMocks
    private TrasactionServiceImpl getServiceImpl;

    @Mock
    CustomerRepo customerRepo;

    @Mock
    TransactionRepo transactionRepo;


    @DisplayName("for test addCustomer Trasaction when id wrong method ")
    @Test
    void addCustomerTrasaction() {



        LocalDateTime ltd = LocalDateTime.now();
        Transaction transaction = new Transaction();
        transaction.setTotal(10d);
        transaction.setDateTime(ltd);
        transaction.setDescription("xyz");
        Set<Transaction> s =new HashSet<>();
        s.add(transaction);

        when(customerRepo.findById(0l)).thenThrow(NotFoundException.class);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            getServiceImpl.addCustomerTrasaction(transaction, 1l);
        });

        String expectedMessage = "customer id not found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}