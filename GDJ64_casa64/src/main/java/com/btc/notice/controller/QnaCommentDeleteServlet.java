package com.btc.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.service.QnaService;

/**
 * Servlet implementation class QnaCommentDeleteServlet
 */
@WebServlet("/qna/deleteQnaComment.do")
public class QnaCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int no = Integer.parseInt(request.getParameter("no")); //question_no
		int result = new QnaService().QnaCommentDelete(no);
		
		String msg,loc;
		if(result>0) {
			//정상작동하면 qna.jsp로 이동
			msg = "정상적으로 삭제되었습니다.";
			loc = "/qna/insertQna.do";
		}else {
			//등록불가능->등록실패결과출력후 qna-view.jsp로 이동
			/* request.setAttribute("no", no); */
			msg = "QnA 삭제실패";
			loc = "/qna/viewQna.do?no="+no;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);
	}

}
