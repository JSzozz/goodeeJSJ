package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AnnoLoggerAspect {

	//pointcut 설정
	@Pointcut("within(com.bs.spring.member..*)")
	public void loggerTest() {}

	//advisor 설정
	@Before("loggerTest()")
	public void loggerBefore(JoinPoint jp){//Joinpoint (x)
		log.debug("============== annotation aop before start========");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("============== annotation aop before end========");
	}
	
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void memoLogger() {}
	
	@After("memoLogger()")
	public void afterLogger(JoinPoint jp) {
		log.debug("======= annotation aop after ========");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("============== aop after ==================");
	}
	
	
}
