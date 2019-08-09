package com.wasu.springboot.integration.system.remoting.impl;

import com.wasu.springboot.integration.base.BaseRemoting;
import com.wasu.springboot.integration.common.config.DynamicConfig;
import com.wasu.springboot.integration.entity.system.PubOperateRecord;
import com.wasu.springboot.integration.system.remoting.OperateRecordRemoting;
import com.wasu.springboot.integration.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class OperateRecordRemotingImpl extends BaseRemoting implements OperateRecordRemoting {

    @Autowired
    private DynamicConfig dynamicConfig;

    @Override
    public JsonResult insert(PubOperateRecord pubOperateRecord) {
        return null;
    }

    @Override
    public String getPage() {
        StringBuffer sb=new StringBuffer();
        try {
            URL url = new URL("http://q.10jqka.com.cn/");
            BufferedReader br=null;
            InputStream is=null;
            InputStreamReader isr=null;
            try{
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                is = url.openStream();
                isr = new InputStreamReader(is,"gbk");
                //为字符输入流添加缓冲
                br = new BufferedReader(isr);
                String data = br.readLine();//读取数据

                while (data!=null){//循环读取数据
                    System.out.println(data);//输出数据
                    data = br.readLine();
                    sb.append(data);
                }
                br.close();
                isr.close();
                is.close();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(null != br){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(null != isr){
                    try {
                        isr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(null != is){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args){
        OperateRecordRemoting operateRecordRemoting=new OperateRecordRemotingImpl();
        operateRecordRemoting.getPage();
    }
}
