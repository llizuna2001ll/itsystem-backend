package com.llizuna2001ll.itsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameOrPasswordIncorrectException.class)
    public ResponseEntity<?> handleUsernameOrPasswordIncorrectException(UsernameOrPasswordIncorrectException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not found");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not found");
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<?> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Not found");
    }
}