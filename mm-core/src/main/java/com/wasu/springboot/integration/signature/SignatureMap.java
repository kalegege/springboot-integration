package com.wasu.springboot.integration.signature;

import java.util.*;

/**
 * @author 吴国庆
 * @date 2019/5/5-10:19
 * @description: TODO
 */
public class SignatureMap extends HashMap<String,String> {

    /**
     * 生成字典升序排序请求参数字符串
     * @return
     */
    public String sortAsc () {

        if (this.size() < 1) {
            return "";
        }

        List<String> keys = new ArrayList<>(this.keySet());
        Collections.sort(keys);

        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = keys.iterator();
        String key = iterator.next();
        builder.append(key);
        builder.append("=");
        builder.append(this.get(key));
        while (iterator.hasNext()) {
            builder.append("&");
            key = iterator.next();
            builder.append(key);
            builder.append("=");
            builder.append(this.get(key));
        }

        return builder.toString();
    }
}
