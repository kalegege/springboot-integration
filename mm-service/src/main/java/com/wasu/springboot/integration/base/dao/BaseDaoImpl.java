package com.wasu.springboot.integration.base.dao;

import com.wasu.springboot.integration.entity.page.PageBean;
import com.wasu.springboot.integration.entity.page.PageParam;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class BaseDaoImpl implements BaseDao {

    protected static final Logger LOG= LoggerFactory.getLogger(BaseDaoImpl.class);

    private static final String SQL_INSERT="insert";
    private static final String SQL_BATCH_INSERT="batchInsert";
    private static final String SQL_UPDATE="update";
    private static final String SQL_BATCH_UPDATE="batchUpdate";
    private static final String SQL_GET_BY_ID="getById";
    private static final String SQL_GET_BY="getBy";
    private static final String SQL_DELETE_BY_ID="deleteById";
    private static final String SQL_REMOVE="remove";
    private static final String SQL_LIST_PAGE="listPage";
    private static final String SQL_LIST_PAGE_COUNT="listPageCount";
    private static final String SQL_LIST_BY="listBy";
    private static final String SQL_COUNT_BY_PAGE_PARAM="countByPageParam";
    private static final String SQL_BATCH_DELETE_BY_COLUMN="batchDeleteByColumn";
    private static final String SQL_BATCH_DELETE_BY_IDS="batchDeleteByIds";
    private static final int SQL_MAX_PAGE_NUM=200;

    private SqlSession readSessionTemplate;
    private SqlSession writeSessionTemplate;

    public SqlSession getReadSessionTemplate() {
        return readSessionTemplate;
    }

    public void setReadSessionTemplate(SqlSession readSessionTemplate) {
        this.readSessionTemplate = readSessionTemplate;
    }

    public SqlSession getWriteSessionTemplate() {
        return writeSessionTemplate;
    }

    public void setWriteSessionTemplate(SqlSession writeSessionTemplate) {
        this.writeSessionTemplate = writeSessionTemplate;
    }

    @Override
    public <T> long insert(T entity) {
        return 0;
    }

    @Override
    public <T> long insert(List<T> list) {
        return 0;
    }

    @Override
    public <T> int update(T entity) {
        return 0;
    }

    @Override
    public <T> int update(List<T> list) {
        return 0;
    }

    @Override
    public <T> T getById(long id) {
        return null;
    }

    @Override
    public <T> T getById(Object paramObject) {
        return null;
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public int remove(Object paramObject) {
        return 0;
    }

    @Override
    public <T> int delete(List<T> list) {
        return 0;
    }

    @Override
    public <T> int delete(Object paramObject) {
        return 0;
    }

    @Override
    public <T> PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public <T> PageBean<T> listPageWithSpecialCount(PageParam pageParam, Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public <T> List<T> listBy(Object paramObject) {
        return null;
    }

    @Override
    public <T> T getBy(Object paramObject) {
        return null;
    }

    @Override
    public long tempCount(Object paramObject) {
        return 0;
    }
}
