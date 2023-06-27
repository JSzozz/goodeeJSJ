package com.btc.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/notice/deleteNotice.do")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		int result = new NoticeService().NoticeDelete(no);
		//확인되면 다시 돌아감(boardView)
		String msg,loc;
		if(result>0) {
			msg = "정상적으로 삭제되었습니다.";
			loc = "/notice/insertNotice.do";
		}else {
			//등록불가능->등록실패결과출력후 boardView로 이동
			request.setAttribute("no", no);
			msg = "공지사항 삭제실패";
			loc = "/notice/viewNotice.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
