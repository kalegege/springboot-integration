package com.wasu.springboot.integration.task;

import com.alibaba.fastjson.JSONArray;
import com.wasu.springboot.integration.common.batch.aop.TaskLogAnnotation;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.constants.CacheKeyConstants;
import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.entity.report.OutSideReportEntity;
import com.wasu.springboot.integration.exceptions.ServiceException;
import com.wasu.springboot.integration.job.api.JobContext;
import com.wasu.springboot.integration.job.api.SimpleJob;
import com.wasu.springboot.integration.lock.LockMode;
import com.wasu.springboot.integration.lock.LockService;
import com.wasu.springboot.integration.remoting.file.FileRemoting;
import com.wasu.springboot.integration.remoting.outsideReport.OutsideReportRemoting;
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

    @Autowired
    private LockService lockService;

    @Autowired
    private FileRemoting fileRemoting;

    @Autowired
    private OutsideReportRemoting outsideReportRemoting;

    @Override
    @TaskLogAnnotation(taskName = "autoImportReportTask")
    public void execute(JobContext var1) {
        LOGGER.info("start---------------");
        Long start = System.currentTimeMillis();
        Map<String, Object> param=new HashMap<>();
        param.put("appkey",dynamicConfig.getGatewayAppkey());
        param.put("appsecret",dynamicConfig.getGatewayAppsecret());
        String lastImportDateStr="2019-06-10 17:00:000";
        Date now= DateUtils.getNow();
        param.put("endTime",DateUtils.formate(now));
        Date dateTime;
        if(StringUtils.isNoneBlank(lastImportDateStr)){
            dateTime=DateUtils.parseDateTime(lastImportDateStr);
            if(DateUtils.getSubDay(now,dateTime) > 90){
                param.put("startTime",DateUtils.formate(DateUtils.addOrSubDay(now,-90)));
                param.put("isContainCtime",CommonConstant.NUMBER_1);
            }else{
                param.put("startTime",DateUtils.formate(now));
            }
        }else{
            dateTime=DateUtils.addOrSubDay(now,-90);
            param.put("startTime",dateTime);
            param.put("isContainCtime",CommonConstant.NUMBER_1);
        }
        Integer dateNumber;
        if((dateNumber=DateUtils.getSubDay(now,dateTime)) > CommonConstant.NUMBER_1){
            for(int i=CommonConstant.NUMBER_0;i<dateNumber;i++){
                param.put("startTime",DateUtils.formate(dateTime));
                if((i+CommonConstant.NUMBER_1) == dateNumber){
                    param.put("endTime",DateUtils.formate(now));
                }else{
                    param.put("endTime",DateUtils.formate(DateUtils.addOrSubDay(dateTime,CommonConstant.NUMBER_1)));
                }
                this.queryOutsideFromOutside(param);
                dateTime=DateUtils.addOrSubDay(dateTime,CommonConstant.NUMBER_1);
            }
        }else{
            this.queryOutsideFromOutside(param);
        }

        Long end = System.currentTimeMillis();
        LOGGER.info("end-----------------");
        LOGGER.info("耗时:"+(start - end));
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
        if(jsonResult.getData() == null){
            LOGGER.info("获取到的外部报告为空");
            return;
        }
        List<OutSideReportEntity> outSideReportEntityList= JSONUtils.parseArray((JSONArray) jsonResult.getData(), OutSideReportEntity.class);
        if(CollectionUtils.isEmpty(outSideReportEntityList)){
            LOGGER.info("获取到的外部报告为空");
            return;
        }
//        String randomToken = UUIDGenerator.getUUID();
//        Long seq=null;
//        try{
//            if(lockService.tryLock(CacheKeyConstants.OUTSIDE_REPORT_BATCH_INSERT_KEY,randomToken, LockMode.WRITE,3000)){
//                seq=fileRemoting.getSequence();
//                fileRemoting.setSequence(seq+outSideReportEntityList.size() - CommonConstant.NUMBER_1);
//            }
//        }catch(Exception e){
//            LOGGER.error("autoImportReportTask:添加分布式锁失败");
//        }finally {
//            lockService.releaseLock(CacheKeyConstants.OUTSIDE_REPORT_BATCH_INSERT_KEY,randomToken);
//        }
//        if(seq != null){
//            outsideReportRemoting.batchInsertOutsideReport(outSideReportEntityList,seq);
//        }

        outsideReportRemoting.batchInsertOutsideReport(outSideReportEntityList);
    }

    @Override
    public String getJobname() {
        return "autoImportReportTask";
    }
}
