package com.wasu.springboot.integration.utils;

import java.io.*;

public class SerializeUtils {

    public static byte[] serialize(Object object){
        if(null == object){
            return null;
        }

        ObjectOutputStream oos=null;
        ByteArrayOutputStream baos=null;
        try{
            baos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes=baos.toByteArray();
            return bytes;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(null != baos){
                try{
                    baos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                baos=null;
            }
            if(null != oos){
                try{
                    oos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                oos=null;
            }
        }
        return new byte[0];
    }

    public static Object unserialize(byte[] bytes){
        if(null == bytes){
            return null;
        }

        ByteArrayInputStream bais=null;
        ObjectInputStream ois=null;

        try{
            bais=new ByteArrayInputStream(bytes);
            ois=new ObjectInputStream(bais);
            return ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != bais){
                try{
                    bais.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                bais=null;
            }
            if(null != ois){
                try{
                    ois.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                ois=null;
            }
        }
        return null;
    }
}
