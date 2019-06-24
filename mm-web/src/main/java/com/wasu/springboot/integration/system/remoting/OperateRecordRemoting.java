package com.wasu.springboot.integration.system.remoting;

import com.wasu.springboot.integration.entity.system.PubOperateRecord;
import com.wasu.springboot.integration.utils.JsonResult;

public interface OperateRecordRemoting {

    JsonResult insert(PubOperateRecord pubOperateRecord);
}
