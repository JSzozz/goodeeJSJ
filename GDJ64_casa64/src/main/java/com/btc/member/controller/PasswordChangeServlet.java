package com.btc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.member.model.dao.MemberDao;
import com.btc.member.model.service.MemberService;

/**
 * Servlet implementation class PasswordChangeServlet
 */
@WebServlet("/member/changePassword.do")
public class PasswordChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String pw=request.getParameter("password");
		String email=(String)session.getAttribute("pwEmail");
		
		int result=new MemberService().updatePassword(email, pw);
		String msg="",loc="";
		if(result>0) {
			msg="비밀번호를 변경하였습니다.";
			loc="/views/LOGIN/login.jsp";
		}else {
			msg="비밀번호 변경에 실패하였습니다.";
			loc="/views/LOGIN/login.jsp";
		}
		session.removeAttribute("pwEmail");
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
