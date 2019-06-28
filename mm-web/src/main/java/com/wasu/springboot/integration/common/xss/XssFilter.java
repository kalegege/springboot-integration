package com.wasu.springboot.integration.common.xss;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class XssFilter implements Filter {

    private static final Logger LOGGER= LoggerFactory.getLogger(XssFilter.class);

    private static final String[] URL_PATHS={""};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String urlpath=((HttpServletRequest) request).getServletPath();
        if(checkSxx(urlpath)){
            chain.doFilter(new XSSRequestWrapper((HttpServletRequest)request),response);
        }else{
            chain.doFilter(request,response);
        }
    }

    /**
     * 是否进行xss过滤
     * @param urlpath
     * @return
     */
    private boolean checkSxx(String urlpath) {
        return !(StringUtils.isNotBlank(urlpath) && Arrays.asList(URL_PATHS).contains(urlpath));
    }

    @Override
    public void destroy() {

    }
}
