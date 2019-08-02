package com.wasu.springboot.integration.service.impl;

import com.wasu.springboot.integration.entity.Task.TaskDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ActivitiServiceImpl extends BaseServiceImpl  {

    private static final Logger LOGGER= LoggerFactory.getLogger(ActivitiServiceImpl.class);

    @Override
    public void beforeComplete() {

    }

    @Override
    public List<TaskDO> complete() {
        List<TaskDO> result=new ArrayList<>();
        return result;
    }

    @Override
    public void afterComplete() {
        LOGGER.info("after do complete ActivitiServiceImpl");
    }
}
