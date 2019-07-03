package com.wasu.springboot.integration.entity.system;

import java.util.List;
import java.util.Set;

public class ActiveUser implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Long userid;

    private String loginName;

    private String userName;

    private Long orgId;

    private Integer accountType;

    private boolean flag;

    private Set<String> roleSet;

    private Set<String> permissionSet;

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<String> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<String> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<String> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
