package com.wasu.springboot.integration.remoting.outsideReport;

import com.wasu.springboot.integration.entity.report.OutSideReportEntity;

import java.util.List;

public interface OutsideReportRemoting {
    void batchInsertOutsideReport(List<OutSideReportEntity> outsideReportEntityList, Long seq);
}
