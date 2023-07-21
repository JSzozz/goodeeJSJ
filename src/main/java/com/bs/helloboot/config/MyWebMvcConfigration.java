package com.bs.helloboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigration implements WebMvcConfigurer{

	//view에 대한 설정
	//기본 화면전환에 대한 설정을 하는 메소드를 재정의할 수 있다
	//alt shift s v 
	// addViewControllers
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/test").setViewName("test");
	}

	
	
	//Interceptor 설정
	//cors에 대한 허용설정 : 기원이 달랐을 경우 처리하는 담당(cross origin)
	//
}
