package com.btc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.admin.model.service.AdminMemberService;
import com.btc.member.model.service.MemberService;

/**
 * Servlet implementation class EnrollMemberEndServlet
 */
@WebServlet("/member/enrollMemberEnd.do")
public class EnrollMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userName=(String)session.getAttribute("userName");
		String email=(String)session.getAttribute("email");
		String nickName=(String)session.getAttribute("nickName");
		String phone=(String)session.getAttribute("phone");
		String password=(String)session.getAttribute("password");
		String code=(String)session.getAttribute("code");

		String inputCode=request.getParameter("code");
		if(new AdminMemberService().selectCMember(email)!=null) {
			new AdminMemberService().deleteCMember(email);
		}
		
		String msg="",loc="";
		if(code.equals(inputCode)) {
			int result=new MemberService().insertMember(userName, email, nickName, phone, password);
			
			if(result>0) {
				msg="회원가입성공";
				loc="/index.jsp";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}else {
				msg="서버오류";
				loc="/views/LOGIN/signup03-success.jsp";
				request.setAttribute("loc", loc);
				request.setAttribute("msg", msg);
				
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
		}else{
				msg="인증번호가 일치하지 않습니다";
				loc="/views/LOGIN/signup03-success.jsp";
				request.setAttribute("loc", loc);
				request.setAttribute("msg", msg);
				
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
