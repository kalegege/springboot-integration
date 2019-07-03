package com.wasu.springboot.integration.common.shiro;

import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.utils.CacheUtil;
import com.wasu.springboot.integration.utils.SerializeUtils;
import com.wasu.springboot.integration.utils.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RedisSessionDAO extends AbstractSessionDAO {

    private static final Logger LOGGER= LoggerFactory.getLogger(RedisSessionDAO.class);

    private  static final int SESSION_TIME=46800;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId=null;
        if(StringUtils.isEmpty(session.getAttribute(BoardSessionFactory.OLD_SESSION_ID))){
            sessionId=this.generateSessionId(session);
        }else{
            sessionId=(Serializable)session.getAttribute(BoardSessionFactory.OLD_SESSION_ID);
        }
        this.assignSessionId(session,sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        try{
            if(sessionId == null){
                LOGGER.warn("session id is null");
                return null;
            }
            String key =getShiroKey(sessionId);
            Session s = (Session) SerializeUtils.unserialize(cacheUtil.getBytes(key));
            return s;
        }catch(Exception e){
            LOGGER.warn(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    private void saveSession(Session session) throws UnknownSessionException {
        try{
            if(session == null || session.getId() == null){
                LOGGER.warn("session id is null");
                return;
            }
            String key = getShiroKey(session.getId());
            byte[] value= SerializeUtils.serialize(session);
            session.setTimeout(SESSION_TIME * 1000);
            cacheUtil.setBytes(key,value);
            cacheUtil.expire(key,SESSION_TIME);
        }catch(Exception e){
            LOGGER.warn(e.getMessage(),e);
        }
    }

    private String getShiroKey(Serializable id) {
        String preKey=dynamicConfig.getSessionKeyPrefix() + id;
        return preKey;
    }

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
            LOGGER.warn("session id is null");
            return;
        }
        String key =getShiroKey(session.getId());
        cacheUtil.remove(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions=new HashSet<>();
        return sessions;
    }
}
