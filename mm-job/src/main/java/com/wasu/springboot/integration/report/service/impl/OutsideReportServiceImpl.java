package com.wasu.springboot.integration.report.service.impl;

import com.wasu.springboot.integration.entity.report.OutSideReportEntity;
import com.wasu.springboot.integration.report.dao.OutsideReportDao;
import com.wasu.springboot.integration.report.service.OutsideReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutsideReportServiceImpl implements OutsideReportService {

    @Autowired
    private OutsideReportDao outsideReportDao;


    @Override
    public Long batchInsert(List<OutSideReportEntity> outSideReportEntities) {
        return outsideReportDao.insert(outSideReportEntities);
    }
}
