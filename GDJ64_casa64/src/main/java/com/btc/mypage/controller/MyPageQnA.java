package com.btc.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.member.model.dto.Member;
import com.btc.mypage.model.service.MyPageService;
import com.btc.mypage.model.vo.QnA;

/**
 * Servlet implementation class MyPageQnA
 */
@WebServlet("/myPage/myPageQnA")
public class MyPageQnA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageQnA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서비스를 통해 dao 에서 전달 된 리스트 불러와서 화면으로 setAtt해주고 뷰에서는 그걸 활용해서 뿌려줌.

		int memberNo = checkLogin(request, response);
		if (memberNo > 0) {
			List<QnA> list = new MyPageService().selectQnAMypage(memberNo);
			request.setAttribute("MypageQnAList", list);
			request.getRequestDispatcher("/views/myPage/myPageQnA.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public int checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if (loginMember == null) { // 로그인 해야함
			String msg = "로그인이 필요합니다.";
			String loc = "/views/LOGIN/login.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return 0;
		}
		return loginMember.getMemberNo();
	}

}
