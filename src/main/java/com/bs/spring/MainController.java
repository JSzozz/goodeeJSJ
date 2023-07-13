package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller //spring에서 controller역할을 하는 클래스에 적용하는 것
//springbean으로 등록된다
public class MainController {
	
	//log를 출력하기 위한 Logger 가져오기
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	//import org.slf4j.Logger;

			
	//@Controller로 등록된 클래스는 클라이언트가 요청한 서비스를 진행하는 메소드(매핑메소드)를 선언한다.
	//요청 URL주소 연결되는 메소드
	
	//@RequestMapping어노테이션을 메핑메소드 선언부에 선언을 한다
	//controller에 선언된 메소드는 일반적으로 String 값을 반환하게 설정함.
	//view를 선택해서 출력시킬 때
	@RequestMapping("/")
	public String main(HttpServletRequest req,
			HttpSession session,HttpServletResponse res) {
		//메소드가 반환하는 값은 viewResolver Bean이 처리함.(*servlet-context.xml)
		//<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		//<beans:property name="prefix" value="/WEB-INF/views/" />
		//<beans:property name="suffix" value=".jsp" />
		//</beans:bean>
		
		//등록된 InternalResourceResolver Bean은 반환된 문자열에
		//객체에 설정된 prefix, suffix를 붙여서 내부에서 화면출력파일을 찾는다.
		// ex. /WEB-INF/views/리턴값.jsp
		// RequestDispatcher().forward 의 괄호 안에 'ex.'값이 들어가는 처리가 이뤄짐
		
		//쿠키 추가하기
		Cookie c = new Cookie("testData", "cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		session.setAttribute("sessionId", "admin");
		
		
		//=============================================================
		//log4j를 이용해서 log출력하기
		//slf4j에서 제공하는 Logger인터페이스를 구현한 클래스를 이용함
		//LoggerFactory클래스에 static메소드인 getLogger(logger가져오는 클래스지정);
		
		//log를 출력할때는 logger가 제공하는 메소드를 이용
		//debug() : 개발시에 사용하는 로그를 출력할때 사용
		//info() : 프로그램 실행하는 정보를 출력할때 사용
		//warn() : 잘못된 사용을 했을때 출력할때 사용
		//error() : Exception 실행이 불가능한 기능에 대한 로그를 출력할때 사용
		// *메소드의 매개변수는 기본적으로 String만 가능, 객체나 다른 데이터를 출력하려면 ("{}",출력변변수)
		
		//level
		// debug -> info -> warn -> error -> fatal
		
		//log4j를 이용해서 로그 출력하기
		logger.debug("debug내용출력하기");
		logger.info("info내용출력하기");
		logger.warn("warn내용출력하기");
		logger.error("error내용출력하기");
		
		
		return "index";
	}
}
