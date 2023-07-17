package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.exception.AuthenticationException;
import com.bs.spring.member.model.dto.Member;

@Component
@Aspect
public class AuthenticationCheckAop {

	@Before("execution(* com.bs.spring.memo..*(..))")
	public void checkcheck(JoinPoint jp) {
		
		//로그인 정보(아이디)를 확인하여 admin이면 통과, admoin 아니면 막기
		//spring이 제공하는 RequestContextHolder클래스
		HttpSession session=(HttpSession) RequestContextHolder.currentRequestAttributes()
		.resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember==null||!loginMember.getUserId().equals("admin")) {
			throw new AuthenticationException("서비스 권한이 부족합니다[관리자 전용]");//자체 Exception만들기
		}
	}
}
