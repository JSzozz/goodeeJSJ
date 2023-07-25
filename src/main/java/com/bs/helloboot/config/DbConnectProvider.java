package com.bs.helloboot.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bs.helloboot.dao.MemberDao;
import com.bs.helloboot.dto.MemberDto;

@Component
public class DbConnectProvider implements AuthenticationProvider{

	private MemberDao dao;
//	private SqlSession session;
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
	
	public DbConnectProvider(MemberDao dao/* , BCryptPasswordEncoder encoder */) {
		this.dao=dao;
		/* this.encoder=encoder; */
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		MemberDto loginMember = dao.selectMemberById(userId);
		
		if(loginMember==null||!encoder.matches(password, loginMember.getPassword())){
			throw new BadCredentialsException("인증실패");
		}
		return new UsernamePasswordAuthenticationToken(loginMember, loginMember.getPassword());
	}
 
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
