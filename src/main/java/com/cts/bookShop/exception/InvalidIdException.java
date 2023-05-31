package com.cts.bookShop.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class InvalidIdException extends RuntimeException{
    private static final long serialVersionUID=1L;
    private  String errorCode;
    private String errorMessage;

    public InvalidIdException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public InvalidIdException(){

    }

    public InvalidIdException(String productIdNotFound) {
    }
}
