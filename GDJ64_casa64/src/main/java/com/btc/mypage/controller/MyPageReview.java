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
import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Review;

/**
 * Servlet implementation class MyPageReview
 */
@WebServlet("/myPage/myPageReview")
public class MyPageReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPageReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int memberNo = checkLogin(request, response);
		if (memberNo > 0) {
			List<Review> list = new ReviewService().selectReviewsMypage(memberNo);
			request.setAttribute("MypageReviewList", list);
			request.getRequestDispatcher("/views/myPage/myPageReview.jsp").forward(request, response);
		}
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

	public int checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if (loginMember == null) { // 마이페이지 로그인 해야함
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
