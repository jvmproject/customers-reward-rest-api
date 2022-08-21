package com.customer.customersrewardrestapi.repositery;

import com.customer.customersrewardrestapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Set;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT t FROM Transaction t where t.customer.cid =?1 and t.dateTime between ?2 AND ?3")
    Set<Transaction> getCustomerPointByMonth(Long cid, LocalDateTime endDate, LocalDateTime startDate);
}
