package com.tide.common.exception;

public abstract class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
