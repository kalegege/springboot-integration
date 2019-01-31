package com.wasu.springboot.integration.entity.batch;

import com.wasu.springboot.integration.base.BaseEntity;

import java.sql.Timestamp;
import java.util.Date;

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

    private Date mextime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getDependOnId() {
        return dependOnId;
    }

    public void setDependOnId(Integer dependOnId) {
        this.dependOnId = dependOnId;
    }

    public String getDependOnName() {
        return dependOnName;
    }

    public void setDependOnName(String dependOnName) {
        this.dependOnName = dependOnName;
    }

    public String getDependOn() {
        return dependOn;
    }

    public void setDependOn(String dependOn) {
        this.dependOn = dependOn;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBatchLocation() {
        return batchLocation;
    }

    public void setBatchLocation(String batchLocation) {
        this.batchLocation = batchLocation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public Timestamp getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Timestamp executeTime) {
        this.executeTime = executeTime;
    }

    public Date getMextime() {
        return mextime;
    }

    public void setMextime(Date mextime) {
        this.mextime = mextime;
    }
}
