package com.wasu.springboot.integration.remoting.outsideReport.impl;

import com.wasu.springboot.integration.entity.report.OutSideReportEntity;
import com.wasu.springboot.integration.remoting.outsideReport.OutsideReportRemoting;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutsideReportRemotingImpl implements OutsideReportRemoting {
    @Override
    public void batchInsertOutsideReport(List<OutSideReportEntity> outsideReportEntityList, Long seq) {

    }
}
