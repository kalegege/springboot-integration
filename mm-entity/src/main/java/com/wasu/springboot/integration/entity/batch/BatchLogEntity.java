package com.wasu.springboot.integration.entity.batch;

import com.wasu.springboot.integration.base.BaseEntity;

import java.util.Date;

public class BatchLogEntity extends BaseEntity{
    private static final long serialVersionUID=1L;

    private Long taskId;

    private Date startTime;

    private Date endTime;

    private Long usedMsec;

    private String result;

    private String exceptionMessage;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getUsedMsec() {
        return usedMsec;
    }

    public void setUsedMsec(Long usedMsec) {
        this.usedMsec = usedMsec;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
