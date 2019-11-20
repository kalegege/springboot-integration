package com.wasu.springboot.integration.entity.system;

import com.wasu.springboot.integration.base.BaseEntity;
import lombok.Data;

@Data
public class Dictionary extends BaseEntity {

    private String dataType;

    private String dataCode;

    private String dataValue;

    private Integer sortNo;

    private String dataDesc;

    private Long parentId;
}
