package com.customer.customersrewardrestapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer  implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "CID")
    private Long cid;

    @NotBlank(message = "name should not be blank!")
    @Column(name = "NAME")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    @Transient
    private Long rewardPoints;

    @Transient
    private Double totalAmount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
        for (Transaction b : transactions) {
            b.setCustomer(this);
        }
    }

    public Long getCid() {
        return cid;
    }

    public Long getRewardPoints() {

        if (transactions == null || transactions.isEmpty()) {
            return 0L;
        }

        return transactions.stream().map(trans -> trans.getPoints().longValue()).reduce(0L, (a, b) -> a + b).longValue();
    }

    public Double getTotalAmount() {
        if (transactions == null || transactions.isEmpty()) {
            return 0d;
        }

        return transactions.stream().map(trans -> trans.getTotal().doubleValue()).reduce(0d, (a, b) -> a + b).doubleValue();
    }
}