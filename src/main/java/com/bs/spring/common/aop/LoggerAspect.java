package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {

	public  void loggerBefore(JoinPoint jp) {
	log.debug("-----------AOP loogerBefore start-----------");
	Signature sig=jp.getSignature();
	//import org.aspectj.lang.Signature;
	log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
	log.debug("-----------AOP loogerBefore end-----------");
	}
	
	public void loggerAfter(JoinPoint jp) {
		log.debug("----- AOP loggerAfter -----");
		Signature sig=jp.getSignature();
		
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
				
		log.debug("----------------------------");
	}
}
