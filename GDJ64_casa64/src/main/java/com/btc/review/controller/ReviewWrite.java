package com.btc.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.btc.member.model.dto.Member;
import com.btc.mypage.model.vo.Booking;
import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Review;
import com.btc.review.model.vo.ReviewImages;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
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
			
			// select, 예약-이용완료 리스트 불러오기.
			List<Booking> bkList = new ReviewService().getBookingList(memberNo);
			request.setAttribute("bkList", bkList);
			
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
			int result = 1;
			if(!ServletFileUpload.isMultipartContent(request)) {
				request.setAttribute("msg", "잘못된 접근입니다. 관리자에게 문의하세요 :(");
				request.setAttribute("loc","/");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
				return;
			}
			String path = getServletContext().getRealPath("/upload/review");
			//최대파일크기설정
			int maxSize=1024*1024*100;
			//인코딩설정
			String encode="UTF-8";
			//리네임클래스 생성
			DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
			
			MultipartRequest multi = new MultipartRequest(request,path,maxSize,encode,dfr);
			String redirect = "/";
			String type = multi.getParameter("type");
			
			// 1. 넣어줘야할 값을 파라미터로 받기
	        String title = multi.getParameter("title"); // 제목
	        String contents = multi.getParameter("contents"); // 내용
	         
	        System.out.println(multi.getParameter("roomNo"));
	        System.out.println(multi.getParameter("bookingNo"));

	        int roomNo = Integer.parseInt(multi.getParameter("roomNo")); // 선택한 객실 번호
	        int bookingNo = Integer.parseInt(multi.getParameter("bookingNo")); // 원래는 선택한 객실 번호와 함께 셋팅 되어야 함
			System.out.println(roomNo);
			System.out.println(bookingNo);
			
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
				int reviewNo = Integer.parseInt(multi.getParameter("reviewNo"));
				reviews.setNo(reviewNo);
				result = new ReviewService().updateReviews(reviews);
				redirect = "/review/reviewView?no="+reviewNo;
			}
			if(result > 0 ) {
				// 파일을 담을 수 있는 multi 라는 변수를 준비
		 		Enumeration files = multi.getFileNames();
		 		// files 라는 변수는 오브젝트를 배열형식으로 담겨져있음
		 		List<ReviewImages> imgList = new ArrayList();
		 		while (files.hasMoreElements()) {
		 			String file = (String) files.nextElement();
		 			String saveFileName = multi.getFilesystemName(file);
		 			String saveFilePath = request.getContextPath() + "/upload/review/" + saveFileName;
		 			// 업로드된 파일명 -> 중복된 파일을 업로드할 경우 파일명이 바뀜
		 			String fileName = multi.getOriginalFileName(file);
		 			ReviewImages ri = ReviewImages.builder().fileName(fileName).saveFileName(saveFilePath).build();
//		 			ri.setFileName(fileName);
//		 			ri.setSaveFileName(saveFileName);
		 			if(fileName != null) {
		 				imgList.add(ri);
		 			}
		 		}
		 		
		 		int uploadResult = new ReviewService().uploadImages(imgList, reviews);
		 	
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
