package com.wasu.springboot.integration.utils.file;

import com.wasu.springboot.integration.constants.CommonConstant;
import com.wasu.springboot.integration.exceptions.ServiceException;
import com.wasu.springboot.integration.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class ImageZipUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageZipUtil.class);


    /**
     * 对图片进行压缩
     * @param inputStream
     * @param fileName
     * @param width
     * @param height
     */
    public static void zipImageFile(InputStream inputStream, String fileName, int width, int height) {
        if(inputStream == null){
            return;
        }

        String srcImgpath=System.getProperty("user.dir").replace("bin","temp")+ "/"
                            + DateUtils.formatDateTime(new Date(),"yyMMddHHmmssSSS") + fileName;
        File newFile =new File(srcImgpath);
        try{
            Image srcFile= ImageIO.read(inputStream);
            int w=srcFile.getWidth(null);
            int h=srcFile.getHeight(null);
            double bili;
            if(width > 0){
                bili=width /(double)w;
                height=(int)(h*bili);
            }else{
                if(height > 0){
                    bili=height/(double)h;
                    width=(int)(w*bili);
                }
            }
            String subFix=srcImgpath.substring(srcImgpath.lastIndexOf(".")+1,srcImgpath.length());
            BufferedImage bufferedImage=null;
            if(subFix.equals("png")){
                bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            }else{
                bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            }

            Graphics2D graphics2D=bufferedImage.createGraphics();
            graphics2D.setBackground(new Color(255,255,255));
            graphics2D.setColor(new Color(255,255,255));
            graphics2D.fillRect(0,0,width,height);
            graphics2D.drawImage(srcFile.getScaledInstance(width,height,Image.SCALE_SMOOTH),0,0,null);
            ImageIO.write(bufferedImage,subFix,newFile);
            newFile.delete();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.warn("文件压缩失败");
            throw new ServiceException(CommonConstant.FAILURE,"上传的图片不是标准图片格式");
        }
    }
}
