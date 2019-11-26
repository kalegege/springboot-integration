package com.wasu.springboot.integration.remoting.file.impl;

import com.wasu.springboot.integration.base.BaseRemoting;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.entity.Task.TaskDO;
import com.wasu.springboot.integration.remoting.file.FileRemoting;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileRemotingImpl extends BaseRemoting implements FileRemoting {

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    public Long getSequence() {
        return null;
    }

    @Override
    public void setSequence(long l) {

    }

    @Override
    public String pushMessage(List<String> targetList) {
        Map<String,Object> param=new HashMap<>();
//        param.put("targetList", JSONUtils.toJsonString(targetList));
        param.put("targetList", targetList);

        String resultStr=postForObject(dynamicConfig.getServiceName(),"outsideReport/pushMessage",param,String.class);
        JsonResult jsonResult=checkInvokeSuccess(resultStr);
        return JSONUtils.parseObject(jsonResult.getData() != null? jsonResult.getData().toString() : null, String.class);
    }

    @Override
    public TaskDO test() {
        String resultStr=postForObject(dynamicConfig.getServiceName(),"outsideReport/test",null,String.class);
        JsonResult jsonResult=checkInvokeSuccess(resultStr);
        return JSONUtils.parseObject(jsonResult.getData() != null? jsonResult.getData().toString() : null, TaskDO.class);
    }
}
