package com.wasu.springboot.integration.common.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;

public class BoardSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext sessionContext) {
        return null;
    }
}
