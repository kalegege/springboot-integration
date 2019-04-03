package com.wasu.springboot.integration.exceptions;


import org.apache.commons.lang3.StringUtils;

public class CommonCodeException extends RuntimeException {
    private static final long serialVersionUID=-5875371379845226068L;

    protected int code;

    protected  String msg;

    protected String subCode;

    protected String subMsg;

    public CommonCodeException() {
    }

    public CommonCodeException(int code, String msg, String subCode, String subMsg) {
        this.code = code;
        this.msg = msg;
        this.subCode = subCode;
        this.subMsg = subMsg;
    }

    public CommonCodeException(Throwable cause) {
        super(cause);
    }

    public String print(){
        return StringUtils.join(new String[]{getMessage(),String.valueOf(code),msg,subCode,subMsg},',');
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }
}
