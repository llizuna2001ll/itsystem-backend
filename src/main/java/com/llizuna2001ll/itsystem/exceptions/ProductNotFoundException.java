package com.llizuna2001ll.itsystem.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product Not Found");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
}

