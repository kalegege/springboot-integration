package com.wasu.springboot.integration.service;

import com.wasu.springboot.integration.entity.report.OutSideReportEntity;

import java.util.List;

public interface OutsideReportService {
    Long batchInsert(List<OutSideReportEntity> outSideReportEntities);
}
