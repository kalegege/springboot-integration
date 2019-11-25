package com.wasu.springboot.integration.dao;

import com.wasu.springboot.integration.base.BaseEntity;
import com.wasu.springboot.integration.constants.SystemCodeConstant;
import com.wasu.springboot.integration.entity.page.PageBean;
import com.wasu.springboot.integration.entity.page.PageParam;
import com.wasu.springboot.integration.exceptions.SysException;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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

    /**
     * 保存对象
     * @param entity
     * @param <T>
     * @return
     */
    @Override
    public <T> long insert(T entity) {
        int result= writeSessionTemplate.insert(getStatement(SQL_INSERT),entity);
        return result;
    }

    /**
     *
     * @param sqlId
     * @param entity
     * @param <T>
     * @return
     */
    protected <T> long insertOther(String sqlId,T entity){
        int result= writeSessionTemplate.insert(getStatement(sqlId),entity);
        return result;
    }

    /**
     * 批量保存对象
     * @param list
     * @param <T>
     * @return
     */
    @Override
    public <T> long insert(List<T> list) {
        if(list == null || list.size() <= 0){
            return 0;
        }
        int result=writeSessionTemplate.insert(getStatement(SQL_BATCH_INSERT),list);
        return result;
    }

    /**
     * 更新对象
     * @param entity
     * @param <T>
     * @return
     */
    @Override
    public <T> int update(T entity) {
        int result= writeSessionTemplate.insert(getStatement(SQL_UPDATE),entity);
        return result;
    }

    /**
     * 批量更新对象
     * @param list
     * @param <T>
     * @return
     */
    @Override
    public <T> int update(List<T> list) {
        if(list == null || list.size() <= 0){
            return 0;
        }
        int result= writeSessionTemplate.insert(getStatement(SQL_BATCH_UPDATE),list);
        return result;
    }

    /**
     * 自定义更新方法
     * @param sqlId
     * @param paramObject
     * @return
     */
    protected int update(String sqlId,Object paramObject){
        int result= writeSessionTemplate.insert(getStatement(sqlId),paramObject);
        return result;
    }

    /**
     * 自定义批量更新
     * @param sqlId
     * @param list
     * @param <T>
     * @return
     */
    protected <T> int update(String sqlId,List<T> list) {
        if(list == null || list.size() <= 0){
            return 0;
        }
        if(!StringUtils.isNotEmpty(sqlId)){
            sqlId=SQL_BATCH_INSERT;
        }
        int result= writeSessionTemplate.insert(getStatement(sqlId),list);
        return result;
    }

    /**
     * 根据id查找对象
     * @param id
     * @param <T>
     * @return
     */
    @Override
    public <T> T getById(long id) {
        return readSessionTemplate.selectOne(getStatement(SQL_GET_BY_ID),id);
    }

    /**
     * 根据id查找对象
     * @param paramObject
     * @param <T>
     * @return
     */
    @Override
    public <T> T getById(Object paramObject) {
        return readSessionTemplate.selectOne(getStatement(SQL_GET_BY_ID),paramObject);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(long id) {
        return writeSessionTemplate.delete(getStatement(SQL_DELETE_BY_ID),id);
    }

    /**
     * 根据id删除
     * @param sqlId
     * @param id
     * @return
     */
    protected int deleteById(String sqlId,long id) {
        return writeSessionTemplate.delete(getStatement(sqlId),id);
    }

    @Override
    public int remove(Object paramObject) {
        return writeSessionTemplate.update(getStatement(SQL_REMOVE),paramObject);
    }

    /**
     * 根据id批量删除
     * @param list
     * @param <T>
     * @return
     */
    @Override
    public <T> int delete(List<T> list) {
        if(list.isEmpty() || list.size() <= 0){
            return 0;
        }else{
            return writeSessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_IDS),list);
        }
    }

    /**
     * 根据条件删除
     * @param paramObject
     * @param <T>
     * @return
     */
    @Override
    public <T> int delete(Object paramObject) {
        if(paramObject == null) {
            return 0;
        }else{
            return writeSessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_COLUMN),paramObject);
        }
    }

    /**
     * 自定义删除方法
     * @param sqlId
     * @param paramObject
     * @param <T>
     * @return
     */
    protected  <T> int delete(String sqlId,Object paramObject){
        if(!StringUtils.isNotEmpty(sqlId)){
            sqlId=SQL_DELETE_BY_ID;
        }
        return writeSessionTemplate.delete(getStatement(sqlId),paramObject);
    }

    /**
     * 分页查询
     * @param pageParam
     * @param paramMap
     * @param <T>
     * @return
     */
    @Override
    public <T> PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap) {
        if(paramMap == null){
            paramMap =new HashMap<String,Object>();
        }
        if(pageParam.getNumPerPage() > SQL_MAX_PAGE_NUM){
            throw new SysException(SystemCodeConstant.ParamIllegalEnum.PAGE_PARAM_ERROR,SystemCodeConstant.PARAM_ILLEGAL_CODE,SystemCodeConstant.PARAM_ILLEGAL_MSG);
        }
        paramMap.put("pageFirst",(pageParam.getPageNum() - 1)*pageParam.getNumPerPage());
        paramMap.put("pageSize",pageParam.getNumPerPage());
        paramMap.put("startRowNum",(pageParam.getPageNum() -1)*pageParam.getNumPerPage());
        paramMap.put("endRowNum",pageParam.getPageNum()*pageParam.getNumPerPage());

//        Long count=readSessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT),paramMap);
        List<Object> list=readSessionTemplate.selectList(getStatement(SQL_LIST_PAGE),paramMap);

        int count=this.getListPageCount(list);
        if(count < list.size()){
            count=Integer.parseInt(readSessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT),paramMap).toString());
        }
        Object isCount =paramMap.get("isCount");
        if(isCount != null && "1".equals(isCount.toString())){
            Map<String,Object> countResultMap=readSessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM),paramMap);
            return new PageBean<T>(pageParam.getPageNum(),pageParam.getNumPerPage(),count,list,countResultMap);
        }else{
            return new PageBean<T>(pageParam.getPageNum(),pageParam.getNumPerPage(),count,list);
        }
    }

    /**
     * 分页查询自定义方法
     * @param pageParam
     * @param paramMap
     * @param listSqlId
     * @param countSqlId
     * @param countByPageSqlId
     * @param <T>
     * @return
     */
    protected  <T> PageBean<T> listPage(PageParam pageParam, Map<String, Object> paramMap,String listSqlId,
                                    String countSqlId,String countByPageSqlId) {
        if(paramMap == null){
            paramMap =new HashMap<String,Object>();
        }
        if(pageParam.getNumPerPage() > SQL_MAX_PAGE_NUM){
            throw new SysException(SystemCodeConstant.ParamIllegalEnum.PAGE_PARAM_ERROR,SystemCodeConstant.PARAM_ILLEGAL_CODE,SystemCodeConstant.PARAM_ILLEGAL_MSG);
        }
        paramMap.put("pageFirst",(pageParam.getPageNum() - 1)*pageParam.getNumPerPage());
        paramMap.put("pageSize",pageParam.getNumPerPage());
        paramMap.put("startRowNum",(pageParam.getPageNum() -1)*pageParam.getNumPerPage());
        paramMap.put("endRowNum",pageParam.getPageNum()*pageParam.getNumPerPage());

//        Long count=readSessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT),paramMap);
        List<Object> list=readSessionTemplate.selectList(getStatement(listSqlId),paramMap);

        int count=this.getListPageCount(list);
        if(count < list.size()){
            count=Integer.parseInt(readSessionTemplate.selectOne(getStatement(countSqlId),paramMap).toString());
        }
        Object isCount =paramMap.get("isCount");
        if(isCount != null && "1".equals(isCount.toString())){
            Map<String,Object> countResultMap=readSessionTemplate.selectOne(getStatement(countByPageSqlId),paramMap);
            return new PageBean<T>(pageParam.getPageNum(),pageParam.getNumPerPage(),count,list,countResultMap);
        }else{
            return new PageBean<T>(pageParam.getPageNum(),pageParam.getNumPerPage(),count,list);
        }
    }

    /**
     * 自定义查询
     * @param pageParam
     * @param paramMap
     * @param <T>
     * @return
     */
    @Override
    public <T> PageBean<T> listPageWithSpecialCount(PageParam pageParam, Map<String, Object> paramMap) {
        if(paramMap == null){
            paramMap =new HashMap<String,Object>();
        }
        if(pageParam.getNumPerPage() > SQL_MAX_PAGE_NUM){
            throw new SysException(SystemCodeConstant.ParamIllegalEnum.PAGE_PARAM_ERROR,SystemCodeConstant.PARAM_ILLEGAL_CODE,SystemCodeConstant.PARAM_ILLEGAL_MSG);
        }
        paramMap.put("pageFirst",(pageParam.getPageNum() - 1)*pageParam.getNumPerPage());
        paramMap.put("pageSize",pageParam.getNumPerPage());
        paramMap.put("startRowNum",(pageParam.getPageNum() -1)*pageParam.getNumPerPage());
        paramMap.put("endRowNum",pageParam.getPageNum()*pageParam.getNumPerPage());

        Long count=readSessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT),paramMap);
        List<Object> list=readSessionTemplate.selectList(getStatement(SQL_LIST_PAGE),paramMap);

        Object isCount =paramMap.get("isCount");
        if(isCount != null && "1".equals(isCount.toString())){
            Map<String,Object> countResultMap=readSessionTemplate.selectOne(getStatement(SQL_COUNT_BY_PAGE_PARAM),paramMap);
            return new PageBean<T>(pageParam.getPageNum(),pageParam.getNumPerPage(),count.intValue(),list,countResultMap);
        }else{
            return new PageBean<T>(pageParam.getPageNum(),pageParam.getNumPerPage(),count.intValue(),list);
        }
    }

    /**
     * 自定义查询
     * @param pageParam
     * @param paramMap
     * @param listSqlId
     * @param countSqlId
     * @param countByPageSqlId
     * @param <T>
     * @return
     */
    protected  <T> PageBean<T> listPageWithSpecialCount(PageParam pageParam, Map<String, Object> paramMap,String listSqlId,
                                                        String countSqlId,String countByPageSqlId) {
        if(paramMap == null){
            paramMap =new HashMap<String,Object>();
        }
        if(pageParam.getNumPerPage() > SQL_MAX_PAGE_NUM){
            throw new SysException(SystemCodeConstant.ParamIllegalEnum.PAGE_PARAM_ERROR,SystemCodeConstant.PARAM_ILLEGAL_CODE,SystemCodeConstant.PARAM_ILLEGAL_MSG);
        }
        paramMap.put("pageFirst",(pageParam.getPageNum() - 1)*pageParam.getNumPerPage());
        paramMap.put("pageSize",pageParam.getNumPerPage());
        paramMap.put("startRowNum",(pageParam.getPageNum() -1)*pageParam.getNumPerPage());
        paramMap.put("endRowNum",pageParam.getPageNum()*pageParam.getNumPerPage());

        Long count=readSessionTemplate.selectOne(getStatement(countSqlId),paramMap);
        List<Object> list=readSessionTemplate.selectList(getStatement(listSqlId),paramMap);

        Object isCount =paramMap.get("isCount");
        if(isCount != null && "1".equals(isCount.toString())){
            Map<String,Object> countResultMap=readSessionTemplate.selectOne(getStatement(countByPageSqlId),paramMap);
            return new PageBean<T>(pageParam.getPageNum(),pageParam.getNumPerPage(),count.intValue(),list,countResultMap);
        }else{
            return new PageBean<T>(pageParam.getPageNum(),pageParam.getNumPerPage(),count.intValue(),list);
        }
    }

    @Override
    public <T> List<T> listBy(Object paramObject) {
        return readSessionTemplate.selectList(getStatement(SQL_LIST_BY),paramObject);
    }

    protected  <T> List<T> listBy(String sqlId,Object paramObject) {
        return readSessionTemplate.selectList(getStatement(sqlId),paramObject);
    }

    /**
     * 根据条件查询
     * @param paramObject
     * @param <T>
     * @return
     */
    @Override
    public <T> T getBy(Object paramObject) {
        return readSessionTemplate.selectOne(getStatement(SQL_GET_BY),paramObject);
    }

    /**
     * 根据条件查询
     * @param sqlId
     * @param paramObject
     * @param <T>
     * @return
     */
    protected  <T> T getBy(String sqlId,Object paramObject){
        return readSessionTemplate.selectOne(getStatement(sqlId),paramObject);
    }

    @Override
    public long tempCount(Object paramObject) {
        Long count=readSessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT),paramObject);
        return count;
    }

    protected long tempCount(String sqlid,Object paramObject) {
        Long count=readSessionTemplate.selectOne(getStatement(sqlid),paramObject);
        return count;
    }

    /**
     * 获取mapper命名空间
     * @param sqlId
     * @return
     */
    private String getStatement(String sqlId){
        String name=this.getClass().getName();
        StringBuffer sb=new StringBuffer();
        sb.append(name).append(".").append(sqlId);
        String statement=sb.toString();
        return statement;
    }

    /**
     * 统计分页查询总记录数
     * @param list
     * @return
     */
    protected int getListPageCount(List<Object> list) {
        int count=0;
        if(list!=null && list.size() > 0){
            Object firstRow=list.get(0);
            if(BaseEntity.class.isInstance(firstRow))
                count=((BaseEntity)firstRow).getQueryRowCount();
            else if(Map.class.isInstance(firstRow))
                count=Integer.parseInt(((Map<?,?>)firstRow).get("queryRowCount").toString());
        }
        return count;
    }
}
