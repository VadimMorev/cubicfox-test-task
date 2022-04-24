package com.example.testtask.exception;

import static java.lang.String.format;

public class ResourceNotFoundException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "%s with identifier %s not found";

    public ResourceNotFoundException(final Class<?> type, final Object identifier) {
        super(format(MESSAGE_TEMPLATE, type.getSimpleName(), identifier));
    }
}
