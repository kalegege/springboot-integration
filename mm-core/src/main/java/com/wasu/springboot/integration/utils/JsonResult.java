package com.wasu.springboot.integration.utils;

import com.wasu.springboot.integration.constants.BizCodeConstant;
import com.wasu.springboot.integration.constants.CommonCodeConstant;
import com.wasu.springboot.integration.exceptions.CommonCode;

import java.io.Serializable;

public class JsonResult implements Serializable{

    private static final long serialVersionUID=1L;

    protected Integer code;

    protected String msg;

    protected String subCode;

    protected String subMsg;

    protected Object data;

    public JsonResult() {
        this.code= CommonCodeConstant.SUCCESS_CODE;
        this.msg=CommonCodeConstant.SUCCESS_MSG;
        this.data=null;
    }

    public JsonResult(String subMsg, Object data) {
        this.code= CommonCodeConstant.SUCCESS_CODE;
        this.msg=CommonCodeConstant.SUCCESS_MSG;
        this.subMsg = subMsg;
        this.data = data;
    }

    public JsonResult(Object data) {
        this.code= CommonCodeConstant.SUCCESS_CODE;
        this.msg=CommonCodeConstant.SUCCESS_MSG;
        this.data = data;
    }

    public JsonResult(CommonCode exceptionCode) {
        this.code = BizCodeConstant.BUSINESS_EXCEPTION_CODE;
        this.msg=BizCodeConstant.BUSINESS_EXCEPTION_MSG;
        this.subCode=exceptionCode.getSubCode();
        this.subMsg=exceptionCode.getSubMsg();
    }

    public JsonResult(CommonCode exceptionCode,Object data) {
        this.code = BizCodeConstant.BUSINESS_EXCEPTION_CODE;
        this.msg=BizCodeConstant.BUSINESS_EXCEPTION_MSG;
        this.subCode=exceptionCode.getSubCode();
        this.subMsg=exceptionCode.getSubMsg();
        this.data=data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
