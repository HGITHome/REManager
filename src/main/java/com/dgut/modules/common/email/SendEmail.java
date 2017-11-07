package com.dgut.modules.common.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

	 public static final String HOST = "smtp.qq.com";
	    public static final String PROTOCOL = "smtp";  
	    public static final int PORT = 465;
	    public static final String FROM = "2465824792@qq.com";//发件人的email
	    public static final String PWD = "dixpfegxjvvmebfd";//发件人密码
	 
	    /**
	     * 获取Session
	     * @return
	     */
	    private static Session getSession() {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", HOST);//设置服务器地址
	        props.put("mail.store.protocol" , PROTOCOL);//设置协议
	        props.put("mail.smtp.port", PORT);//设置端口
	        props.put("mail.smtp.auth" , true);
	        props.put("mail.smtp.ssl.enable", "true");
	        Authenticator authenticator = new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(FROM, PWD);
	            }
	 
	        };
	        Session session = Session.getDefaultInstance(props , authenticator);
	 
	        return session;
	    }
	 
	    public static void send(String toEmail ,String subject, String content) {
	        Session session = getSession();
	        try {
	            System.out.println("--send--"+content);
	            // Instantiate a message
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(FROM));
	            InternetAddress[] address = {new InternetAddress(toEmail)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject(subject);
	            msg.setSentDate(new Date());
	            Multipart multipart = new MimeMultipart();
	            BodyPart contentPart = new MimeBodyPart();
	            //Set message attributes
	            // 设置邮件的文本内容
	            contentPart.setContent(content , "text/html;charset=utf-8");
	            multipart.addBodyPart(contentPart);
	            msg.setContent(multipart);      
	            msg.saveChanges();
	            //Send the message
	            Transport.send(msg);
	        }
	        catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
	    }
}
