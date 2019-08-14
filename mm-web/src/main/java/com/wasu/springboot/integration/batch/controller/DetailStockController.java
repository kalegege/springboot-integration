package com.wasu.springboot.integration.batch.controller;


import com.wasu.springboot.integration.utils.DomUtils;
import com.wasu.springboot.integration.utils.HttpClientUtils;
import com.wasu.springboot.integration.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@Controller
public class DetailStockController {
    private final String URL = "http://stockpage.10jqka.com.cn/";

    private static final Logger LOGGER = LoggerFactory.getLogger(DetailStockController.class);

    /**
     * http://stockpage.10jqka.com.cn/002256/
     * 只能隐藏的原因是，改变了层级结构会导致生成的获取数据的url不对，url是通过js生成的，找不到
     *
     * @param response
     * @param code
     * @return
     * @author 王越
     * @date 2019/8/12 9:53
     */
    @RequestMapping("/stockpage/{code}")
    @ResponseBody
    public void stockPageHS(HttpServletResponse response, @PathVariable String code) throws Exception {
        String html = HttpClientUtils.getPage(URL + code, "utf-8");
        String[] hideClasses = {"line", "adplace ad2 clearfix", "m_cont_3", "interface interface_2", "m_title_0 m_title_1 tit", "sub_cont_2", "sub_cont_6 hSty3", "rec-pop", "sub_cont_5 hSty4", "sub_cont_6 hSty4", "main_cont_1 m_s_l", "main_cont_2", "ad-fix-left", "right_float fix", "f_box m_s_l"};
        String[] hideIds = {"ad1", "in_channelhead", "in_datachannel", "in_squote", "in_menu", "newsright_fieldnews", "imgWrap", "ad4", "gegugp_zjjp", "sscjfb", "xwgg", "ad6", "footer"};
        html = StringUtils.hideByClass(html, Arrays.asList(hideClasses));
        html = StringUtils.hideById(html, Arrays.asList(hideIds));

        // 修改样式，隐藏百度图标
        if (StringUtils.isNotBlank(html)) {
            int index = html.indexOf("<head>");
            if (index > 0) {
                String prev = html.substring(0, index);
                String suffix = html.substring(index + 6, html.length());
                String style = "<style>a img{display:none;} .ad09{display:none;}</style>";
                html = prev + style + suffix;
            }
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(html);
    }

    /**
     * http://stockpage.10jqka.com.cn/HK0651/
     *
     * @param response
     * @param code
     * @return
     * @author 王越
     * @date 2019/8/12 9:53
     */
    @RequestMapping("/stockpage/hk/{code}")
    @ResponseBody
    public void stockPageHK(HttpServletResponse response, @PathVariable String code) throws Exception {
        String html = HttpClientUtils.getPage(URL + code, "utf-8");
        String[] doms = {"div[class=main_cont_0]"};
        html = DomUtils.selectDom(html, doms);
        html = html.replaceAll("../HQ_v4.html", "http://stockpage.10jqka.com.cn/HQ_v4.html");
        html = html.replaceAll("<body", "<body style=\"text-align: center;\"");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(html);
    }
}
