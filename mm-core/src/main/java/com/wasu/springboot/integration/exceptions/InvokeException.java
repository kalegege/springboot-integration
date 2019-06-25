package com.wasu.springboot.integration.exceptions;

public class InvokeException extends RuntimeException {
    private static final long serialVersionUID =1L;

    protected String msg;

    protected int code;

    public InvokeException(){
        super();
    }

    public InvokeException(String msg) {
        this.msg = msg;
    }

    public InvokeException(int code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }
}
