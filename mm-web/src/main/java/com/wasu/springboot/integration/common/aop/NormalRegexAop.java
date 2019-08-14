package com.wasu.springboot.integration.common.aop;


import com.wasu.springboot.integration.constants.URLConstants;
import com.wasu.springboot.integration.utils.HttpClientUtils;
import com.wasu.springboot.integration.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Order(0)
public class NormalRegexAop {
    private static final Logger LOGGER= LoggerFactory.getLogger(NormalRegexAop.class);

    private static final List<String> urlList=Arrays.asList("/zs","/index/fxjs","/gn",
            "/dy","/thshy","/zjhhy","/xsb","/index","/hk/indexYs","/index/fxjs/board/fxjsgg/stype/all",
            "/index/fxjs/board/fxjsgg/stype/cx","/index/fxjs/board/fxjsgg/stype/ss");

//    @Pointcut("execution(* getNormal*(..))")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void point(){}

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url=request.getRequestURI();
        LOGGER.info("NormalRegexAop 请求链接" + url);
        String html =getNormalPage(request);
        //判断需要全局替换的页面
        if(isNeedCheck(url)){
            try {
                html=doCheckNormal(html);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("html",doCheckNormal(html));
        HttpServletResponse response = attributes.getResponse();
        //执行方法
        result=joinPoint.proceed();
        //返回页面
        if(isNeedCheck(url)){
            returnPage(response,request);
        }
        return result;
    }

    /**
     * 判断是否需要做处理
     * @param url
     * @return
     */
    private boolean isNeedCheck(String url){
        boolean flag = false;
        for(String tmp:urlList){
            if(url.equals(tmp) || url.equals(tmp + "/") || url.contains("/detail/code/")){
                flag=true;
                return flag;
            }
        }
        return flag;
    }

    /**
     * 生成请求同花顺的链接
     * @param request
     * @return
     */
    public String generateURL(HttpServletRequest request){
        return URLConstants.BASE_URL + request.getRequestURI().replace("/xinhua-dataInterface-web","");
    }

    /**
     * 回写数据给页面
     * @param response
     * @param request
     * @throws Exception
     */
    public void returnPage(HttpServletResponse response, HttpServletRequest request) throws Exception{
        response.setContentType("text/html;charset=GBK");
        response.getWriter().write((String)request.getAttribute("html"));
    }

    /**
     * 获取页面所有字符
     * @param request
     * @return
     */
    private String getNormalPage(HttpServletRequest request){
        return HttpClientUtils.getPage(generateURL(request));
    }

    /**
     * 获取页面所有字符
     * @param html
     * @return
     */
    private String doCheckNormal(Object html){
        String result="";
        if(html instanceof String){
            result=(String)html;
        }
        //去掉不需要的菜单
        result= StringUtils.hideByHref(result,Arrays.asList("http://q.10jqka.com.cn/usa/",
                "http://q.10jqka.com.cn/eu/","http://q.10jqka.com.cn/global/","http://data.10jqka.com.cn"));
        //去掉不需要的div
        result=StringUtils.hideByClass(result,
                Arrays.asList("bottom-map-warp","bottom-link","login-box"));
        //title="行情中心"
        result=result.replace("title=\"行情中心\"",
                "style=\"display:none;\" title=\"行情中心\"");
        //首页秃瓢添加样式
        result=result.replace("alt=\"同花顺\"","alt=\"同花顺\" style=\"\n" +
                "    width: 115px;\n" +
                "    height: 35px;\n" +
                "\"");
        //去掉图标的跳转
        result=result.replace("href=\"http://www.10jqka.com.cn\"","");
        //修改跳转首页的链接
        result=StringUtils.changeHrefByNameAndUrl(result,"沪深市场","http://q.10jqka.com.cn/","http://q.10jqka.com.cn/index");
        result=result.replace(" <a href=\"http://q.10jqka.com.cn/\" ",
                " <a href=\"http://q.10jqka.com.cn/index\" ");
        //修改页面为系统的链接
        result=result.replaceAll("http://q.10jqka.com.cn/hk/","http://q.10jqka.com.cn/hk/indexYs");
        //去掉请求链接前缀
        result=result.replaceAll("http://q.10jqka.com.cn","");
        return result;
    }
}
