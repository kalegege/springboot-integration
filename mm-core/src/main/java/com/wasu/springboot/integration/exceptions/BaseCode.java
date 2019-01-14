package com.wasu.springboot.integration.exceptions;

public class BaseCode implements CommonCode {

    private final int code;

    private final String msg;

    public BaseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getSubCode() {
        return code +"";
    }

    @Override
    public String getSubMsg() {
        return msg;
    }
}
