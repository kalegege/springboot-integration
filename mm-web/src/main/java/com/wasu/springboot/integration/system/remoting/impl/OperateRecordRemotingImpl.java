package com.wasu.springboot.integration.system.remoting.impl;

import com.wasu.springboot.integration.base.BaseRemoting;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.entity.system.PubOperateRecord;
import com.wasu.springboot.integration.system.remoting.OperateRecordRemoting;
import com.wasu.springboot.integration.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperateRecordRemotingImpl extends BaseRemoting implements OperateRecordRemoting {

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    public JsonResult insert(PubOperateRecord pubOperateRecord) {
        return null;
    }
}
