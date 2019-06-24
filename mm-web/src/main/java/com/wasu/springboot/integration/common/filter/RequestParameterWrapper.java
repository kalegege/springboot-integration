package com.wasu.springboot.integration.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class RequestParameterWrapper extends HttpServletRequestWrapper {

    private Map<String,String[]> params=null;

    public RequestParameterWrapper(HttpServletRequest request) {
        super(request);
        this.params=new HashMap<>(request.getParameterMap());
    }

    public RequestParameterWrapper(HttpServletRequest request,Map<String,Object> extraParams) {
        super(request);
        addParameters(extraParams);
    }

    public void addParameters(Map<String, Object> extraParams) {
        for (Map.Entry<String,Object> entry:extraParams.entrySet()) {
            addParameters(entry.getKey(),entry.getValue());
        }
    }

    private void addParameters(String name,Object value) {
        if(null != value){
            if(value instanceof String[]){
                params.put(name,(String[])value);
            }else if(value instanceof String){
                params.put(name,new String[]{(String)value});
            }else{
                params.put(name,new String[]{String.valueOf(value)});
            }
        }
    }

    public Map<String, String[]> getParams() {
        return params;
    }

    public void setParams(Map<String, String[]> params) {
        this.params = params;
    }

    @Override
    public String getParameter(String name) {
        String[] values=params.get(name);
        if(null == values || values.length == 0){
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> nameList=new Vector<>();
        for (Map.Entry<String, String[]> entry :params.entrySet()){
            nameList.add(entry.getKey());
        }
        return nameList.elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }
}
