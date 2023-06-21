package com.btc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.member.model.service.MemberService;

/**
 * Servlet implementation class EnrollMemberEndServlet
 */
@WebServlet("/member/enrollMailMember.do")
public class EnrollMemberSendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberSendMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.setAttribute("userName",request.getParameter("userName"));
		String email=request.getParameter("email");
		session.setAttribute("email",request.getParameter("email"));
		session.setAttribute("nickName",request.getParameter("nickName"));
		session.setAttribute("phone",request.getParameter("phone"));
		session.setAttribute("password",request.getParameter("password"));
		VerifyEmail mail=new VerifyEmail();
		String code=mail.sendMail(email);
		
		session.setAttribute("code", code);
		if(code!=null) {
				request.getRequestDispatcher("/views/LOGIN/signup03-success.jsp").forward(request, response);
		}else {
			String msg="유효하지 않은 이메일입니다";
			String loc="/views/LOGIN/signup02-input.jsp";
			request.getAttribute(msg);
			request.getAttribute(loc);
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
