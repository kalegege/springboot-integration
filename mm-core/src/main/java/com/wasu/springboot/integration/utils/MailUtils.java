package com.wasu.springboot.integration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class MailUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtils.class);

    public static void main(String[] args) throws Exception{
        // 定义连接POP3服务器的属性信息
        String pop3Server = "pop.qq.com";
        String protocol = "imap";
        String username = "330790416@qq.com";
        String password_pop3= "cmngizqlmbgrbhjb"; // QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？
        String password = "enatjjudlnmlbihf";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", protocol); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址
        props.setProperty("mail."+protocol+".socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail."+protocol+".socketFactory.fallback","true");
        props.setProperty("mail."+protocol+".socketFactory.port","993");

        // 获取连接
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);

        // 获取Store对象
        Store store = session.getStore(protocol);
        store.connect(pop3Server, username, password); // POP3服务器的登陆认证

        // 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
        Folder folder = store.getFolder("INBOX");// 获得用户的邮件帐户
        folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限

        Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件

        for (Message message : messages) {
            String subject = message.getSubject();// 获得邮件主题
            Address from = (Address) message.getFrom()[0];// 获得发送者地址
            System.out.println("邮件的主题为: " + subject + "\t发件人地址为: " + from);
//            System.out.println("邮件的内容为：");
//            message.writeTo(System.out);// 输出邮件内容到控制台

            if(message.isMimeType("multipart/mixed") && subject.equals("EM9A1245")){
                //存在附件
                Multipart mp=(Multipart) message.getContent();
                File targetfile=new File("D:\\doc\\test.jpg");
                FileOutputStream os=new FileOutputStream(targetfile,true);
                message.writeTo(os);
                int length=mp.getCount();
                for(int i=0;i<length;i++){
                    BodyPart bodyPart=mp.getBodyPart(i);
                    System.out.println("name:"+bodyPart.getFileName()+"\tsize:"+bodyPart.getSize());
                }
            }
        }

        folder.close(false);// 关闭邮件夹对象
        store.close(); // 关闭连接对象
    }

}
