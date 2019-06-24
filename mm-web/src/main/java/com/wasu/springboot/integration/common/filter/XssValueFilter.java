package com.wasu.springboot.integration.common.filter;

import com.alibaba.fastjson.serializer.ValueFilter;
import org.springframework.web.util.HtmlUtils;

public class XssValueFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        if(value != null && String.class.isAssignableFrom(value.getClass())){
            return HtmlUtils.htmlEscape((String) value);
        }
        return value;
    }
}
