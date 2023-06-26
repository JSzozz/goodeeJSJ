package com.btc.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.member.model.dto.Member;
import com.btc.notice.model.dto.Notice;
import com.btc.notice.model.dto.Notice_images;
import com.btc.notice.model.service.NoticeService;
import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Review;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class ReviewWrite
 */
@WebServlet("/notice/noticeWrite.do")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeWriteServlet() {
		super();
	}

	/**
	 * 게시물 -> 작성페이지 이동 = get 
	 * 작성페이지 -> 생성,수정,삭제 서블릿으로 이동 = post
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = checkLogin(request, response); //로그인체크 로직
		if(memberNo > 0){ //로그인 되어있으면
			String no = request.getParameter("no"); //수정의 목적으로 작성하기를 누르면 생성이 아닌 수정으로 인식하려하기 때문에 수정할 게시물번호를 구하는 과정
			System.out.println(no);
			String communityTitle = "공지사항 작성";
			if(no != null) { // null 이 아닌 건 no 가 있다는거니까 수정하기
				int reviewNo = Integer.parseInt(no);
				Review reviews = new ReviewService().getReviewView(reviewNo);
				request.setAttribute("reviews", reviews);
				communityTitle = "공지사항 수정";
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
			request.getRequestDispatcher("/views/board/notice_write.jsp").forward(request, response);
		}
	}

	/**
	 * 게시물 -> 작성페이지 이동 = get 
	 * 작성페이지 -> 생성,수정,삭제 서블릿으로 이동 = post
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = checkLogin(request, response); //로그인체크 로직
		if(memberNo > 0){
			// 파일 업로드 로직
			if(!ServletFileUpload.isMultipartContent(request)) {
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("loc", "/views/board/notice.jsp");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
				return;
			}
			
			//절대경로 가져오기
			String path = getServletContext().getRealPath("/images/notice");
			//최대 파일크기
			int maxSize = 1024*1024*50; //50mb
			//인코딩 설정
			String encode = "utf-8";
			//rename 클래스 설정
			DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
			MultipartRequest mr = new MultipartRequest(request,path,maxSize,encode,dfr);
			
			//나머지 문자열은 MultipartRequest객체를 이용해야 된다.
			int result = 0; //생성,수정 결과를 받는 변수
			String redirect = "/";
			String type = mr.getParameter("type"); //생성인지 수정인지 받아오는 값
			
			// 1. 넣어줘야할 값을 파라미터로 받기
			String title = mr.getParameter("title"); // 제목
			String contents = mr.getParameter("contents"); // 내용
//			String uploadFile = mr.getParameter("uploadFile"); // 파일
			String fileName = mr.getOriginalFileName("uploadFile");
			String saveFilename = mr.getFilesystemName("uploadFile");
			
			Notice n = Notice.builder()
					.noticeTitle(title)
					.noticeContent(contents)
					.build();
			
			Notice_images image = Notice_images.builder()
					.saveFilename(fileName)
					.fileName(saveFilename)
					.noticeNo(memberNo)
					.build();
			
			result = new NoticeService().insertNotice(n); //공지사항 생성
			int noticeNo = new NoticeService().searchNoticeNo(); //마지막에 만든 공지사항번호 가져오기
			if(result > 0) result = new NoticeService().insertNoticeImage(image,noticeNo); //생성로직
			response.sendRedirect(request.getContextPath() + "/notice/insertNotice.do");
			
			//구현할 일 : 큰주제는 게시글생성(notice), 첨부파일 빼고 생성하고 pk값 받아와서 이미지 테이블 만들고, 다시 번호와 일치하는(join)
			//          값을 게시글 테이블에 저장, 이미지 dto, 테이블 만들기 , 파일이름 바꿔저장하는건 나중에하기
//			if(type.equals("write")) { // 작성하기
//				result = new NoticeService().insertNotice(n); //공지사항 생성
//				int noticeNo = new NoticeService().searchNoticeNo(); //마지막에 만든 공지사항번호 가져오기
//				if(result > 0) result = new NoticeService().insertNoticeImage(image,noticeNo); //생성로직
//				redirect = "/notice/insertNotice.do"; //응답주소
//			} else if(type.equals("update")) { // 수정하기
//				int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
//				reviews.setNo(reviewNo);
//				result = new ReviewService().updateReviews(reviews);
//				redirect = "/review/reviewView?no="+reviewNo;
//			}
//			if(result > 0 ) {
//				response.sendRedirect(request.getContextPath() + redirect);
//			} else {
//				// 작성 실패에 대한 피드백을 return 해주기
//			}
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
