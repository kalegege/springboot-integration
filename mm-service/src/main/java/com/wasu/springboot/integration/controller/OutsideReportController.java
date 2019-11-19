package com.wasu.springboot.integration.controller;

import com.wasu.springboot.integration.entity.report.OutSideReportEntity;
import com.wasu.springboot.integration.service.OutsideReportService;
import com.wasu.springboot.integration.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     *
    */
    @RequestMapping("batchInsert")
    @ResponseBody
    public JsonResult batchInsert(List<OutSideReportEntity> outsideReportEntityList){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setData(this.outsideReportService.batchInsert(outsideReportEntityList));
        return jsonResult;
    }
}
