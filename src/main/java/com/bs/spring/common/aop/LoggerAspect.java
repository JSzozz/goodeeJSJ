package com.bs.spring.common.aop;

import java.util.Map;

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
		
		Object[] arg=jp.getArgs();//메소드가 실행될때 전달되는 매개변수의 인수값을 가져옴
		if(arg!=null) {
			for(Object o : arg) {
				log.debug("{}",o);
				if(o instanceof Map) {//log 속에 넣고싶은 값 추가하기
					((Map) o).put("test", "째훈째훈");
				}
			}
		}
		log.debug("-----------AOP loogerBefore end-----------");
	}
	
	public void loggerAfter(JoinPoint jp) {
		log.debug("----- AOP loggerAfter start -----");
		Signature sig=jp.getSignature();
		
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
				
		log.debug("----- AOP loggerAfter end -----");
	}
}
