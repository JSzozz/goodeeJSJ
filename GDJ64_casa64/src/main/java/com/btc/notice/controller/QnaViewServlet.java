
package com.btc.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.dto.Notice;
import com.btc.notice.model.dto.Notice_images;
import com.btc.notice.model.dto.Qna;
import com.btc.notice.model.dto.QnaComment;
import com.btc.notice.model.service.NoticeService;
import com.btc.notice.model.service.QnaService;

/**
 * Servlet implementation class QnaViewServlet
 */
@WebServlet("/qna/viewQna.do") //qna_view.jsp로 이동하는 서블릿
public class QnaViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 선택한 게시물의 번호
		int no=Integer.parseInt(request.getParameter("no"));
		request.setAttribute("no",no);
		
		String categoryName = (String)request.getParameter("categoryName");
		String communityTitle = (String)request.getParameter("communityTitle");
		
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("communityTitle", communityTitle);
		
		//Qna 게시판
		Qna qna = new QnaService().selectQnaByNo(no);
		int memberNo = qna.getMemberNo();
		//회원번호로 이름 가져오기
		String memberName = new QnaService().checkName(memberNo);
//				System.out.println("membername : "+memberName+", "+qna);
		//Qna 댓글 답글
		List<QnaComment> comments=new QnaService().selectQnaComment(no);
//				System.out.println(comments.get(0).getQnaCommentDate());
		
		//게시판 상세내용
		request.setAttribute("qna",qna);
		request.setAttribute("memberName", memberName);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("/views/board/qna_view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

