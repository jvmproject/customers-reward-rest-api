package com.customer.customersrewardrestapi.service;

import com.customer.customersrewardrestapi.entity.Customer;
import com.customer.customersrewardrestapi.exception.BadRequestException;
import com.customer.customersrewardrestapi.repositery.CustomerRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomersRewardServiceImplTest {

    @InjectMocks
    CustomersRewardServiceImpl customersRewardServiceImplTest;
    @Mock
    CustomerRepo repo;



    @DisplayName("test for get customer when you pass id")
    @Test
    public void get_customer_when_you_pass_id_success() throws Exception {

        Customer customer1 = new Customer(1l, "vishnu", null, 1l, 1d);
        Customer customer2 = new Customer(1l, "vishnu", null, 1l, 1d);
        Customer customer3 = new Customer(1l, "vishnu", null, 1l, 1d);

        List<Customer> customersList = Arrays.asList(customer1, customer2, customer3);

        when(repo.findAll()).thenReturn(customersList);

        List<Customer> result = customersRewardServiceImplTest.getAllCustomers();

        assertThat(result.get(0).getName()).isEqualTo("vishnu");
    }

    @DisplayName("get customer when you pass wrong id fail  (negative scenario)")
    @Test
    public void get_customer_when_you_pass_wrong_id_fail() throws Exception {

        when(repo.getById(0l)).thenThrow(new BadRequestException());

        Exception exception = assertThrows(BadRequestException.class, () -> {
            customersRewardServiceImplTest.getCustomerById(0l);
        });

        String expectedMessage = "invalid customer id provided.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}