package com.wasu.springboot.integration.batch.controller;


import com.wasu.springboot.integration.utils.HttpClientUtils;
import com.wasu.springboot.integration.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DetailGNController {

    private final String URL = "http://q.10jqka.com.cn/gn/detail/code/";

    private static final Logger LOGGER = LoggerFactory.getLogger(DetailGNController.class);

    /**
     * 概念详情
     *
     * @param code
     */
    @RequestMapping("/gn/detail/code/{code}")
    @ResponseBody
    public void detailCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String code) {
        checkHtml(request);
    }

    /**
     * 指数详情
     *
     * @param code
     */
    @RequestMapping("/zs/detail/code/{code}")
    @ResponseBody
    public void detailZSCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String code) {
        checkHtml(request);
    }

    /**
     * 地域详情
     *
     * @param code
     */
    @RequestMapping("/dy/detail/code/{code}")
    @ResponseBody
    public void detailDYCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String code) {
        checkHtml(request);
    }

    /**
     * 证监会行业详情
     *
     * @param code
     */
    @RequestMapping("/zjhhy/detail/code/{code}")
    @ResponseBody
    public void detailZJHHYCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String code) {
//        String html = (String)request.getAttribute("html");
//        request.setAttribute("html",deleteSelf(html));
        checkHtml(request);
    }

    /**
     * 同花顺行业详情
     *
     * @param code
     */
    @RequestMapping("/thshy/detail/code/{code}")
    @ResponseBody
    public void detailTHSHYCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String code) {
        checkHtml(request);
    }


    //http://localhost:13980/gn/detail/field/264648/order/desc/page/2/ajax/1/code/301259
    @RequestMapping("/{module}/detail/field/{field}/order/{order}/page/{page}/ajax/1/code/{code}")
    @ResponseBody
    public String queryPage(@PathVariable(value = "module") String module,
                            @PathVariable(value = "field") String field,
                            @PathVariable(value = "order") String order,
                            @PathVariable(value = "page") String page,
                            @PathVariable(value = "code") String code) {
        String s = HttpClientUtils.doGet("http://q.10jqka.com.cn/" + module + "/detail/field/" + field + "/order/" + order + "/page/" + page + "/ajax/1/code/" + code + "/");
        s = s.replaceAll("加自选", "");
        s = s.replaceAll("<a class=\"j_addStock\"", "<a class=\"j_addStock\" style='display:none'");
        s = s.replaceAll("http://stockpage.10jqka.com.cn/","/stockpage/");
        return s;
    }

    /**
     * do some checking
     *
     * @param request
     */
    private void checkHtml(HttpServletRequest request) {
        //http://s.thsi.cn/js/home/v6/weblogin_v3.20170609.js
        String js = HttpClientUtils.getPage("http://s.thsi.cn/js/home/v6/weblogin_v3.20170609.js");
        String js_draw = HttpClientUtils.getPage("http://s.thsi.cn/js/q/newq/gn_main_v3.js");

        js_draw = js_draw.replaceAll("//@charset \"gbk\";", "");
        String html = (String) request.getAttribute("html");
        html = html.replaceAll("<script type=\"text/javascript\" src=\"http://s.thsi.cn/js/home/v6/weblogin_v3.20170609.js\"></script>", "");
        html = html.replaceAll("<script type=\"text/javascript\" src=\"http://s.thsi.cn/js/q/newq/gn_main_v3.js\"></script>", "");
        html = html.replaceAll("http://stockpage.10jqka.com.cn/","/stockpage/");
        html = deleteSelf(html);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(html);

        stringBuffer.append("<script type='text/javascript'>");
        js = js.replaceAll("document.domain=\"10jqka.com.cn\";", "");
        stringBuffer.append(js);
        stringBuffer.append(js_draw);
        stringBuffer.append("</script>");
        request.setAttribute("html", stringBuffer.toString());
    }

    private String deleteSelf(String html) {
        String result = html;
        result = StringUtils.hideByClass(html, "j_addStock");
        result = result.replaceAll("加自选", "");
        return result;
    }
}
