package com.wasu.springboot.integration.entity.system;

import com.wasu.springboot.integration.base.BaseEntity;

public class UserInfo extends BaseEntity{

    private static final long serialVersionUID = 112L;

    private String name;

    private int age;

    private String passwd;

    private Integer relationType;

    private Integer role;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
