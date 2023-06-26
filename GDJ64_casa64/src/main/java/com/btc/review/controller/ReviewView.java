package com.btc.review.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Review;
import com.btc.review.model.vo.ReviewImages;

/**
 * Servlet implementation class ReviewView
 */
@WebServlet("/review/reviewView")
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
		
		/* 조회수 영역*/
		Cookie[] cookies = request.getCookies();
		String read = Arrays.stream(cookies).filter(c -> c.getName().equals("reviewRead")).findFirst()
				.orElse(new Cookie("reviewRead", "")).getValue();
		boolean isRead = read.contains("|" + reviewNo + "|");
		if (!isRead) {
			Cookie c = new Cookie("reviewRead", read + "|" + reviewNo + "|");
			c.setMaxAge(60 * 60 * 24);
			response.addCookie(c);
		}
		int isReadResult = new ReviewService().reviewCountUpdate(isRead, reviewNo); // 조회수 업데이트
		
		/* 실제 리뷰 데이터 */
		Review reviews = new ReviewService().getReviewView(reviewNo);

		/* reviewNo 로 리뷰이미지 데이터  */
		List<ReviewImages> imgList = new ReviewService().getReviewImages(reviewNo);
		
		request.setAttribute("reviewImages", imgList);
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
