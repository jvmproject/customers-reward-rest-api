package com.customer.customersrewardrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String url;
    private int code;
    private List<ErrorDetails> errors;
}
