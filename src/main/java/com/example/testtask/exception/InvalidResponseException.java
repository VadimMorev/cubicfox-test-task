package com.example.testtask.exception;

public class InvalidResponseException extends RuntimeException{
    public InvalidResponseException(final String message) {
        super(message);
    }
}
