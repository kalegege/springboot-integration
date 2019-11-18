package com.wasu.springboot.integration.entity.system;

import com.wasu.springboot.integration.base.BaseEntity;
import lombok.Data;

@Data
public class PubOperateRecord extends BaseEntity {

    private static final long serialVersionUID=1L;

    private Long userId;

    private String userName;

    private String ipAddress;

    private String requestUrl;

    private String param;

    private String operateName;

    private Short operateType;

    private Short isSuccess;

    private String msg;
}
