package com.Adepu.Product.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(CategoryNotFoundException.class)
//    public ResponseEntity<String> handleCategoryNotFoundException(Exception e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
//    @ExceptionHandler(OrderNotFoundException.class)
//    public ResponseEntity<String> OrderNotFoundException(Exception e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(StripeException.class)
//    public ResponseEntity<String> handleStripeException(Exception e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
