package com.eis.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public static void sendMail(String subject,String content,String... to){
		if(to == null || to.length == 0){
			return;
		}
		String host = "aicdb";
		String from = "notify@aicdb.com";
		Properties p = System.getProperties();
		p.put("mail.smtp.host", host);
		p.put("mail.smtp.auth", true);
		Session session = Session.getDefaultInstance(p,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("notify", "abc123");
			}
		});
		try{
			MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(from));  
            for(String t : to){
            	if(!CheckUtil.isEmptry(t)){
            		message.addRecipient(Message.RecipientType.TO, new InternetAddress(t)); 
            	}
            }
            message.setSubject(subject);  
            message.setText(content);  
            Transport tp = session.getTransport("smtp");
            Transport.send(message);    
  
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }  
	}
	
	public static void main(String[] args) {
		String[] to = {"wangzhen@allinfinance.com"};
		String host = "smtp.qq.com";
		String from = "wangzhen@allinfinance.com";
		Properties p = System.getProperties();
		p.put("mail.smtp.host", host);
		p.put("mail.smtp.auth", true);
		Session session = Session.getDefaultInstance(p,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("wangzhen@allinfinance.com", "Pass2015");
			}
		});
		try{
			MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress(from));  
            for(String t : to){
            	if(!CheckUtil.isEmptry(t)){
					message.addRecipient(Message.RecipientType.TO,
							new InternetAddress(t));
				}
			}

			message.setSubject("test");
			message.setText("test");
			session.getTransport("smtp").send(message);

			session.getTransport("smtp").close();

		} catch (MessagingException e) {
			e.printStackTrace();  
        }  
	}
	
}
