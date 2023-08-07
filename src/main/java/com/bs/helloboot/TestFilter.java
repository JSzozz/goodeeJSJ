package com.bs.helloboot;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@WebFilter("/*")
@Slf4j
public class TestFilter implements Filter{//import javax.servlet.Filter;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.debug("========필터가 적용!========");
		chain.doFilter(request, response);
		
	}
	
}