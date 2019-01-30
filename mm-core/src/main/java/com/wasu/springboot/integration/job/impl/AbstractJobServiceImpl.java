package com.wasu.springboot.integration.job.impl;

import com.wasu.springboot.integration.job.JobQuartzAdapterService;
import com.wasu.springboot.integration.job.filter.JobFilter;

import java.util.List;

public abstract class AbstractJobServiceImpl implements JobQuartzAdapterService{
    private List<JobFilter> listJobFilter;

    public AbstractJobServiceImpl() {
    }

    public List<JobFilter> getListJobFilter() {
        return listJobFilter;
    }

    public void setListJobFilter(List<JobFilter> listJobFilter) {
        this.listJobFilter = listJobFilter;
    }

    @Override
    public void execute() {
        List<JobFilter> listjobFilter=this.getListJobFilter();
        int length=listjobFilter.size();

        int i;
        JobFilter filter;
        for(i=0;i<length;i++){
            filter=(JobFilter)listjobFilter.get(i);
            if(!filter.preHandler()){
                return;
            }
        }

        this.doExecute();

        for(i=length-1;i>=0;--i){
            filter=(JobFilter)listjobFilter.get(i);
            filter.afterHandler();
        }
    }

    protected abstract void doExecute();
}
