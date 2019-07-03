package com.wasu.springboot.integration.common.shiro;

import com.wasu.springboot.integration.batch.controller.HelloController;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.constants.ThsLoginConstant;
import com.wasu.springboot.integration.entity.system.ActiveUser;
import com.wasu.springboot.integration.entity.system.UserInfo;
import com.wasu.springboot.integration.system.remoting.UserRemoting;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomRealm extends AuthorizingRealm {

    private static final Logger LOGGER= LoggerFactory.getLogger(CustomRealm.class);

    private final String[] APPROVAL_PERMISSION_ARRAY={
            "office:manage",
            "office:task:manage",
            "office:approval:manage",
            "office:approval:deal"
    };

    @Autowired
    private UserRemoting userRemoting;

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        ActiveUser activeUser=(ActiveUser)this.getAuthenticationCacheKey(principals);

        authorizationInfo.setRoles(activeUser.getRoleSet());
        authorizationInfo.setStringPermissions(activeUser.getPermissionSet());
        return authorizationInfo;
    }

    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserAuthenticationToken authenticationToken=(UserAuthenticationToken) token;

        String thsUserId=authenticationToken.getThsUserId();
        Long thsId=Long.parseLong(thsUserId);
        Integer userType=authenticationToken.getUserType();

        UserInfo userInfo=null;
        try{
            userInfo=userRemoting.getUserInfoByUserId(thsId);
        }catch(Exception e){
            LOGGER.error("根据用户名查询用户信息异常",e);
        }

        if(userInfo == null){
            LOGGER.warn("不存在此用户,id:"+thsId);
            //其他用户操作
        }

        String password= ThsLoginConstant.PASSWORD_SIMPLE_PLAIN;
        Integer relationType=userInfo.getRelationType();
        Integer role=userInfo.getRole();

        ActiveUser activeUser=new ActiveUser();
        activeUser.setUserid(userInfo.getId());
        activeUser.setOrgId(userInfo.getOrgId());
        activeUser.setAccountType(userType);

        ShiroUtils.setAttribute("userInfo",activeUser);
        ShiroUtils.setAttribute("privilegeApi",dynamicConfig.getSystemServiceName());
        return new SimpleAuthenticationInfo(activeUser,password,this.getName());
    }
}
