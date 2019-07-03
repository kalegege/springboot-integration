package com.wasu.springboot.integration.system.remoting.impl;

import com.wasu.springboot.integration.base.BaseRemoting;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.entity.system.UserInfo;
import com.wasu.springboot.integration.system.remoting.UserRemoting;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("userRemoting")
public class UserRemotingImpl extends BaseRemoting implements UserRemoting {

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    public UserInfo getUserInfoByUserId(Long id) {
        Map<String,Object> param=new HashMap<>();
        param.put("id",id);
        String resultStr=postForObject(dynamicConfig.getSystemServiceName(),"user/getUserInfoByUserId",param,String.class);
        JsonResult jsonResult=checkInvokeSuccess(resultStr);
        return JSONUtils.parseObject(jsonResult.getData() != null? jsonResult.getData().toString() : null, UserInfo.class);
    }
}
