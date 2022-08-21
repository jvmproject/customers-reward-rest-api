package com.customer.customersrewardrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private LocalDate date;
    private String message ;
    private String errorDetails;
}
