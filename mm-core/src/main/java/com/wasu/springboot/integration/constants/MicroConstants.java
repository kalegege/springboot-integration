package com.wasu.springboot.integration.constants;


import com.wasu.springboot.integration.utils.UUIDGenerator;

public class MicroConstants {

    public final static String REGISTER_TOKEN= UUIDGenerator.getUUID();

    public final static String HEADER_REGISTER_TOKEN_KEY="x-register-token";

    public final static String METADATA_PREFIX="eureka.instance.metadata-map";

    public final static String METADATA_REGISTER_TOKEN_KEY="register-token";
}
