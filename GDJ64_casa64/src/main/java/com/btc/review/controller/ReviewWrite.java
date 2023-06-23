package com.btc.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.member.model.dto.Member;
import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Review;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class ReviewWrite
 */
@WebServlet("/review/reviewWrite")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = checkLogin(request, response);
		if(memberNo > 0){
			String no = request.getParameter("no");
			String communityTitle = "이용후기 작성";
			if(no != null) { // null 이 아닌 건 no 가 있다는거니까 수정하기
				int reviewNo = Integer.parseInt(no);
				Review reviews = new ReviewService().getReviewView(reviewNo);
				request.setAttribute("reviews", reviews);
				communityTitle = "이용후기 수정";
				if(reviews.getMemberNo() != memberNo) {
					String msg="수정 권한이 없습니다.";
					String loc="/review/reviewList";
					request.setAttribute("msg", msg);
					request.setAttribute("loc", loc);
					request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
				}
			}
			
			request.setAttribute("categoryName", "COMMUNITY");
			request.setAttribute("communityTitle", communityTitle);
			request.getRequestDispatcher("/views/review/review_write.jsp").forward(request, response);
		}
	}

	/**
	 * 이용후기 작성 버튼 클릭 후 실행하는 곳 (post, insert)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = checkLogin(request, response);
		if(memberNo > 0){
			int result = 0;
			String redirect = "/";
			String type = request.getParameter("type");
			
			// 1. 넣어줘야할 값을 파라미터로 받기
			String title = request.getParameter("title"); // 제목
			String contents = request.getParameter("contents"); // 내용
			int roomNo = Integer.parseInt(request.getParameter("roomNo")); // 선택한 객실 번호
			int bookingNo = 1; // 원래는 선택한 객실 번호와 함께 셋팅 되어야 함
			
			Review reviews = Review.builder()
					.title(title)
					.contents(contents)
					.roomNo(roomNo)
					.memberNo(memberNo)
					.bookingNo(bookingNo)
					.isReply(0)
					.build();
			if(type.equals("write")) { // 작성하기
				reviews.setViews(0);
				reviews.setIsDeleted(0);
				result = new ReviewService().insertReviews(reviews);
				redirect = "/review/reviewList";
			} else if(type.equals("update")) { // 수정하기
				int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
				reviews.setNo(reviewNo);
				result = new ReviewService().updateReviews(reviews);
				redirect = "/review/reviewView?no="+reviewNo;
			}
			if(result > 0 ) {
				response.sendRedirect(request.getContextPath() + redirect);
			} else {
				// 작성 실패에 대한 피드백을 return 해주기
			}
		}
	}
	public int checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember"); // 로그인 정보 가져오기
		if (loginMember == null) { // 로그인 해야함
			String msg="로그인이 필요합니다";
			String loc="/views/LOGIN/login.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return 0;
		}
		return loginMember.getMemberNo();
	}

}
