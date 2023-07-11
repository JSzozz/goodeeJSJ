package com.bs.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //spring에서 controller역할을 하는 클래스에 적용하는 것
//springbean으로 등록된다
public class MainController {
	//@Controller로 등록된 클래스는 클라이언트가 요청한 서비스를 진행하는 메소드(매핑메소드)를 선언한다.
	//요청 URL주소 연결되는 메소드
	
	//@RequestMapping어노테이션을 메핑메소드 선언부에 선언을 한다
	//controller에 선언된 메소드는 일반적으로 String 값을 반환하게 설정함.
	//view를 선택해서 출력시킬 때
	@RequestMapping("/")
	public String main() {
		//메소드가 반환하는 값은 viewResolver Bean이 처리함.(*servlet-context.xml)
		//<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		//<beans:property name="prefix" value="/WEB-INF/views/" />
		//<beans:property name="suffix" value=".jsp" />
		//</beans:bean>
		
		//등록된 InternalResourceResolver Bean은 반환된 문자열에
		//객체에 설정된 prefix, suffix를 붙여서 내부에서 화면출력파일을 찾는다.
		// ex. /WEB-INF/views/리턴값.jsp
		// RequestDispatcher().forward 의 괄호 안에 'ex.'값이 들어가는 처리가 이뤄짐
		
		return "index";
		
	}
	
}
