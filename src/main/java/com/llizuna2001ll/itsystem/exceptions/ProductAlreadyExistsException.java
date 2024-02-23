package com.llizuna2001ll.itsystem.exceptions;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException() {
        super("Category Not Found");
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    public ProductAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
