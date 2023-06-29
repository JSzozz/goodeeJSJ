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
 * Servlet implementation class QnaCommentUpdateServlet
 */
@WebServlet("/qna/updateQnaComment.do")
public class QnaCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int no=Integer.parseInt(request.getParameter("no"));
		String categoryName = (String)request.getParameter("categoryName");
		String communityTitle = (String)request.getParameter("communityTitle");
		
		System.out.println("categoryName : "+categoryName+"communityTitle : "+communityTitle);
//		request.setAttribute("no",no);
//		request.setAttribute("categoryName",categoryName);
//		request.setAttribute("communityTitle",communityTitle);
		
		QnaComment qc = QnaComment.builder()
				.QnaCommentNo(Integer.parseInt(request.getParameter("QnaCommentNo"))) //pk구분자 
				.QnaRef(Integer.parseInt(request.getParameter("QnaRef")))
				.QnaCommentLevel(Integer.parseInt(request.getParameter("QnaCommentLevel")))
				.QnaCommentWriter(request.getParameter("QnaCommentWriter"))
				.QnaCommentContent(request.getParameter("QnaCommentContent"))
				.build();
		
		//댓글 수정
		int result=new QnaService().updateQnaComment(qc);

		String msg,loc;
		if(result>0) {
			msg = "정상적으로 수정되었습니다.";
		}else {
			msg = "수정 실패";
		}
		int no=qc.getQnaRef();
		/* String loc = "/board/boardView.do?no=" */
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/qna/viewQna.do?no="+no+"&categoryName="+categoryName+"&communityTitle"+communityTitle);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
