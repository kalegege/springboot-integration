package com.wasu.springboot.integration.redis;

public enum MasterSlaveStateEnum {
    INVALID,MASTER,SLAVE;

    public static String getStateName(MasterSlaveStateEnum state){
        switch(state){
            case INVALID:return "invalid";
            case MASTER:return "master";
            case SLAVE:return "slave";
            default:return "";
        }
    }
}
