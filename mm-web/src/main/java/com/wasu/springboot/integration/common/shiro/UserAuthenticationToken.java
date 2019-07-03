package com.wasu.springboot.integration.common.shiro;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.shiro.authc.UsernamePasswordToken;

public class UserAuthenticationToken extends UsernamePasswordToken {

    private static final long serialVersionUID=1L;

    private String thsUserId;

    private Integer userType;

    public UserAuthenticationToken(){super();}

    public UserAuthenticationToken(Integer userType) {
        super();
        this.userType = userType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getThsUserId() {
        return thsUserId;
    }

    public void setThsUserId(String thsUserId) {
        this.thsUserId = thsUserId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
