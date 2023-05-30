package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.model.dto.MemberDTO;
import com.web.model.service.MemberService;

/**
 * Servlet implementation class LoginfrmServlet
 */
@WebServlet(name="login", urlPatterns="/loginfrm.do")
public class LoginfrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginfrmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
//		System.out.println(userId +"\n"+password);
		
		//student계정의 Member테이블에 있는 모든 데이터를 조회하는 기능
		//1. DB에서 필요한 데이터를 가져온다. -> JDBC를 이용
		MemberDTO loginMember=new MemberService().login(userId, password);
		System.out.println(loginMember);

		//화면에 조회한 데이터를 전달하는 방법
//		request.setAttribute("members", members);
//		request.setAttribute("userId", userId);
//		request.setAttribute("password", password);
		
		//2. DB정보를 출력할 화면을 출력한다. -> jsp를 이용
//		RequestDispatcher rd=request.getRequestDispatcher("/views/memberList.jsp");
		//getRequestDispatcher의 매개변수 /이하는 webapp폴더 이하이다!
//		rd.forward(request, response);
		
		//아이디 저장 로직처리
		String saveId=request.getParameter("saveId");
		System.out.println("saveId : " + saveId);//saveId : on
		
		// checkbox에 check가 되면 on이 넘어오고 
		// checkbox에 cherck가 안되면 null이 return됨

//x?	if(saveId=="on") {
		if(saveId!=null) {
			Cookie c=new Cookie("saveId", userId);
			c.setMaxAge(60*60*24*7);//7일
			response.addCookie(c);
			System.out.println("userId : "+ c);
		}	else {
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0);
			response.addCookie(c);
//			저장된 아이디값 지우기 위해 js이용해서 val("")를 먹이려고했는데, 서버단에서 cookie도 지워야했구나!
		}
		
		//loginMember null을 기준으로 로그인처리여부를 결정할 수 있음
		if(loginMember!=null) {
			//로그인 성공 -> 인증 받음
			HttpSession session=request.getSession();//true/flase도 줄 수 있다. 그러나 세션은 (jsp파일에 별도 설정 없는 상태에서는) 기본적으로 존재한다
			session.setAttribute("loginMember", loginMember);
			
			response.sendRedirect(request.getContextPath());
			
		}else {
			//로그인 실패-> 인증 못받음
			//실패 메세지 출력
			request.setAttribute("msg", "아이디, 패스워드가 일치하지 않습니다.");
			
			request.setAttribute("loc", "/");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
