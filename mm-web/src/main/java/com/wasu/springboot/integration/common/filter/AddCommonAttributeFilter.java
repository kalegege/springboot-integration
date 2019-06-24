package com.wasu.springboot.integration.common.filter;

import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.entity.system.ActiveUser;
import com.wasu.springboot.integration.utils.StringUtils;
import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddCommonAttributeFilter extends OncePerRequestFilter {

    private static List<String> excludeUrls = Stream.of("/login","/error").collect(Collectors.toList());

    @Override
    public void destroy() {

    }

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpServletRequest=((HttpServletRequest)request);
        String url=httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        if(isInclude(url) || (StringUtils.isNoneBlank(url)) && excludeUrls.indexOf(".") > 0){
            chain.doFilter(httpServletRequest,response);
            return;
        }
        Map<String,Object> extraParams=new HashMap<>();
        ActiveUser activeUser=(ActiveUser)((HttpServletRequest)request).getSession().getAttribute(CommonConstant.USER_INFO_SESSION);
        if(activeUser != null){
            extraParams.put("creater",activeUser.getUserid());
            extraParams.put("updater",activeUser.getUserid());
            extraParams.put("orgId",activeUser.getOrgId());
        }
        RequestParameterWrapper requestParameterWrapper=new RequestParameterWrapper((HttpServletRequest)request);
        requestParameterWrapper.addParameters(extraParams);
        chain.doFilter(requestParameterWrapper,response);
    }

    private boolean isInclude(String url) {
        return excludeUrls.stream().anyMatch(str->str.equals(url));
    }
}
