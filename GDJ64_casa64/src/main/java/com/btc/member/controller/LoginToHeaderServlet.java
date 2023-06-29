package com.btc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.booking.model.service.BookingService;
import com.btc.booking.model.vo.Booking;
import com.btc.member.model.dto.Member;
import com.btc.member.model.service.MemberService;

//import com.web.controller.emailId;

/**
 * Servlet implementation class LoginToHeaderServlet
 */
@WebServlet( name = "login", urlPatterns = {"/LoginToHeaderServlet.do"})
public class LoginToHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginToHeaderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//아이디값을 받아와서 헤더로 넘기기
		
		//아이디 불러오기
		String email=request.getParameter("email");
		String pw=request.getParameter("password");
		Member loginMember=new MemberService().login(email,pw);
		//세션에 저장
		HttpSession session=request.getSession();//true/flase도 줄 수 있다. 그러나 세션은 (jsp파일에 별도 설정 없는 상태에서는) 기본적으로 존재한다
		
		int loginMemberNo= loginMember.getMemberNo();
		Booking recentBooking = new BookingService().searchBookingByMemberNo(loginMemberNo);
		
		
		//값이 없으면 로그인 금지
		if(loginMember==null) {
			//실패 로그 출력
			String msg="아이디 혹은 비밀번호가 일치하지 않습니다";
			String loc="/views/LOGIN/login.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}else{
			//메인에 보내기
			session.setAttribute("loginMember", loginMember);
			//최근 예약내역 추가함-sj
			session.setAttribute("recentBooking", recentBooking);
			response.sendRedirect(request.getContextPath());			
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
