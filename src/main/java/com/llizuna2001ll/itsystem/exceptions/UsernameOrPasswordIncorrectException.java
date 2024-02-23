package com.llizuna2001ll.itsystem.exceptions;

public class UsernameOrPasswordIncorrectException extends RuntimeException{
    public UsernameOrPasswordIncorrectException() {
        super("");
    }

    public UsernameOrPasswordIncorrectException(String message) {
        super(message);
    }

    public UsernameOrPasswordIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameOrPasswordIncorrectException(Throwable cause) {
        super(cause);
    }
}
