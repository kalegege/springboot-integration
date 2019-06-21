package com.wasu.springboot.integration.common.tag;

import com.wasu.springboot.integration.batch.controller.HelloController;
import com.wasu.springboot.integration.utils.CacheUtil;
import com.wasu.springboot.integration.utils.SpringUtils;
import com.wasu.springboot.integration.utils.StringUtils;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ConstantTag implements TemplateDirectiveModel {

    private static final Logger LOGGER= LoggerFactory.getLogger(ConstantTag.class);

    private static final String PARAM_NAME_TYPE="type";

    private static final String PARAM_NAME_SHOW="show";

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params, TemplateModel[] models, TemplateDirectiveBody body) throws TemplateException, IOException {
        TemplateModel paramType = (TemplateModel)params.get(PARAM_NAME_TYPE);
        TemplateModel paramShow = (TemplateModel)params.get(PARAM_NAME_SHOW);

        String type = null;
        if(paramType != null){
            type = ((SimpleScalar)paramType).getAsString();
        }
        String show = null;
        if(paramType != null){
            show = ((SimpleScalar)paramShow).getAsString();
        }

        if(StringUtils.isNotEmpty(type)){
            try{
                Writer out =env.getOut();

                String result = null;
                if(!StringUtils.isBlank(show)){
                    CacheUtil cacheUtil= SpringUtils.getBean("cacheUtils");
                    show = show.trim();
                    String key ="";
                    if(show.contains(" ")){
                        String[] arr=show.split(" ");
                        for(int i=0;i<arr.length;i++){
                            arr[i]=cacheUtil.get(key+arr[i]);
                        }
                        result=StringUtils.join(arr,",");
                    }else{
                        result=cacheUtil.get(key + show);
                    }

                    if(null == result){
                        out.write("");
                    }else{
                        out.write(result);
                    }
                }else{
                    out.write("");
                }
                if(body != null){
                    body.render(out);
                }
            }catch(IOException e){
                LOGGER.warn(StringUtils.getStackTraceAsString(e));
            }catch(Exception e){
                LOGGER.warn(StringUtils.getStackTraceAsString(e));
            }
        }
    }

    public static enum ConstantType{
        TYPE_LEGALHIERARCHY("1","试用范围"),TYPE_PUBLISHER("2","发文单位"),
        TYPE_VIOLATIONTYPE("3","违规类型"),TYPE_PENALTYTYPE("4","出发类型");

        private String keyword;

        private String desc;

        public static ConstantType getEnum(String keyword){
            ConstantType resultEnum =null;
            ConstantType[] enumAry=ConstantType.values();
            for(int i =0;i<enumAry.length;i++){
                if(enumAry[i].getKeyword().equals(keyword)){
                    resultEnum = enumAry[i];
                    break;
                }
            }
            return resultEnum;
        }

        ConstantType(String keyword, String desc) {
            this.keyword = keyword;
            this.desc = desc;
        }

        public String getKeyword() {
            return keyword;
        }

        public String getDesc() {
            return desc;
        }
    }
}
