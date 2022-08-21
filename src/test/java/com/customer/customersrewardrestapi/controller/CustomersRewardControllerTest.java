package com.customer.customersrewardrestapi.controller;

import com.customer.customersrewardrestapi.entity.Customer;
import com.customer.customersrewardrestapi.entity.Transaction;
import com.customer.customersrewardrestapi.service.CustomersRewardServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomersRewardControllerTest {


    @InjectMocks
    CustomersRewardController employeeController;

    @Mock
    CustomersRewardServiceImpl service;

    Customer customer1 = new Customer(1l, "vishnu", null, 1l, 1d);
    Customer customer2 = new Customer(1l, "vishnu", null, 1l, 1d);
    Customer customer3 = new Customer(1l, "vishnu", null, 1l, 1d);


    @DisplayName("JUnit test for getAllCustomers method ")
    @Test
    public void test_getAllCustomers_success() {

        List<Customer> customersList = Arrays.asList(customer1, customer2, customer3);

        when(service.getAllCustomers()).thenReturn(customersList);

        ResponseEntity<List<Customer>> result = employeeController.getAllCustomers();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().get(0).getName()).isEqualTo("vishnu");

    }

    @DisplayName("JUnit test for getCustomers byId method ")
    @Test
    public void test_getCustomer_success() throws Exception  {

        LocalDateTime ltd = LocalDateTime.now();
        Transaction t = new Transaction( );
        t.setDescription("xyz");
        t.setTotal(2d);
        t.setDateTime(ltd);

        Long id = 1l;

        Set<Transaction> tlist = new HashSet<>();

        Customer customer1 = new Customer(1l, "vishnu", tlist, 1l, 1d);

        when(service.getCustomerById(id)).thenReturn(customer1);

        ResponseEntity<Customer> result = employeeController.getCustomer(id);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo("vishnu");

    }

    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    public void getAllEmployees_fail(){

        when(service.getAllCustomers()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Customer>> customerList = employeeController.getAllCustomers();

        assertThat(customerList).isNotNull();
        assertThat(customerList.getBody().size()).isEqualTo(0);
    }


    @DisplayName("JUnit test for getEmployees method (negative scenario)")
    @Test
    public void getEmployees_fail() throws Exception{

        Customer c = new Customer();
        when(service.getCustomerById(1l)).thenReturn(c);

        ResponseEntity<Customer> customer = employeeController.getCustomer(1l);

        assertThat(customer).isNotNull();
        assertThat(customer.getBody()).isEqualTo(c);
    }



}