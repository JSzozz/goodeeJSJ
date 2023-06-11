package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.board.model.service.BoardService;
import com.web.board.model.vo.Board;

/**
 * Servlet implementation class BoardInsertEndServlet
 */
@WebServlet("/board/insertBoard.do")
public class BoardInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//파일 업로드 처리
		//1. multpart/form-data형식의 요청인지 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못된 접근입니다. 관리자에게 문의하세요");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
//		if(!ServletFileUpload.isMultipartContent(request)) {
//			request.setAttribute("msg", "잘못된 접근입니다. 관리자에게 문의하세요.");
//			request.setAttribute("loc", "/");
//			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//			return;
//		}
		//2.데이터 업로드 처리
		String path=getServletContext().getRealPath("/upload/board");
		
		//최대 파일크기 설정
		int maxSize=1024*1024*100;//100mb
		//인코딩 설정
		String encode="UTF-8";
		//리네임클래스 생성
		DefaultFileRenamePolicy dfr=new DefaultFileRenamePolicy();
		//MultipartRequest클래스 생성하기 -> 지정된 위치에 업로드된 파일을 저장시킴
		MultipartRequest mr=new MultipartRequest(request, path, maxSize, encode, dfr );
		
		//클라이언트가 보낸 데이터는 생성된 MultipartRequest객체를 이용해서 가져온다
		String boardTitle=mr.getParameter("boardTitle");
		String boardWriter=mr.getParameter("boardWriter");
		String boardContent=mr.getParameter("boardContent");
		
		//저장된 파일에 대한 정보도 가져올 수 있음
		//원본파일명, 재정의 파일명 등의 정보를 가져올 수 있다.
		String orifilename=mr.getOriginalFileName("upFile");
		String renamefilename=mr.getFilesystemName("upFile");
		
		Board b=Board.builder()
				.boardTitle(boardTitle)
				.boardWriter(boardWriter)
				.boardContent(boardContent)
				.boardOriginalFilename(orifilename)
				.boardRenamedFilename(renamefilename)
				.build();
	
		int result= new BoardService().insertBoard(b);
		String msg="게시글 등록 완료", loc="/board/boardList.do";
		if(result==0) {
			msg="게시글 등록실패";
			loc="/board/insertForm.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);	
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
