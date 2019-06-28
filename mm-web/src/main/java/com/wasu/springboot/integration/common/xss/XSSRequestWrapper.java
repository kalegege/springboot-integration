package com.wasu.springboot.integration.common.xss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

    private static final Whitelist JSOUP_WHITE_LIST= Whitelist.basic();
    private static final Document.OutputSettings JSOUP_OUT_SETTING = new Document.OutputSettings();

    static {
        JSOUP_WHITE_LIST.removeTags("a");

        JSOUP_OUT_SETTING.prettyPrint(false);
    }

    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if(values == null){
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for(int i=0;i<count;i++){
            encodedValues[i]=stripXSS(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    @Override
    public String getParameter(String name) {
        String value=super.getParameter(name);
        return stripXSS(value);
    }

    private String stripXSS(String value) {
        if(value != null){
            value = HtmlUtils.htmlEscape(value);
            value = value.replaceAll("","");

            value= Jsoup.clean(value,"",JSOUP_WHITE_LIST,JSOUP_OUT_SETTING);
        }
        return value;
    }
}
