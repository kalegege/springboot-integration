package com.wasu.springboot.integration.task;

import com.alibaba.fastjson.JSONArray;
import com.wasu.springboot.integration.common.batch.aop.TaskLogAnnotation;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.entity.report.OutSideReportEntity;
import com.wasu.springboot.integration.exceptions.ServiceException;
import com.wasu.springboot.integration.job.api.JobContext;
import com.wasu.springboot.integration.job.api.SimpleJob;
import com.wasu.springboot.integration.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Configurable
@EnableScheduling
public class AutoImportReportTask implements SimpleJob {
    private static final Logger LOGGER= LoggerFactory.getLogger(AutoImportReportTask.class);
    private static final String url_last="/api/outsideReport/queryOutsideReport";

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    @TaskLogAnnotation(taskName = "autoImportReportTask")
    public void execute(JobContext var1) {
        LOGGER.info("start---------------");
        Map<String, Object> param=new HashMap<>();
        param.put("appkey",dynamicConfig.getGatewayAppkey());
        param.put("appsecret",dynamicConfig.getGatewayAppsecret());
        Date now= DateUtils.getNow();
        param.put("endTime",DateUtils.formate(now));

        queryOutsideFromOutside(param);
        LOGGER.info("end-----------------");
    }

    /**
     * 调用外部接口获取数据
     * @param param
     */
    private void queryOutsideFromOutside(Map<String, Object> param) {
        String jsonResultJSon= HttpClientUtils.postForJson(dynamicConfig.getGatewayUrl()+url_last,param);
        JsonResult jsonResult;
        if(StringUtils.isBlank(jsonResultJSon) || (jsonResult=JSONUtils.parseObject(jsonResultJSon,JsonResult.class)) == null || jsonResult.getCode() != CommonConstant.NUMBER_1){
            throw new ServiceException("获取外部报告失败");
        }
        List<OutSideReportEntity> outSideReportEntityList= JSONUtils.parseArray((JSONArray) jsonResult.getData(), OutSideReportEntity.class);
        if(CollectionUtils.isEmpty(outSideReportEntityList)){
            return;
        }

    }

    @Override
    public String getJobname() {
        return "autoImportReportTask";
    }
}
