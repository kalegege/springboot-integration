package com.wasu.springboot.integration.exceptions;

import com.wasu.springboot.integration.constants.ServiceCodeConstant;

public class ServiceException extends CommonCodeException {

    private static final long serialVersionUID=-1;

    {
        this.code= ServiceCodeConstant.BUSINESS_EXCEPTION_CODE;
        this.msg=ServiceCodeConstant.BUSINESS_EXCEPTION_MSG;
    }

    public ServiceException(String subMsg){
        this(String.valueOf(ServiceCodeConstant.BUSINESS_EXCEPTION_CODE),subMsg);
    }

    public ServiceException(String subCode,String subMsg) {
        this.subCode=subCode;
        this.subMsg=subMsg;
    }

    public ServiceException(CommonCode commonCode){
        this.subCode=commonCode.getSubCode();
        this.subMsg=commonCode.getSubMsg();
    }

    public ServiceException(CommonCode commonCode,Throwable throwable) {
        super(throwable);
        this.subCode=commonCode.getSubCode();
        this.subMsg=commonCode.getSubMsg();
    }
}
