package com.wasu.springboot.integration.controller;

import com.alibaba.fastjson.JSONObject;
import com.wasu.springboot.integration.entity.Task.TaskDO;
import com.wasu.springboot.integration.entity.report.OutSideReportEntity;
import com.wasu.springboot.integration.entity.report.OutsideReportEntityList;
import com.wasu.springboot.integration.service.OutsideReportService;
import com.wasu.springboot.integration.utils.JSONUtils;
import com.wasu.springboot.integration.utils.JsonResult;
import com.wasu.springboot.integration.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/outsideReport")
public class OutsideReportController {

    @Autowired
    private OutsideReportService outsideReportService;


    /**
     * @Description:
     * @Author: xujian
     * @Date: 2019/11/19 14:23
     * @Param:
     * @Return:
     * @Exception:
     */
    @RequestMapping("batchInsert")
    @ResponseBody
    public JsonResult batchInsert(String outsideReportEntityList) {
        JsonResult jsonResult = new JsonResult();
        List<OutSideReportEntity> outSideReportEntityList = null;
        if (StringUtils.isNotBlank(outsideReportEntityList)) {
            outSideReportEntityList = JSONObject.parseArray(outsideReportEntityList, OutSideReportEntity.class);
        }
        if (null != outSideReportEntityList && outSideReportEntityList.size() > 0) {
            jsonResult.setData(this.outsideReportService.batchInsert(outSideReportEntityList));
        }
        return jsonResult;
    }

    /**
     * @author: xujian
     * @Date: 2019/11/20 17:37
     * @Description:
     */
    @RequestMapping("batchInsertList")
    @ResponseBody
    public JsonResult batchInsertList(@RequestBody List<OutSideReportEntity> outsideReportEntityList) {
        JsonResult jsonResult = new JsonResult();
        if (null != outsideReportEntityList && outsideReportEntityList.size() > 0) {
            jsonResult.setData(this.outsideReportService.batchInsert(outsideReportEntityList));
        }
        return jsonResult;
    }


    @RequestMapping("pushMessage")
    @ResponseBody
    public JsonResult pushMessage(@RequestParam("targetList") List<String> targetList) {
        System.out.println("获取到pushMessage请求：---------------" );
        targetList.forEach(s -> System.out.println(s));
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData("success");
        return jsonResult;
    }

    @RequestMapping("test")
    @ResponseBody
    public JsonResult test() {
        JsonResult jsonResult = new JsonResult();
        TaskDO result=new TaskDO();
        result.setName("test");
        System.out.println("获取到test请求：---------------" );
        jsonResult.setData(result);
        return jsonResult;
    }
}
