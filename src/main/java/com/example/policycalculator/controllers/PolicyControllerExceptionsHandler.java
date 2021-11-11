package com.example.policycalculator.controllers;

import com.example.policycalculator.exceptions.ElementNotFoundInDatabaseException;
import com.example.policycalculator.exceptions.PolicyStrategyLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PolicyControllerExceptionsHandler {

    @ExceptionHandler(ElementNotFoundInDatabaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String elementNotFound(ElementNotFoundInDatabaseException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PolicyStrategyLogicException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String policyStrategyLogicProblem(PolicyStrategyLogicException ex) {
        return ex.getMessage();
    }

}
