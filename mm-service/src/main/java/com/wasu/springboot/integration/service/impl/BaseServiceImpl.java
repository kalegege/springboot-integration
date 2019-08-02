package com.wasu.springboot.integration.service.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装一个完成任务的通用模板
 */
public abstract class BaseServiceImpl {
    public abstract void beforeComplete();

    public abstract <T> List<T> complete();

    public abstract void afterComplete();

    public <T> List<T> doComplete() {
        List<T> result = new ArrayList<>();
        this.beforeComplete();
        result = this.complete();
        this.afterComplete();
        return result;
    }
}
