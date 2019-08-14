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


@Controller
public class NormalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NormalController.class);

    /**
     * http://q.10jqka.com.cn/zs/
     * 沪深指数
     * @return
     */
    @RequestMapping("/zs")
    @ResponseBody
    public void zs()  {

    }

//    /**
//     * 分页获取沪深指数表格数据
//     * @param order
//     * @param page
//     * @return
//     * @author yangbin3
//     * @date 2019/8/9
//     */
//    @RequestMapping("/zs/index/field/{field}/order/{order}/page/{page}/ajax/{index}/")
//    @ResponseBody
//    public String queryZSPage(@PathVariable(value = "order")String order,
//                            @PathVariable(value = "page")Integer page,
//                            @PathVariable(value = "field")String field,
//                            @PathVariable(value = "index")Integer index){
//        String s = HttpClientUtils.doGet("http://q.10jqka.com.cn/zs/index/field/"+field+"/order/" + order + "/page/" + page + "/ajax/"+index+"/");
//        return s;
//    }

    /**
     * http://q.10jqka.com.cn/index/fxjs/
     * 风险警示
     * @return
     */
    @RequestMapping("/index/fxjs")
    @ResponseBody
    public void fxjs(HttpServletRequest request){
        String page=(String)request.getAttribute("html");
        page=handleFxjs(page);
        request.setAttribute("html",page);
    }

    private String handleFxjs(String page){
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
     * 跳转风险警示公告（根据stype下拉选）
     * @param stype
     * @return
     * @author yangbin3
     * @date 2019/8/12
     */

    @RequestMapping("/index/fxjs/board/fxjsgg/stype/{stype}")
    @ResponseBody
    public void fxjsggStype(@PathVariable(value = "stype")String stype) throws Exception{

    }

    /**
     * 分页获取风险警示公告type类型表格数据
     * @param stype
     * @param page
     * @return
     * @author yangbin3
     * @date 2019/8/9
     */
    @RequestMapping("/index/fxjs/board/fxjsgg/stype/{stype}/page/{page}")
    @ResponseBody
    public String fxjsggStypePage(@PathVariable(value = "stype")String stype,
                                @PathVariable(value = "page")Integer page){
        String s = HttpClientUtils.doGet("http://q.10jqka.com.cn/index/fxjs/board/fxjsgg/stype/"+stype+"/page/"+page+"/");
        s=s.replaceAll("http://stockpage.10jqka.com.cn","/stockpage");
        return s;
    }

    /**
     * 分页获取风险警示表格数据
     * @param order
     * @param page
     * @return
     * @author yangbin3
     * @date 2019/8/9
     */
    @RequestMapping("/index/fxjs/board/fxjs/field/{field}/order/{order}/page/{page}/ajax/{index}/")
    @ResponseBody
    public String queryFXJSPage(@PathVariable(value = "order")String order,
                            @PathVariable(value = "page")Integer page,
                            @PathVariable(value = "field")String field,
                            @PathVariable(value = "index")Integer index){
        String s = HttpClientUtils.doGet("http://q.10jqka.com.cn/index/fxjs/board/fxjs/field/"+field+"/order/" + order + "/page/" + page + "/ajax/"+index+"/");
        s=s.replaceAll("http://stockpage.10jqka.com.cn","/stockpage");
        return s;
    }


    /**
     * 分页获取风险警示公告表格数据（tab页切换）
     * @param order
     * @param page
     * @return
     * @author yangbin3
     * @date 2019/8/9
     */
    @RequestMapping("/index/fxjs/board/fxjsgg/field/{field}/order/{order}/page/{page}/ajax/{index}/")
    @ResponseBody
    public String queryFXJSGGPage(@PathVariable(value = "order")String order,
                            @PathVariable(value = "page")Integer page,
                            @PathVariable(value = "field")String field,
                            @PathVariable(value = "index")Integer index){
        String s = HttpClientUtils.doGet("http://q.10jqka.com.cn/index/fxjs/board/fxjsgg/field/"+field+"/order/" + order + "/page/" + page + "/ajax/"+index+"/");
        s=s.replaceAll("http://stockpage.10jqka.com.cn","/stockpage");
        return s;
    }


    /**
     * http://q.10jqka.com.cn/gn/
     * 概念板块
     * @return
     */
    @RequestMapping("/gn")
    @ResponseBody
    public void gn(HttpServletRequest request){
        String result=(String)request.getAttribute("html");
        String js = HttpClientUtils.getPage("http://s.thsi.cn/js/q/newq/gnbk_v2.min.js");

        StringBuffer stringBuffer = new StringBuffer();
        //去掉 id = maincont
        result= StringUtils.hideById(result,"maincont");
        //去掉 <h2>概念时间表
        result=result.replace("<h2>概念时间表",
                "<h2 style=\"display:none;\">概念时间表");
        result = result.replaceAll("<script type=\"text/javascript\" src=\"http://s.thsi.cn/js/q/newq/gnbk_v2.min.js\"></script>", "");

        stringBuffer.append(result);
        stringBuffer.append("<script type='text/javascript'>");
        js = js.replaceAll("http://q.10jqka.com.cn", "");
        stringBuffer.append(js);
        stringBuffer.append("</script>");
        request.setAttribute("html",stringBuffer.toString());
    }

    /**
     * http://q.10jqka.com.cn/dy/
     * 地域板块
     * @return
     */
    @RequestMapping("/dy")
    @ResponseBody
    public void dy(){}


    /**
     * http://q.10jqka.com.cn/thshy/
     * 同花顺行业
     * @return
     */
    @RequestMapping("/thshy")
    @ResponseBody
    public void thshy(){}

    /**
     * http://q.10jqka.com.cn/zjhhy/
     * 证监会行业
     * @return
     */
    @RequestMapping("/zjhhy")
    @ResponseBody
    public void zjhhy(){}

    /**
     * http://q.10jqka.com.cn/xsb/
     * 新三板
     * @return
     */
    @RequestMapping("/xsb")
    @ResponseBody
    public void xsb(HttpServletRequest request){
        String html = (String)request.getAttribute("html");
        html=html.replaceAll("http://stockpage.10jqka.com.cn","/stockpage");
        request.setAttribute("html",html);
    }

    /**
     * 新三板列表和沪深指数列表
     * @param order
     * @param page
     * @param board
     * @param field
     * @param index
     * @return
     * @author yangbin3
     * @date 2019/8/12
     */

    @RequestMapping("/{board}/index/field/{field}/order/{order}/page/{page}/ajax/{index}/")
    @ResponseBody
    public String queryXSBAndHSZSData(@PathVariable(value = "order")String order,
                @PathVariable(value = "page")Integer page,
                @PathVariable(value = "board")String board,
                @PathVariable(value = "field")String field,
                @PathVariable(value = "index")Integer index){
        String s = HttpClientUtils.doGet("http://q.10jqka.com.cn/"+board+"/index/field/"+field+"/order/"+ order + "/page/" + page + "/ajax/" + index + "/");
        s=s.replaceAll("http://q.10jqka.com.cn","");
        s=s.replaceAll("http://stockpage.10jqka.com.cn","/stockpage");
        return s;
    }
}
