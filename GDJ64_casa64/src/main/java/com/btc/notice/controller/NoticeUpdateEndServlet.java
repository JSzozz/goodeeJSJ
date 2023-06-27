package com.btc.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.btc.notice.model.dto.Notice;
import com.btc.notice.model.dto.Notice_images;
import com.btc.notice.model.service.NoticeService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeUpdateEndServlet
 */
@WebServlet("/notice/updateNoticeEnd.do")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("loc", "/views/board/notice.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		//절대경로 가져오기
		String path = getServletContext().getRealPath("/images/notice");
		//최대 파일크기
		int maxSize = 1024*1024*50; //50mb
		//인코딩 설정
		String encode = "utf-8";
		//rename 클래스 설정
		DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request,path,maxSize,encode,dfr);
		
		//나머지 문자열은 MultipartRequest객체를 이용해야 된다.
		
		// 1. 넣어줘야할 값을 파라미터로 받기
		String title = mr.getParameter("title"); // 제목
		String contents = mr.getParameter("contents"); // 내용
		String fileName = mr.getOriginalFileName("uploadFile"); //파일
		String saveFilename = mr.getFilesystemName("uploadFile");
		int noticeNo = Integer.parseInt(mr.getParameter("noticeNo")); //공지사항
		
		Notice n = Notice.builder()
				.noticeNo(noticeNo)
				.noticeTitle(title)
				.noticeContent(contents)
				.build();
		
		Notice_images image = Notice_images.builder()
				.saveFilename(saveFilename)
				.fileName(fileName)
				.noticeNo(noticeNo)
				.build();
		
		int result = new NoticeService().updateNotice(n,image);
		response.sendRedirect(request.getContextPath()+"/notice/insertNotice.do");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
