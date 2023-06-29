package com.btc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.member.model.dto.Member;
import com.btc.member.model.service.MemberService;

/**
 * Servlet implementation class ChangeMyinfo
 */
@WebServlet("/member/changemyinfo.do")
public class ChangeMyinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeMyinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName=request.getParameter("nick");
		String phone=request.getParameter("phone");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		Member updateMember=new MemberService().updateMember(nickName,phone,name,email);
		
		String msg="",loc="";
		if(updateMember!=null) {
			msg="회원정보를 변경하였습니다.";
			loc="/views/myPage/myPageInfo.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getSession().setAttribute("loginMember", updateMember);
			
		}else {
			msg="정보변경에 실패하였습니다.";
			loc="/views/myPage/myPageInfo.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
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
