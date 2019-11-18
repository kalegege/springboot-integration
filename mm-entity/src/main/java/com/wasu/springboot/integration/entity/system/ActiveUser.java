package com.wasu.springboot.integration.entity.system;

import lombok.Data;

import java.util.Set;

@Data
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
}
