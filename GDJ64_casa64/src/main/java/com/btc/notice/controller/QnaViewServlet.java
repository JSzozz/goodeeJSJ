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
@WebServlet("/qna/viewQna.do")
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
				
//				//쿠키 조회
//				Cookie[] cookies=request.getCookies();
//				String noticeRead="";
//				boolean isRead=false;
//				if(cookies!=null) {	
//					for(Cookie c : cookies) {
//						if(c.getName().equals("noticeRead")) { //쿠키에 key값이 boardRead와 같으면 true
//							noticeRead=c.getValue(); //해당 key의 value를 저장
//							if(noticeRead.contains("|"+no+"|")) { //지정한 양식(정확한 조회수 집계를위해 사용)이 있으면
//								isRead=true;
//							}
//							break;
//						}
//					}
//				}
//				//쿠키 생성 , 쿠키에 지정한 양식이 없으면 true
//				if(!isRead) {
//					Cookie c=new Cookie("noticeRead",noticeRead+"|"+no+"|");
//					c.setMaxAge(60*60*24);
//					response.addCookie(c);			
//				}
				//Qna 게시판
				Qna qna = new QnaService().selectQnaByNo(no);
				System.out.println(qna);
				//Qna 댓글 답글
				List<QnaComment> comments=new QnaService().selectQnaComment(no);
				
				//게시판 상세내용
				request.setAttribute("qna",qna);
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
