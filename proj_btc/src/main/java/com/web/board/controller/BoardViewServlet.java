package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;
import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView.do")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("no"));	
		
		Cookie[] cookies=request.getCookies();
		String boardRead="";
		boolean isRead=false;
		if(cookies!=null) {
			for(Cookie c: cookies) {
				if(c.getName().equals("boardRead")) {
					boardRead=c.getValue();
					if(boardRead.contains("|"+boardNo+"|")) {
						isRead=true;
					}
					break;
				}
			}
		}
		if(isRead==false) {
			Cookie c=new Cookie("boardRead", boardRead+"|"+boardNo+"|");// | | 는 구분자
			c.setMaxAge(60*60*24);
			response.addCookie(c);
		}
		
		Board b=new BoardService().selectBoardByNo(boardNo, isRead);
		request.setAttribute("board", b);
		
		//boardcomment 가져오기
		List<BoardComment> bc=new BoardService().selectBoardCommentByNo(boardNo);
		request.setAttribute("boardComment", bc);
		
		
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
