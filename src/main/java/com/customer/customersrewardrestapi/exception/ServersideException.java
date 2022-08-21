package com.customer.customersrewardrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "internal server error")
public class ServersideException extends RuntimeException {
    public ServersideException() {
    }

    public ServersideException(String message) {
        super(message);
    }

    public ServersideException(String message, Throwable cause) {
        super(message, cause);
    }
}