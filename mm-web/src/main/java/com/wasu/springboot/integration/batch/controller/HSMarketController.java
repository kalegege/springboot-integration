package com.wasu.springboot.integration.batch.controller;

import com.wasu.springboot.integration.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 沪深市场页面
 */
@Controller
public class HSMarketController{

    private static final Logger LOGGER= LoggerFactory.getLogger(HSMarketController.class);


    /**
     * http://q.10jqka.com.cn/
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public void indexYs(HttpServletRequest request) throws Exception{
        String html = (String)request.getAttribute("html");
        html=this.handleIndex(html);
        request.setAttribute("html",html);
    }

    /**
     * 首页数据处理
     * @param page
     * @return
     * @author yangbin3
     * @date 2019/8/12
     */

    public String handleIndex(String page) throws Exception{
        //第二步获取沪深市场行情数据js文件
        String js= HttpClientUtils.getPage("http://s.thsi.cn/cb?js/q/newq/hssc_v1.min.js&20180912");
        //第三步，将生成沪深行情数据的js删掉
        page=page.replaceAll("hssc_v1.min.js","hssc_remove_v1.min.js");
        page=page.replaceAll("加自选","");
        page=page.replaceAll("<a class=\"j_addStock\"","<a class=\"j_addStock\" style='display:none'");
        page=page.replaceAll("http://stockpage.10jqka.com.cn","/stockpage");
        //将js拼接到页面后
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(page);
        stringBuffer.append("<script type='text/javascript'>");
        //第四步，将获取行情数据的接口为修改为调用本地 /xinhua-dataInterface-web
        js=js.replaceAll("http://q.10jqka.com.cn/api.php\\?t=indexflash&","/getSSMarketData");
        stringBuffer.append(js);
        stringBuffer.append("</script>");
        String s = stringBuffer.toString().replaceAll("//@charset \"gbk\";", "")
                .replaceAll("// type: 'logarithmic',", "")
                .replaceAll("// 定义最小值", "")
                .replaceAll("//涨跌分布", "")
                .replaceAll("//涨跌停", "").replaceAll("//昨天涨停今日收益", "").replaceAll("//大盘评级", "");
        return s;
    }

    /**
     *  获取沪深行情数据
     * @param
     * @return
     * @author yangbin3
     * @date 2019/8/9
     */
    @RequestMapping("/getSSMarketData")
    @ResponseBody
    public String getSSMarketData(){
        return HttpClientUtils.doGet("http://q.10jqka.com.cn/api.php?t=indexflash&");
    }


    /**
     * 分页获取表格数据
     * @param order
     * @param page
     * @return
     * @author yangbin3
     * @date 2019/8/9
     */
    @RequestMapping("/index/index/board/{board}/field/{field}/order/{order}/page/{page}/ajax/{index}/")
    @ResponseBody
    public String queryPage(@PathVariable(value = "order")String order,
                            @PathVariable(value = "page")Integer page,
                            @PathVariable(value = "board")String board,
                            @PathVariable(value = "field")String field,
                            @PathVariable(value = "index")Integer index){
        String s = HttpClientUtils.doGet("http://q.10jqka.com.cn/index/index/board/"+board+"/field/"+field+"/order/" + order + "/page/" + page + "/ajax/"+index+"/");
        s=s.replaceAll("加自选","");
        s=s.replaceAll("<a class=\"j_addStock\"","<a class=\"j_addStock\" style='display:none'");
        s=s.replaceAll("http://stockpage.10jqka.com.cn","/stockpage");
        return s;
    }
}
