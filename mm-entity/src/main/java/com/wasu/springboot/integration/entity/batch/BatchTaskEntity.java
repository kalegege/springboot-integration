package com.wasu.springboot.integration.entity.batch;

import com.wasu.springboot.integration.base.BaseEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class BatchTaskEntity extends BaseEntity{
    private static final long serialVersionUID=1L;

    private String taskName;

    private String taskDescription;

    private String frequency;

    private Integer useStatus;

    private Integer orderNo;

    private Integer dependOnId;

    private String dependOnName;

    private String dependOn;

    private String batchType;

    private String result;

    private String batchLocation;

    private Date startDate;

    private Date endDate;

    private String startDateStr;

    private String endDateStr;

    private Timestamp executeTime;

    private Date mtime;
}
