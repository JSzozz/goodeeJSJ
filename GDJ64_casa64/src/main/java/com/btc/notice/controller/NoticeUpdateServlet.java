package com.btc.notice.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.dto.Notice;
import com.btc.notice.model.service.NoticeService;
import com.btc.notice.model.service.NoticeService;

/**
 * Servlet implementation class CategoryInsertNameServlet
 */
@WebServlet("/notice/updateNotice.do")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 내용, 수정댓글번호, 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String file = request.getParameter("file");
		int no = Integer.parseInt(request.getParameter("no"));
		
		request.setAttribute("categoryName", "COMMUNITY");
		request.setAttribute("communityTitle", "공지사항 수정");
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("file", file);
		request.getRequestDispatcher("/views/board/notice_update.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
