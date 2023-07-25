package com.bs.helloboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	boot에서 security 적용하기
//	1. 인증처리할 bean을 등록하기
//		인증관련설정하는 bean -> securityFilterChain bean 등록
//	2. 인증방법에 대한 설정 클래스를 등록
//	inMemory, DB연동방식 -> provider를 등록
//	
	@Bean
	public SecurityFilterChain authenticationPath(HttpSecurity http) throws Exception{
		
		return http.csrf().disable()
				.formLogin()
					.successForwardUrl("/successLogin")
					.failureForwardUrl("/errorLogin")
					.usernameParameter("userId")
					.passwordParameter("pw")
					.loginProcessingUrl("/login.do")
					.loginPage("/loginpage")
				.and()
				.authorizeHttpRequests()//interceptor를 등록하는 것과 동일한 기능
					.antMatchers("/loginpage").permitAll()
					.antMatchers("/**").hasAnyAuthority("admin","user")
				.and()
				.logout()
					.logoutSuccessUrl("/logout")
					.logoutUrl("/logout.do")
				.and()
				.authenticationProvider(null)
				.build();	
	}
	
}
