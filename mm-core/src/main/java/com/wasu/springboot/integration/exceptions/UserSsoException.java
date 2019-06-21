package com.wasu.springboot.integration.exceptions;

public class UserSsoException extends ServiceException {

    private static final long serialVersionUID=-1L;

    public static final String USER_SESSION_CODE="99999999";

    public static final String USER_SESSION_MESSAGE="会话超时，请重新登录";

    private String message;

    public UserSsoException(String code,String message) {
        super(code,message);
        this.subCode=code;
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
