package com.btc.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.dto.Notice;
import com.btc.notice.model.dto.Notice_images;
import com.btc.notice.model.dto.Qna;
import com.btc.notice.model.service.NoticeService;
import com.btc.notice.model.service.QnaService;

/**
 * Servlet implementation class QnaUpdateEndServlet
 */
@WebServlet("/qna/updateQnaEnd.do")
public class QnaUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		int result = new QnaService().updateQna(q);
		response.sendRedirect(request.getContextPath() + "/qna/insertQna.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
