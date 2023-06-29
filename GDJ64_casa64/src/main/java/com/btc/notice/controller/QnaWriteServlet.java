package com.btc.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.member.model.dto.Member;
import com.btc.notice.model.dto.Qna;
import com.btc.notice.model.service.QnaService;

/**
 * Servlet implementation class QnaWriteServlet
 */
@WebServlet("/qna/qnaWrite.do")
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = checkLogin(request, response); //로그인체크 로직
		if(memberNo > 0){ //로그인 되어있으면(버튼이 보이는 조건이 관리자 로그인이다)
			request.setAttribute("categoryName", "COMMUNITY");
			request.setAttribute("communityTitle", "QnA 작성");

			request.getRequestDispatcher("/views/board/qna_write.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = checkLogin(request, response); //로그인체크 로직
		if(memberNo > 0){
			//넣어줘야할 값을 파라미터로 받기
//			String title = request.getParameter("title"); // 제목
//			String contents = request.getParameter("contents"); // 내용
			int memberWriterNo = Integer.parseInt(request.getParameter("MEMBER_NO"));
			String categoryName = request.getParameter("CATEGORY_NAME");
			String questionTitle = request.getParameter("QUESTION_TITLE");
			String questionContent = request.getParameter("QUESTION_CONTENT");
			
			Qna q = Qna.builder()
					.memberNo(memberWriterNo)
					.categoryName(categoryName)
					.questionContent(questionContent)
					.questionTitle(questionTitle)
					.build();
			
//			int result = new NoticeService().insertNotice(n); //공지사항 생성
			int result = new QnaService().insertQna(q);
//			int noticeNo = new NoticeService().searchNoticeNo(); //마지막에 만든 공지사항번호 가져오기
//			if(result > 0) result = new NoticeService().insertNoticeImage(noticeNo); //생성로직
			response.sendRedirect(request.getContextPath() + "/qna/insertQna.do");
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
