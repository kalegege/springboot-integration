package com.wasu.springboot.integration.job.impl;

import com.wasu.springboot.integration.job.api.SimpleJob;

import java.util.List;

public abstract class AbstractJobGroupServiceImpl extends AbstractJobServiceImpl {
    private List<SimpleJob> listJobGroup;

    public AbstractJobGroupServiceImpl() {
    }

    public List<SimpleJob> getListJobGroup() {
        return listJobGroup;
    }

    public void setListJobGroup(List<SimpleJob> listJobGroup) {
        this.listJobGroup = listJobGroup;
    }
}
