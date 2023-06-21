package com.btc.member.controller;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class VerifyEmail {
		Properties acount=new Properties();
		{
			String path=VerifyEmail.class.getResource("/emailAcount.properties").getPath();
			try {
				acount.load(new FileReader(path));
			}catch(IOException e) {
				e.printStackTrace();
			}	
		}
		String user=acount.getProperty("user");
		String password=acount.getProperty("password");
		
	
	
	public String sendMail(String to) {
		String result=null;
		Properties prop=new Properties();
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port",465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session=Session.getDefaultInstance(prop,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		//인증코드 생성
		Random ran=new Random();
		
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<6;i++) {
			if(ran.nextBoolean()) {
				buffer.append(((int)(ran.nextInt(10))));
			}else {
				buffer.append((char)((int)(Math.random()*26)+65));
			}
		}
		
		MimeMessage message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("BTC이메일 인증");
			
			message.setText("인증코드: ["+buffer+"] 입니다");
			
			Transport.send(message);
			result=buffer.toString();
		}catch(AddressException e) {
			e.printStackTrace();
		}catch(MessagingException e) {
			e.printStackTrace();
		}return result;
	}
}




