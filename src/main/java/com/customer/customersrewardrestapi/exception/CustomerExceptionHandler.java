package com.customer.customersrewardrestapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorDetails handleExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), "Exception");
        return errorDetails;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorDetails> method_argument_exception = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> new ErrorDetails(LocalDate.now(), "Method Argument Exception", error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(status.name(), status.value(), method_argument_exception);
        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), "Constraint Violation Exception", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse badRequestExceptionHandler(Exception ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), "Bad Request Exception", ex.getMessage());
        ErrorResponse error = new ErrorResponse(request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), Arrays.asList(errorDetails));
        return error;
    }


    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse notFoundExceptionHandler(Exception ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), "Customer Not Found Exception", ex.getMessage());
        ErrorResponse error = new ErrorResponse(request.getRequestURI(), HttpStatus.NOT_FOUND.value(), Arrays.asList(errorDetails));
        return error;
    }

    @ExceptionHandler({ServersideException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse internalServerExceptionHandler(Exception ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), "Internal Server Exception", ex.getMessage());
        ErrorResponse error = new ErrorResponse(request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Arrays.asList(errorDetails));
        return error;
    }
}
