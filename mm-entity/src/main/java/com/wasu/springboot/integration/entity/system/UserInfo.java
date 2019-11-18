package com.wasu.springboot.integration.entity.system;

import com.wasu.springboot.integration.base.BaseEntity;
import lombok.Data;

@Data
public class UserInfo extends BaseEntity{

    private static final long serialVersionUID = 112L;

    private String name;

    private int age;

    private String passwd;

    private Integer relationType;

    private Integer role;

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
