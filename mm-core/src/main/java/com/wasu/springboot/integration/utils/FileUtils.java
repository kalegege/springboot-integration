package com.wasu.springboot.integration.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {

    public static byte[] read(File file)throws IOException {
        byte[] result=null;
        FileInputStream fs=null;
        try{
            fs=new FileInputStream(file);
            result=new byte[fs.available()];
            fs.read(result);
        }finally {
            if(fs != null){
                fs.close();
            }
            fs=null;
        }
        return result;
    }
}
