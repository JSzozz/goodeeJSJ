package com.btc.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.dto.QnaComment;
import com.btc.notice.model.service.QnaService;

/**
 * Servlet implementation class QnaCommentInsertServlet
 */
@WebServlet("/qna/insertQnaComment.do")
public class QnaCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int no=Integer.parseInt(request.getParameter("no"));
		String categoryName = (String)request.getParameter("categoryName");
		String communityTitle = (String)request.getParameter("communityTitle");
		
		request.setAttribute("no",no);
		request.setAttribute("categoryName",categoryName);
		request.setAttribute("communityTitle",communityTitle);
		
		QnaComment qc = QnaComment.builder()
				.QnaRef(Integer.parseInt(request.getParameter("QnaRef")))
				.QnaCommentLevel(Integer.parseInt(request.getParameter("QnaCommentLevel")))
				.QnaCommentWriter(request.getParameter("QnaCommentWriter"))
				.QnaCommentContent(request.getParameter("QnaCommentContent"))
				.build();
		//댓글 등록
		int result=new QnaService().insertQnaComment(qc);
		String view;
		if(result>0) {
			//등록가능->등록값을 전송
			view=request.getContextPath()+"/qna/viewQna.do?no="+qc.getQnaRef();
			//기존에 남아있는 insertComment값이 남아있기때문에 버리기위해 sendRedirect
			response.sendRedirect(view);
		}else {
			//등록불가능->등록실패결과출력후 boardView로 이동
			request.setAttribute("msg", "댓글등록실패");
			request.setAttribute("loc", "/qna/viewQna.do?no="+qc.getQnaRef());
			view="/views/common/msg.jsp";
			request.getRequestDispatcher(view).forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
