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
 * Servlet implementation class ReviewWrite
 */
@WebServlet("/board/reviewWrite.do")
public class ReviewWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewWrite() {
		super();
	}

	/**
	 * 이용후기 작성 페이지 get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categoryName", "COMMUNITY");
		request.setAttribute("communityTitle", "이용후기 작성");
		request.getRequestDispatcher("/views/review/review_write.jsp").forward(request, response);
	}

	/**
	 * 이용후기 작성 버튼 클릭 후 실행하는 곳 (post, insert)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		1. 넣어줘야할 값을 파라미터로 받기
		String title = request.getParameter("title"); // 제목
		String contents = request.getParameter("contents"); // 내용
		int roomNo = Integer.parseInt(request.getParameter("roomNo")); // 선택한 객실 번호
		int memberNo = 1; // 세션에서 가져와야하는 멤버 no
		int bookingNo = 1; // 원래는 선택한 객실 번호와 함께 셋팅 되어야 함..
		
		Reviews reviews = Reviews.builder()
				.title(title)
				.contents(contents)
				.views(0)
				.isDeleted(0)
				.roomNo(roomNo)
				.memberNo(memberNo)
				.bookingNo(bookingNo)
				.isReply(0)
				.build();
		
		int result = new ReviewService().insertReviews(reviews);
		
		if(result > 0) {
			 response.sendRedirect(request.getContextPath() + "/board/reviewList.do");
		} else {
			// 작성 실패에 대한 피드백을 return 해줘야함.. (작성을 실패하였습니다. 빈 칸이 없는지 확인해 주세요????)
			
		}

//		
	}

}
