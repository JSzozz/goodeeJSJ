package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.demo.model.dto.Demo;

@Controller
public class DemoController {

	@RequestMapping("/demo/demo.do")
	public String demo() {
		// /WEB-INF/views/demo/demo.jsp
		// prefix : /WEB-INF/views
		// suffix : .jsp

		
		return "/demo/demo";
	}
	
	//메소드 선언하기
	//리턴값, 매개변수 알아보기
	//1. 반환형
	// 1) String : ViewResolver에 의해서 view화면을 출력해줌 * 기본적으로 많이 사용
	// 2) void : HttpServletResponse객체로 직접 응답메세지를 작성할 때 사용(*doGet()과 유사)
	// 3) ModelAndView : 화면에 전달할 데이터와 view내용을 저장하는 객체 spring 제공해줌
	// 4) 클래스타입 : json으로 데이터를 반환할 때, Restful하게 서버를 구성했을 때 많이 사용
	//		*ResponseEntity<클래스 타입>
	
	//2. 매개변수
	// 1) HttpServletRequest : 서블릿에서 쓰던 객체, servlet처럼 사용가능
	// 2) HttpServletResponse: 서블릿에서 쓰던 객체, servlet처럼 사용가능
	// 3) HttpSession : 서블릿에서 쓰던 객체, session값을 가져와서 대입해줌
	// 4) java.util.Local : 서버의 로케일 정보를 저장한 객체
	// 5) InputSteam/Reader : 파일 읽어올 때 사용하는 stream
	// 6) OutputStream/Writer : 파일을 보낼 때 사용하는 stream
	// 7) 기본자료형 변수 : 클라이언트가 보낸 parameter데이터랑 선언한 변수 이름이 동일하면 자동으로 매핑(!)
	// 		*선언이름과 파라미터 이름이 다를 경우 @RequestParam어노테이션을 이용해서 연결할 수 있음
	//		*@RequestParam은 매핑, 기본값 설정, 필수여부 설정 가능
	// 8) 클래스 변수 : Command라고 함, parameter데이터를 field에 넣어서 객체를 전달(!)
	//		*parameter이름과 필드명이 같은 데이터를 대입해줌.
	// 9) java.util.Map : @RequestParam 어노테이션과 함께 사용. parameter값을 map으로 저장(유의-단일값만 가능하다고 함)
	//10) @RequestHeader(name값)와 기본자료형 : 작성하면 header정보를 받을 수 있음
	//11) @CookieValue(name값)와 기본자료형 : 작성하면 Cookie에 저장된 값을 받을 수 있음
	//12) Model : request와 비슷하게 데이터를 key/value형식으로 저장할 수 있는 객체(경량화)
	//13) ModelAndView :  model과 view를 동시에 저장하는 객체
	
	//메소드 어노테이션
	//@ResponseBody -> Rest방식으로 클래스를 json으로 전송할 때
	//@RequestBody  -> json방식으로 전송된 parameter를 클래스로 받을 때 사용
	// + @GetMapping, @PostMapping, @DeleteMapping...
	
	//서블릿 방식으로 매핑매소드 이용하기
	@RequestMapping("demo/demo1.do")
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println(req);
		System.out.println(res);
		String devName=req.getParameter("devName");
		int devAge=Integer.parseInt(req.getParameter("devAge"));
		String devEmail=req.getParameter("devEmail");
		String devGender=req.getParameter("devGender");
		String[] devLang=req.getParameterValues("devLang");
		System.out.println(devName+devAge+devEmail+devGender);
		for(String l : devLang) {
			System.out.println(l);
		}
		
		Demo d=Demo.builder()
				.devName(devName)
				.devAge(devAge)
				.devEmail(devEmail)
				.devGender(devGender)
				.devLang(devLang).build();
		req.setAttribute("demo", d);
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
		
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out = res.getWriter();
//		out.print("<h2>"+devName+devAge+devEmail+devGender+"</h2>");
	}
	
	//1:1 매칭하여 데이터 받기
	//매핑메소드의 매개변수에 파라미터로 전송되는 name과 동일한 이름의 변수를 선언
	//매개변수의 타입은 사용할 타입으로 설정 *변경이 가능해야함
	@RequestMapping("demo/demo2.do")
	public String demo2(String devName, int devAge,
			String devGender, String devEmail, String[] devLang) {
		System.out.println(devName+devAge+devEmail+devGender+Arrays.toString(devLang));
		

		
		
		
		return "demo/demoResult";
	}
}
