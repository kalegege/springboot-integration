package com.wasu.springboot.integration.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 元素节点操作工具类
 * Created by viruser on 2019/8/9.
 */
public class DomUtils {

    /**
     * 保留节点，去除body下的所有div，然后填充如doms里选择的节点，这样可以保留样式和script
     * 没有办法获取该节点下的子节点
     *
     * @param html
     * @param doms
     * @return
     * @author 王越
     * @date 13:27
     */
    public static String selectDom(String html, List<String> doms) {
        if (CollectionUtils.isEmpty(doms) || StringUtils.isBlank(html)) {
            return html;
        }
        Document parse = Jsoup.parse(html);
        List<Element> elements = new ArrayList<>();
        doms.forEach(e -> {
            Elements select = parse.select(e);
            elements.add(select.first());
        });

        parse.select("body div").remove();
        Element body = parse.select("body").first();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            body.prependChild(element);
        }
        return parse.toString();
    }

    /**
     * 保留节点，去除body下的所有div，然后填充如doms里选择的节点，这样可以保留样式和script
     * 没有办法获取该节点下的子节点
     *
     * @param html
     * @param doms
     * @return
     * @author 王越
     * @date 13:27
     */
    public static String selectDom(String html, String[] doms) {
        if (doms == null || doms.length == 0) {
            return html;
        }
        return selectDom(html, Arrays.asList(doms));
    }

    /**
     * 删除list中的dom元素节点
     *
     * @param html
     * @param doms {"div[id=ad1]","div[class=ad1]"}
     * @return
     * @author 王越
     * @date 10:55
     */
    public static String removeDom(String html, List<String> doms) {
        if (CollectionUtils.isEmpty(doms) || StringUtils.isBlank(html)) {
            return html;
        }
        Document document = Jsoup.parse(html);
        doms.forEach(e -> {
            document.select(e).remove();
        });
        html = document.toString();
        return html;
    }

    /**
     * 删除list中的dom元素节点
     *
     * @param html
     * @param doms {"div[id=ad1]","div[class=ad1]"}
     * @return
     * @author 王越
     * @date 10:55
     */
    public static String removeDom(String html, String[] doms) {
        if (doms == null || doms.length == 0) {
            return html;
        }
        return removeDom(html, Arrays.asList(doms));
    }
}
