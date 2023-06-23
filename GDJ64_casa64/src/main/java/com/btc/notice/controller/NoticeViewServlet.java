package com.btc.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.service.NoticeService;
import com.btc.notice.model.dto.Notice;


@WebServlet("/notice/viewNotice.do")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 선택한 게시물의 번호
		int no=Integer.parseInt(request.getParameter("no"));
		//새로고침해도 조회수를 증가하지않게 만드는 로직
		
		//다른 페이지의 조회수를 관리하기위해 이전값 불러오기
		//쿠키 조회
		Cookie[] cookies=request.getCookies();
		String noticeRead=""; //?
		boolean isRead=false;
		if(cookies!=null) {	
			for(Cookie c : cookies) {
				if(c.getName().equals("noticeRead")) { //쿠키에 key값이 boardRead와 같으면 true
					noticeRead=c.getValue(); //해당 key의 value를 저장
					//아래 로직이 있어야하는 이유 : 만약 로직을 지우면 key가 boardRead인 쿠키가 value가 없더라도 true를 반환하여 로직이 꼬이게된다.
					if(noticeRead.contains("|"+no+"|")) { //지정한 양식(정확한 조회수 집계를위해 사용)이 있으면
						isRead=true;
					}
					break;
				}
			}
		}
		//쿠키 생성(해석은 여기를 먼저하는게 쉬움)
		//쿠키에 지정한 양식이 없으면 true
		if(!isRead) {
			//boardRead+"|"+no+"|" : boardRead가 이전의 "|"+no+"|"를 저장하는 것이기 때문에 "|"+prev_no+"|" , "|"+no+"|" 형식으로 된다.
			Cookie c=new Cookie("noticeRead",noticeRead+"|"+no+"|");
			c.setMaxAge(60*60*24);
			response.addCookie(c);			
		}
		//게시판 상세내용
		Notice n=new NoticeService().selectNoticeByNo(no,isRead);
		
		//게시판 상세내용
		request.setAttribute("notice", n);
		request.getRequestDispatcher("/views/board/notice_view.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
