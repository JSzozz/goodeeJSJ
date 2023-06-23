package com.btc.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.dto.Notice;
import com.btc.notice.model.service.NoticeService;

/**
 * Servlet implementation class CategoryInsertNameServlet
 */
@WebServlet("/notice/insertNotice.do")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//주제, 이름을 받아 표시하기(categoryimage , board_tab)
		String categoryName = request.getParameter("categoryName");
		String communityTitle = request.getParameter("communityTitle");
		request.setAttribute("categoryName", "COMMUNITY");
		request.setAttribute("communityTitle", "이용후기");
		
		//게시물 & 페이징 처리
		int cPage; //현재페이지
		try {
			cPage=Integer.parseInt(request.getParameter("cPage")); //받아온 현재페이지가 있으면 가져온다.
		}catch(NumberFormatException e) {
			cPage=1; //없으면 1을 기준으로 설정
		}
		int numPerpage; //페이지당 출력될 데이터 수
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage")); //받아온 페이지당 출력수가 있으면 가져온다.
		}catch(NumberFormatException e) {
			numPerpage=5; //없으면 5를 기준으로 설정
		}
		String pageBar="";
		int totalData=new NoticeService().selectNoticeCount(); //전체 게시물 수
		List<Notice> notices=new NoticeService().selectNotice(cPage,numPerpage); //게시물 가져오기
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5; //페이지당 페이징처리할 넘버링 개수
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1; //페이징처리의 시작번호
		int pageEnd=pageNo+pageBarSize-1; //페이징처리의 끝번호
		
		//이전페이지
		if(pageNo==1) { //===현재페이지가 1이면===
			pageBar+="<li class='page-item'><a class='page-link' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
		}else {
			pageBar+="<li class='page-item'>"
			+ "<a class='page-link' href='"+request.getRequestURI()+"?cPage="+(cPage-1)+"&numPerpage="+numPerpage+"&categoryName="+categoryName+"&communityTitle="+communityTitle
			+ "' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
		}
		//페이징 번호 (현재페이지=1, 페이징처리의 시작번호=1 다음pageNo는 6이므로 6까지 증가하면서 증가시킨 pageNo별 주소 할당)
		while(!(pageNo>pageEnd||pageNo>totalPage)) { //pageEnd가 pageNo보다 크고 , totalPage가 pageNo보다 크면 반복
			if(pageNo==cPage) { //현재 페이지가 시작페이지면
				pageBar+="<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>"; //클릭 비활성화
			}else {
				//pageNo 증가 로직
				pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
						+"?cPage="+pageNo+"&numPerpage="+numPerpage+"&categoryName="+categoryName+"&communityTitle="+communityTitle+"'>"
						+pageNo+"</a></li>";
			}pageNo++;
		}
		//다음페이지
		if(pageNo>totalPage) { //===pageNo가 전체페이지보다 커지면===
			pageBar+="<li class='page-item'><a class='page-link' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>"; //클릭 비활성화
		}else {
			pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
			+"?cPage="+pageNo+"&numPerpage="+numPerpage+"&categoryName="+categoryName+"&communityTitle="+communityTitle+"'aria-label='Next'>"
					+ " <span aria-hidden='true'>&raquo;</span></a></li>";
		}

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("notices",notices);

		
		request.getRequestDispatcher("/views/board/notice.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
