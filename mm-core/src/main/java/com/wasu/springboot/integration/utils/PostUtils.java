package com.wasu.springboot.integration.utils;

import com.wasu.springboot.integration.base.ribbon.CustomRequestContext;
import com.wasu.springboot.integration.constants.MicroConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PostUtils {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public <T> T postForObject(String serviceId, String path, Map<String,Object> params, Class<T> responseType){
        CustomRequestContext context=resolveUri(serviceId,path);

        Map<String,String> headers=new HashMap<>();
        if(context != null && context.getMetadata() != null){
            headers.put(MicroConstants.HEADER_REGISTER_TOKEN_KEY,
                    context.getMetadata().get(MicroConstants.HEADER_REGISTER_TOKEN_KEY));
        }

        String result= HttpClientUtils.postForJson(context == null?null:context.getUrl(),params,"utf-8",headers);

        if(String.class.equals(responseType)){
            return (T)result;
        }
        return JSONUtils.parseObject(result,responseType);
    }

    private CustomRequestContext resolveUri(String url, String path) {
        if(org.apache.commons.lang3.StringUtils.isBlank(url)){
            return null;
        }

        String domain=null;

        CustomRequestContext result=new CustomRequestContext();
        if(url.startsWith("http")){
            domain=url;
        }else{
            ServiceInstance serviceInstance=loadBalancerClient.choose(url);
            if(null == serviceInstance){
                return null;
            }
            domain=String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort());

            if(serviceInstance.getMetadata() != null && serviceInstance.getMetadata().get("context.path") != null){
                domain= StringUtils.join(domain,serviceInstance.getMetadata().get("context.path"));
            }

            result.setMetadata(serviceInstance.getMetadata());
        }

        String szUri=null;
        if(path == null){
            return null;
        }

        if(path.startsWith("/")){
            szUri=String.format("%s%s",domain,path);
        }else{
            szUri=String.format("%s/%s",domain,path);
        }
        result.setUrl(szUri);
        return result;
    }
}
