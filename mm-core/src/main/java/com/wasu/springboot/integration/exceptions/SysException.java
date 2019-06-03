package com.wasu.springboot.integration.exceptions;

public class SysException extends CommonCodeException {
    public SysException(int code,String msg,CommonCode commonCode) {
        this.code=code;
        this.msg=msg;
        this.subCode=commonCode.getSubCode();
        this.subMsg=commonCode.getSubMsg();
    }

    public SysException(CommonCode commonCode,int code,String msg) {
        this.code=code;
        this.msg=msg;
        this.subCode=commonCode.getSubCode();
        this.subMsg=commonCode.getSubMsg();
    }

    public SysException(CommonCode commonCode,int code,String msg,Throwable cause) {
        super(cause);
        this.code=code;
        this.msg=msg;
        this.subCode=commonCode.getSubCode();
        this.subMsg=commonCode.getSubMsg();
    }
}
