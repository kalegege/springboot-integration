package com.wasu.springboot.integration.remoting.outsideReport.impl;

import com.wasu.springboot.integration.base.BaseRemoting;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.entity.report.OutSideReportEntity;
import com.wasu.springboot.integration.remoting.outsideReport.OutsideReportRemoting;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OutsideReportRemotingImpl extends BaseRemoting implements OutsideReportRemoting {

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    public void batchInsertOutsideReport(List<OutSideReportEntity> outsideReportEntityList, Long seq) {

    }

    @Override
    public Long batchInsertOutsideReport(List<OutSideReportEntity> outsideReportEntityList) {
        Map<String,Object> param=new HashMap<>();
        param.put("outSideReportEntities",outsideReportEntityList);
        String resultStr=postForObject(dynamicConfig.getServiceName(),"outsideReport/batchInsert",param,String.class);
        JsonResult jsonResult=checkInvokeSuccess(resultStr);
        return JSONUtils.parseObject(jsonResult.getData() != null? jsonResult.getData().toString() : null, Long.class);
    }
}
