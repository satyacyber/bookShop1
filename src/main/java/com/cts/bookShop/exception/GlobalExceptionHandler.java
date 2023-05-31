package com.cts.bookShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleInvalidId(InvalidIdException invalidIdException){
        return new ResponseEntity<String>("Invalid ID,Please Use Correct ID", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResponseNotFound(ResourceNotFoundException resourceNotFoundException){
        return new ResponseEntity<String>("Resource not found",HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handelNumberFormatException(NumberFormatException exp){
        String msg=exp.getMessage();
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }


}
