package com.wasu.springboot.integration.exceptions;

public class BaseCoreException extends RuntimeException {
    private static final long serialVersionUID=-1L;

    public BaseCoreException(){
        super();
    }

    public BaseCoreException(String message){
        super(message);
    }

    public BaseCoreException(String message,Exception cause){
        super(message,cause);
    }

    public BaseCoreException(Exception cause){
        super(cause);
    }
}
