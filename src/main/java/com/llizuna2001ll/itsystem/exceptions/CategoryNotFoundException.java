package com.llizuna2001ll.itsystem.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super("Category Not Found");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
