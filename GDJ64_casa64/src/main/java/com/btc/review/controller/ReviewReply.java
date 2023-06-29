package com.btc.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewReply
 */
@WebServlet("/review/reply")
public class ReviewReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewReply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 필요한 파라미터
		// 댓글 내용
		// 리뷰 번호
		// 받은 걸 update 해줘야함
		String reply = request.getParameter("adminReply");
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		int isReply = Integer.parseInt(request.getParameter("isReply"));
		int result = new ReviewService().updateAdminReply(reply, reviewNo, isReply);
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().print(result);
	}

}
