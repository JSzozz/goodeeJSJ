package com.btc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.admin.model.service.AdminMemberService;
import com.btc.member.model.dto.Member;
import com.btc.member.model.service.MemberService;

/**
 * Servlet implementation class CancelMember
 */
@WebServlet("/member/cancelMember.do")
public class CancelMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int memberNo = (int) session.getAttribute("memberNo");
		Member m = new AdminMemberService().selectMember(memberNo);
		int insertResult = new AdminMemberService().insertCancelMember(m.getMemberName(), m.getNickName(), m.getEmail(),m.getPhone());
		int result = 0;
		String msg = "", loc = "";
		if (insertResult > 0) {
			result = new MemberService().deleteMember(memberNo);
			
			if (result > 0) {
				msg = "회원탈퇴가 완료되었습니다";
				loc = "/logout.do";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
			} else {
				msg = "서버오류";
				loc = "/";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
			}
		}else {
			msg = "서버오류";
			loc = "/";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}

		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
