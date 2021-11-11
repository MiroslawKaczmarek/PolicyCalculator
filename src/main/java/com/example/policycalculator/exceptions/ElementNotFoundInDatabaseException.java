package com.example.policycalculator.exceptions;

public class ElementNotFoundInDatabaseException extends RuntimeException{
    public ElementNotFoundInDatabaseException(String message) {
        super(message);
    }
}
