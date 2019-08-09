package com.wasu.springboot.integration.common.shiro;

import com.wasu.springboot.integration.common.config.DynamicConfig;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    /**
     * 定义shiro过滤器
     * @param securityManager
     * @return
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/**","anon");
        filterChainDefinitionMap.put("/getPage","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 定义安全管理器
     * @param sessionManager
     * @param customRealm
     * @param rememberMeManager
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(SessionManager sessionManager,
                                                               CustomRealm customRealm, CookieRememberMeManager rememberMeManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     * 定义session管理器
     * @param redisSessionDAO
     * @param
     * @return
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO,
                                                   @Qualifier("sessionIdCookie") SimpleCookie sessionIdCookie,
                                                   SessionFactory boardSessionFactory, DynamicConfig dynamicConfig){
        DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(43200000 * 2);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationInterval(1800000);
        sessionManager.setSessionDAO(redisSessionDAO);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;
    }

    @Bean(name="boardSessionFactory")
    public SessionFactory boardSessionFactory(){
        return new BoardSessionFactory();
    }

    @Bean(name = "redisSessionDAO")
    public RedisSessionDAO redisSessionDAO(){
        return new RedisSessionDAO();
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie(){
        SimpleCookie sessionIdCookie=new SimpleCookie();
        sessionIdCookie.setName("SHAREJSESSIONID");
        sessionIdCookie.setHttpOnly(true);
        return sessionIdCookie;
    }

    /**
     * AOP方法及权限检查
     * @return
     */
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * AOP方法及权限检查
     * @param securityManager
     * @return
     */
    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 保证实现了shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 定义过滤器配置
     * @return
     */
    @Bean
    public DelegatingFilterProxyRegistrationBean shiroFilterRegistrationBean(){
        DelegatingFilterProxyRegistrationBean registrationBean=new DelegatingFilterProxyRegistrationBean("shiroFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public SimpleCookie remeberMeCookie(){
        SimpleCookie simpleCookie=new SimpleCookie("remeberMe");
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(SimpleCookie remeberMeCookie){
        CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remeberMeCookie);
        return cookieRememberMeManager;
    }

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    @Bean(name = "customRealm")
    public CustomRealm customRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        CustomRealm customRealm=new CustomRealm();
        customRealm.setAuthenticationCachingEnabled(true);
        return customRealm;
    }
}
