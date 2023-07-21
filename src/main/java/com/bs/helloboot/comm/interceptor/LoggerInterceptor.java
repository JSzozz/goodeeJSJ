package com.bs.helloboot.comm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{

	//Interceptor 설정
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("=====preHandle실행전=====");
		log.debug(request.getRequestURI());
		log.debug("=====preHandle실행후=====");
		return true;
	}

	
}
