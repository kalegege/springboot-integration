package com.wasu.springboot.integration.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class CmdUtils {
    public static List<String> executeCmd(String cmd){
        List<String> strList = new ArrayList();
        Process process=null;
        LineNumberReader input=null;
        LineNumberReader error=null;
        InputStreamReader ir=null;
        InputStreamReader ie=null;
        String line;
        try {
            process=Runtime.getRuntime().exec(cmd);
            ir = new InputStreamReader(process.getInputStream(),"GBK");
            input = new LineNumberReader(ir);

            process.waitFor();
            while ((line = input.readLine()) != null){
                strList.add(line);
                System.out.println(line);
            }
            if(StringUtils.isEmpty(strList)){
                ie = new InputStreamReader(process.getErrorStream(),"GBK");
                error = new LineNumberReader(ie);
                while ((line = error.readLine()) != null){
                    strList.add(line);
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(ir != null){
                try{
                    ir.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(input != null){
                try{
                    input.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(ie != null){
                try{
                    ie.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(error != null){
                try{
                    error.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return strList;
    }

    public static void main(String[] args){
        executeCmd("ipconfig -all");
        executeCmd("java -version");
    }
}
