package com.btc.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Reviews;

/**
 * Servlet implementation class ReviewView
 */
@WebServlet("/board/reviewView.do")
public class ReviewView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("no"));
		Reviews reviews = new ReviewService().getReviewView(reviewNo);

		request.setAttribute("review", reviews);
		request.setAttribute("categoryName", "COMMUNITY");
		request.setAttribute("communityTitle", "이용후기");
		request.getRequestDispatcher("/views/review/review_view.jsp").forward(request, response);
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
