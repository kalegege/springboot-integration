package com.wasu.springboot.integration.batch.controller;


import com.wasu.springboot.integration.constants.URLConstants;
import com.wasu.springboot.integration.utils.HttpClientUtils;
import com.wasu.springboot.integration.utils.StringUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * 香港市场页面
 */
@Controller
public class HKMarketController{

    private static final Logger LOGGER= LoggerFactory.getLogger(HKMarketController.class);


    /**
     * http://q.10jqka.com.cn/hk/indexYs/
     * @return
     */
    @RequestMapping("/hk/indexYs")
    @ResponseBody
    public void indexYs(HttpServletRequest request){
        String html=(String)request.getAttribute("html");
        html= StringUtils.hideByClass(html,
                Arrays.asList("more","j_addStock","m-page-tip","cf"));
        html=html.replaceAll("加自选","");
        html=html.replaceAll("http://stockpage.10jqka.com.cn","/stockpage/hk");
        html=handlehkJs(html);
        request.setAttribute("html",html);
    }

    /**
     * 获取香港市场的行情数据
     * @param order
     * @param page
     * @param board
     * @param field
     * @param index
     * @return
     * @author yangbin3
     * @date 2019/8/12
     */

    @RequestMapping("/hk/indexYs/board/{board}/field/{field}/order/{order}/page/{page}/ajax/{index}/")
    @ResponseBody
    public String queryHkIndexYsPage(@PathVariable(value = "order")String order,
                            @PathVariable(value = "page")Integer page,
                            @PathVariable(value = "board")String board,
                            @PathVariable(value = "field")String field,
                            @PathVariable(value = "index")Integer index){
        String html = HttpClientUtils.doGet("http://q.10jqka.com.cn/hk/indexYs/board/"+board+"/field/"+field+"/order/" + order + "/page/" + page + "/ajax/"+index+"/");
        html= StringUtils.hideByClass(html,
                Arrays.asList("more","j_addStock","m-page-tip","cf"));
        html=html.replaceAll("加自选","");
        html=html.replaceAll("http://stockpage.10jqka.com.cn","/stockpage/hk");
        return html;
    }

    /**
     *  处理js文件
     * @param page
     * @return
     * @author yangbin3
     * @date 2019/8/12
     */

    private String handlehkJs(String page){
        StringBuffer stringBuffer=new StringBuffer();
        String js= HttpClientUtils.getPage("http://s.thsi.cn/cb?js/q/newq/newzdfb_v4.js&20190428");
        System.out.println(js);
        //将生成沪深行情数据的js删掉
        page=page.replaceAll("newzdfb_v4.js","newzdfb_remove_v4.js");
        stringBuffer.append(page);
        stringBuffer.append("<script type='text/javascript'>");
        js=js.replaceAll("http://q.10jqka.com.cn","");
        stringBuffer.append(js);
        stringBuffer.append("</script>");
        return stringBuffer.toString();
    }

    @RequestMapping("/api")
    @ResponseBody
    public String api(@RequestParam(value = "t")String type){
        return HttpClientUtils.doGet(URLConstants.BASE_URL+"/api.php?t="+type);
    }
}
