package com.wasu.springboot.integration.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.*;
import java.util.Map;

@Component("templateParseUtils")
public class TemplateParseUtils {

    private final String ENCODE="utf-8";

    @Autowired
    private FreeMarkerConfigurer configurer;

    public void parse(String templateName,String excelPath,Map<String,Object> data) throws IOException,TemplateException {
        Template template = configurer.getConfiguration().getTemplate(templateName,ENCODE);
        OutputStreamWriter writer=null;
        try{
            File file = new File(excelPath);
            writer=new OutputStreamWriter(new FileOutputStream(file),ENCODE);
            template.process(data,writer);
            writer.flush();
        }finally {
            writer.close();
        }
    }

    public byte[] parse(String templateName,Map<String,Object> data) throws IOException,TemplateException {
        Template template=configurer.getConfiguration().getTemplate(templateName,ENCODE);
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        Writer out = new OutputStreamWriter(outputStream,ENCODE);
        template.process(data,out);
        return outputStream.toByteArray();
    }
}
