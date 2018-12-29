package com.wasu.springboot.integration.base;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    protected Date ctime;

    protected Date mtime;

    protected Date rtime;

    protected Integer isvalid;

    protected String remark;

    protected Long orgId;

    protected Integer isVersion;

    protected Long creater;

    protected Long updater;

    protected Integer queryRowCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Integer getIsVersion() {
        return isVersion;
    }

    public void setIsVersion(Integer isVersion) {
        this.isVersion = isVersion;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Integer getQueryRowCount() {
        return queryRowCount;
    }

    public void setQueryRowCount(Integer queryRowCount) {
        this.queryRowCount = queryRowCount;
    }
}
