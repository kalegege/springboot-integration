package com.wasu.springboot.integration.enums;

public enum OperationTypeEnum {
    INSERT((short) 1, "新增"),

    UPDATE((short) 2, "修改");


    private Short type;

    private String typeName;

    OperationTypeEnum(Short type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public Short getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }
}
