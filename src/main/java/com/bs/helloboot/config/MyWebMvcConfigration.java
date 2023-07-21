package com.bs.helloboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bs.helloboot.comm.interceptor.LoggerInterceptor;
import com.bs.helloboot.websocket.ChattingServer;

@Configuration
@EnableWebSocket
public class MyWebMvcConfigration implements WebMvcConfigurer, WebSocketConfigurer{

	private ChattingServer chatting;
	public MyWebMvcConfigration(ChattingServer chatting) {
		this.chatting=chatting;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(chatting, "/chatting");
	}
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
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/member/*");
	}

	//cors에 대한 허용설정 : 기원이 달랐을 경우 처리하는 담당(cross origin)
	//
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000");
	}


	
}
