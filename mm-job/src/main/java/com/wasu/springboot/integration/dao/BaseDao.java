package com.wasu.springboot.integration.dao;

import com.wasu.springboot.integration.entity.page.PageBean;
import com.wasu.springboot.integration.entity.page.PageParam;

import java.util.List;
import java.util.Map;

public interface BaseDao {

    <T> long insert(T entity);

    <T> long insert(List<T> list);

    <T> int update(T entity);

    <T> int update(List<T> list);

    <T> T getById(long id);

    <T> T getById(Object paramObject);

    int deleteById(long id);

    int remove(Object paramObject);

    <T> int delete(List<T> list);

    <T> int delete(Object paramObject);

    <T> PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap);

    <T> PageBean<T> listPageWithSpecialCount(PageParam pageParam, Map<String, Object> paramMap);

    <T> List<T> listBy(Object paramObject);

    <T> T getBy(Object paramObject);

    long tempCount(Object paramObject);
}
