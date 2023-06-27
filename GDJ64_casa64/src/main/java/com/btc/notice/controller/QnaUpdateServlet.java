package com.btc.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/qna/updateQna.do")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryName = request.getParameter("categoryName");
		String questionTitle = request.getParameter("questionTitle");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String questionContent = request.getParameter("questionContent");
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println(categoryName);
		
		request.setAttribute("categoryName", "COMMUNITY");
		request.setAttribute("communityTitle", "QnA 수정");
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("questionTitle", questionTitle);
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("questionContent", questionContent);
		request.getRequestDispatcher("/views/board/qna_update.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
