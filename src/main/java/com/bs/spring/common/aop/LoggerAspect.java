package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {

	public  void loggerBefore(JoinPoint jp) {
	log.debug("-----------AOP loogerBefor start-----------");
	log.debug("-----------AOP loogerBefor end-----------");
	}
}
