package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.service.DemoService;

@Controller
public class DemoController {

	private Logger logger=LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoService service;
	
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
		logger.debug("request : {}",req);
		logger.debug("response : {}",res);
		
		String devName=req.getParameter("devName");
		int devAge=Integer.parseInt(req.getParameter("devAge"));
		String devEmail=req.getParameter("devEmail");
		String devGender=req.getParameter("devGender");
		String[] devLang=req.getParameterValues("devLang");
		//System.out.println(devName+devAge+devEmail+devGender);
		for(String l : devLang) {
			//System.out.println(l);
			logger.debug(l);
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
			String devGender, String devEmail, String[] devLang, /*double weight,*/ Model model) {
//		System.out.println(devName+devAge+devEmail+devGender+Arrays.toString(devLang));
		
		//페이지에 생성한 데이터를 전송하려면... request.session, servletContext
		//Spring에서 데이터전송하는 객체를 만들어둠 -> Model
		//Model에 데이터 저장하기 -> model.addAttribute("key", value);
		Demo d= Demo.builder()
					.devName(devName)
					.devAge(devAge)
					.devGender(devGender)
					.devEmail(devEmail)
					.devLang(devLang)
					.build();
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}
	
	//파라미터데이터를 받을 때 @RequestParam어노테이션을 이용해서
	//옵션을 설정할 수 있다
	@RequestMapping("/demo/demo3.do")
	public String requestParamUse(
			@RequestParam(value="devName", defaultValue="아무개" ) String name, 
			@RequestParam(value="devAge", defaultValue="10") int age,
			@RequestParam(value="devGender") String gender, 
			@RequestParam(value="devEmail", required =true) String email,
			String[] devLang, Model model) {
	
	Demo d= Demo.builder()
				.devName(name)
				.devAge(age)
				.devGender(gender)
				.devEmail(email)
				.devLang(devLang)
				.build();
	
	model.addAttribute("demo", d);
	
	return "demo/demoResult";
	}
	
	//DTO/Vo 객체로 직접 parameter값 받기
	// 매개변수로 전달된 parameter 이름과 동일한 필드를 갖는 객체를 선언함
	// * 주의할 점은 클래스 타입 Date를 전달받을 때는 java.sql.Date로 하자
	@RequestMapping("/demo/demo4.do")
	public String commandMapping(Demo demo, Model m) {
		System.out.println(demo);//Demo(devName=1, devAge=1, devGender=M, devEmail=1, devLang=[Java], birthDay=2023-07-13)
		System.out.println(m);
		//{demo=Demo(devName=1, devAge=1, devGender=M, devEmail=1, devLang=[Java], birthDay=2023-07-13),
		//org.springframework.validation.BindingResult.demo=org.springframework.validation.BeanPropertyBindingResult: 0 errors}

		m.addAttribute("demo",demo);
		
		return "demo/demoResult";
	}

	// Map으로 parameter데이터 받아오기
	//@RequestParam어노테이션 설정 Map
	@RequestMapping("/demo/demo5.do")
	 public String mapPapping(@RequestParam Map<String, String[]> param, String[] devLang, Model m) {
		 System.out.println(param);
		 //{devName=정상준, devAge=1, devEmail=1, devGender=M, devLang=Java, birthDay=2023-07-19}
		 // 맵으로 받았을 때는 배열처리를 못해준다 : 단일값으로만 나온다 (*별도로 String[] 매개변수 추가해서 값 받아오기가 필요)
		 
		 param.put("devLang", devLang);
		 m.addAttribute("demo", param);
		 
		 return "demo/demoResult";
	 }

	//추가데이터 받아오기
	//Cookie, Header, Session
	//Cookie : @CookieValue("value="key", required=T/F) String data
	//Header : @RequestHeader(value="헤더이름") String header
	//Session : @SessionAttribute(value="세션key값") String sessionId
	@RequestMapping("/demo/demo6.do")
	public String extraData(
			@CookieValue(value="testData", required = false, defaultValue="rest-time") String data,
			@RequestHeader(value="User-agent") String header,
			@SessionAttribute(value="sessionId", required = false) String sessionId,
			@RequestHeader(value="Referer") String referer
			) {
		
		System.out.println("쿠키"+data);
		System.out.println("헤더"+header);
		System.out.println("세션"+sessionId);
		System.out.println("이전페이지"+referer);
		
		return "index";
	}
	
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewReturn(Demo d, ModelAndView mv) {
		//ModelAndView view설정과, Model설정은 같이 할 수 있는 객체
		//data : addObject("key", value)메소드를 이용해서 저장
		//view : setViewName() 메소드를 이용해서 저장
		mv.addObject("demo", d);
		mv.setViewName("demo/demoResult");
		return mv;
	}
	
	//자료형에 대해 반환하기 ->  Data만 응답할 때 사용 -> jackson라이브러리를 이용해서 처리
	//매소드 선언부 @ResponseBody어노테이션 사용
	//Restful하게 메소드를 구현했을 때 사용
	@RequestMapping("/demo/demo8.do")
	@ResponseBody
	public String dataReturn(){
		return "a b c d e f ㄱ ㄴ ㄷ";
	}
	
	//Request요청 메소드(GET, POST)를 필터링하기
	//@RequestMapping(value="url", method=RequestMethod.GET||RequestMethod.POST)
	//@RequestMapping(value="/demo/demo9.do", method=RequestMethod.POST)
	//@PostMapping("/demo/demo9.do")
	@GetMapping("/demo/demo9.do")// 오류 메세지 : Request method 'POST' not supported 
	public String method(Demo d, Model m) {
		m.addAttribute("demo", d);
		return "demo/demoResult";
	}
	// ㄴ 간편하게 사용할 수 있게 Mapping어노테이션을 지원 (*@RequestMapping 대체)
	//	@GetMapping
	//	@PostMapping
	//	@DeleteMapping
	//	@PutMapping
	
	//mapping주소를 설정할 때 {}를 사용할 수 있음
	// /board/boardView?no=1
	// ㄴ> /board/1 [method=GET]
	// ㄴ> /board [method=GET]
	@GetMapping("/demo/{no}")
	public String searchDemo(@PathVariable(value="no") int no) {
		System.out.println(no);
		return "demo/demoResult";
	}
	
	@RequestMapping(value="/demo/insertDemo.do", 
			method=RequestMethod.POST)
	public String insertDemo(Demo demo, Model m) {
		
		int result=service.insertDemo(demo);
		System.out.println(result);
		
		m.addAttribute("msg",result>0?"저장성공":"저장실패");
		m.addAttribute("loc","/demo/demo.do");
		

		//sendRedirect로 변경하는 방법
		//(*default로 새로고침 시 동일한 로직처리가 이뤄짐 -DB에 중복값이 들어가는 현상도 발생 가능)
		//prefix redirect: 요청할 주소(매핑주소)

		//return "demo/demo";
		//return "redirect:/";
		//return "redirect:/demo/demo.do";
		return "common/msg";
	}	
	
	@RequestMapping("/demo/demoList.do")
	public String selectList(Model m) {
		List<Demo> demos=service.selectDemoAll();
		//demos.stream().forEach(System.out::println);
		m.addAttribute("demos",demos);
		
		return "demo/demoList";
	}
	
	@RequestMapping("/demo/devUpdate.do")
	public String devUpdate( @RequestParam(value="devNo") int devNo, Model m ) {
		Demo demo=service.devByDevNo(devNo);
//		System.out.println(demo);
		
		Demo d = Demo.builder()
						.devName(demo.getDevName())
						.devAge(demo.getDevAge())
						.devGender(demo.getDevGender())
						.devEmail(demo.getDevEmail())
						.devLang(demo.getDevLang())
						.build();
		
		m.addAttribute("demo", d);
		
		return "demo/updateDemo";
	}
	
}
