package com.bs.spring.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.controller.DemoController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler)
			throws Exception {
		//반환형이 boolean true반환하면 매핑메소드가 실행, false반환하면 매핑메소드 실행을 하지 않음
		log.debug("----- 인터셉터 prehandle 실행 ----");
		log.debug(request.getRequestURI());
		Map params=request.getParameterMap();
		for(Object key : params.keySet()) {
			System.out.println(key);
		}
		log.debug("-------------end--------------");
		//response.sendRedirect(request.getContextPath());
		//handler -> 실행되는 controller클래스, 실행되는 메소드확인
		HandlerMethod hm=(HandlerMethod)handler;
		log.debug("{}",hm.getBean());
		DemoController demo=(DemoController)hm.getBean();
		
		log.debug("{}",hm.getMethod());
		Method m=hm.getMethod();
	//	m.invoke(m, null)
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
