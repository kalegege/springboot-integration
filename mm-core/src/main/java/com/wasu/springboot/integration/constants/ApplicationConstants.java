package com.wasu.springboot.integration.constants;

import com.alibaba.fastjson.serializer.SerializerFeature;

public class ApplicationConstants {

    public static final SerializerFeature[] FEATURES={SerializerFeature.WriteDateUseDateFormat};

    public static final String MONITOR_SWITCH_ON="on";

    public static final String MONITOR_SWITCH_OFF="off";

    public static final String TASK_LOG_ON="on";

    public static final String TASK_LOG_OFF="off";
}
