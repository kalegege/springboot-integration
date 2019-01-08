package com.wasu.springboot.integration.base.ribbon;

import java.util.Map;

public class CustomRequestContext {

    private String url;

    private Map<String,String> metadata;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
