package com.btc.common.filter;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncryptoWrapper extends HttpServletRequestWrapper {

	public PasswordEncryptoWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	private String getSha512(String value) {
		//암화된 비밀번호
		String encPw=null;
		//암호화 알고리즘 불러오기위한 객체
		MessageDigest md=null;
		
		try {
			md=MessageDigest.getInstance("SHA-512");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//byte단위로 암호화 처리
		
		
		byte[] bytes=value.getBytes(Charset.forName("UTF-8"));
			//update메소드를 이용해 byte값을 단방향 암호화
			
		
		
		md.update(bytes);
		//String으로 변환해 encPw에 넣어준다
		encPw=Base64.getEncoder().encodeToString(md.digest());
		return encPw;
	}
	
	@Override
	public String getParameter(String data) {
		String val="";
		
		if(data.equals("password")) {
			String password=super.getParameter(data);
			
			String encdata=getSha512(password);
			
			val=encdata;
		}else {
			val=super.getParameter(data);
		}return val;
	}

}









