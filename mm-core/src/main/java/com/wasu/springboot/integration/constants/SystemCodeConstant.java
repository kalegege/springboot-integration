package com.wasu.springboot.integration.constants;

import com.wasu.springboot.integration.exceptions.CommonCode;

public class SystemCodeConstant extends CommonCodeConstant {

    public static final Integer SERVICE_EXCEPTION_CODE=-10000;
    public static final String SERVICE_EXCEPTION_MSG="服务繁忙";

    public enum ServiceEnum implements CommonCode{
        GATEWAY_UNKNOWN_ERROR("gateway_unknown_error","网关发生异常"),
        BUSINESS_UNKNOWN_ERROR("business_unknown_error","业务系统发生异常");

        ServiceEnum(String subCode,String subMsg){
            this.subCode=subCode;
            this.subMsg=subMsg;
        }

        private String subCode;

        private String subMsg;

        @Override
        public String getSubCode() {
            return subCode;
        }

        public void setSubCode(String subCode) {
            this.subCode = subCode;
        }

        @Override
        public String getSubMsg() {
            return subMsg;
        }

        public void setSubMsg(String subMsg) {
            this.subMsg = subMsg;
        }
    }

    public static final Integer PARAM_REQUIRED_CODE=-20001;
    public static final String PARAM_REQUIRED_MSG="缺少必填参数";

    public enum ParamRequiredEnum implements CommonCode{
        GATEWAY_UNKNOWN_ERROR("gateway_unknown_error","网关发生异常"),
        BUSINESS_UNKNOWN_ERROR("business_unknown_error","业务系统发生异常");

        ParamRequiredEnum(String subCode,String subMsg){
            this.subCode=subCode;
            this.subMsg=subMsg;
        }

        private String subCode;

        private String subMsg;

        @Override
        public String getSubCode() {
            return subCode;
        }

        public void setSubCode(String subCode) {
            this.subCode = subCode;
        }

        @Override
        public String getSubMsg() {
            return subMsg;
        }

        public void setSubMsg(String subMsg) {
            this.subMsg = subMsg;
        }
    }


    public static final Integer PARAM_ILLEGAL_CODE=-20002;
    public static final String PARAM_ILLEGAL_MSG="非法参数";

    public enum ParamIllegalEnum implements CommonCode{
        PAGE_PARAM_ERROR("illege_page_request","非法分页请求"),

        GATEWAY_UNKNOWN_ERROR("gateway_unknown_error","网关发生异常"),
        BUSINESS_UNKNOWN_ERROR("business_unknown_error","业务系统发生异常");

        ParamIllegalEnum(String subCode,String subMsg){
            this.subCode=subCode;
            this.subMsg=subMsg;
        }

        private String subCode;

        private String subMsg;

        @Override
        public String getSubCode() {
            return subCode;
        }

        public void setSubCode(String subCode) {
            this.subCode = subCode;
        }

        @Override
        public String getSubMsg() {
            return subMsg;
        }

        public void setSubMsg(String subMsg) {
            this.subMsg = subMsg;
        }
    }

    public static final Integer PERMISSION_EXCEPTION_CODE=-30000;

    public enum PermissionEnum implements CommonCode{
        PERMISSION_SESSION_IS_OUT_TIME("permission_session_is_out_time","会话超时"),
        PERMISSION_KICKOUT("permission_kickout","你的账号被踢出");

        PermissionEnum(String subCode,String subMsg){
            this.subCode=subCode;
            this.subMsg=subMsg;
        }

        private String subCode;

        private String subMsg;

        @Override
        public String getSubCode() {
            return subCode;
        }

        public void setSubCode(String subCode) {
            this.subCode = subCode;
        }

        @Override
        public String getSubMsg() {
            return subMsg;
        }

        public void setSubMsg(String subMsg) {
            this.subMsg = subMsg;
        }
    }
}
