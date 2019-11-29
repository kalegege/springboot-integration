package com.wasu.springboot.integration.entity.system;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseDataRes {

    private Integer  code;

    private String message;

    private TokenRes data;
}
