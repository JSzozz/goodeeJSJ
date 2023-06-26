package com.btc.notice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.dto.Notice;
import com.btc.notice.model.service.NoticeService;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/notice/searchNotice.do")
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 보낸 데이터를 기준으로 member테이블에서 해당하는 데이터를 보내줌(입력타입과 입력값을 이용해서 회원필터기능을 만드는 로직)
    	//header 관리자상태로 회원조회누르면 여기로와서 입력타입,입력값가지고 리스트를 멤버로 저장 저장한걸 기존 서블릿으로 이동
    	//1. 입력타입과 입력값을 받아오기
    	String searchType=request.getParameter("search-type");
		String keyword=request.getParameter("keyword");
		
		String categoryName = (String)request.getParameter("categoryName");
		String communityTitle = (String)request.getParameter("communityTitle");
		
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("communityTitle", communityTitle);
		
		//searchType에 따른 searchKeyword값을 map방식으로 저장
		Map map=Map.of("type",searchType,"keyword",keyword);
		
		//DB의 member테이블에 저장된 전체 회원을 가져와 화면에 출력해주는 기능
		//페이징처리하기
		int cPage; //현재페이지
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerpage; //페이지당 출력될 데이터 수
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=5;
		}
		//cPage에 따른 numPerpage값을 map방식으로 저장
		Map pagemap=Map.of("cPage",cPage,"numPerpage",numPerpage);
		//1. DB에서 member테이블에 있는 데이터 가져오기
		List<Notice> notices=new NoticeService().searchNotice(pagemap,map);
		//2. DB에서 가져온 데이터 저장(화면출력)
		request.setAttribute("notices", notices);
		//3. 페이지바를 구성
		// 1) DB에 저장된 테이블에서 조건에 해당하는 데이터의 수를 가져오기
		int totalData=new NoticeService().selectNoticeSearchCount(map);
//		System.out.println("totalData : "+totalData);
		// 2) 전체페이지수를 계산하기 * 소수점 주의!
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		// 3) 페이지바 시작번호 계산하기
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		//4. 페이지바를 구성하는 html저장하기
		String pageBar="";
		//이전페이지
		if(pageNo==1) { //===현재페이지가 1이면===
			pageBar+="<li class='page-item'><a class='page-link' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
		}else {
			pageBar+="<li class='page-item'>"
			+ "<a class='page-link' href='"+request.getRequestURI()+"?cPage="+(cPage-1)+"&numPerpage="+numPerpage+"&search-type="+searchType+"&keyword="+keyword
			+ "' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
		}
		//페이징 번호 (현재페이지=1, 페이징처리의 시작번호=1 다음pageNo는 6이므로 6까지 증가하면서 증가시킨 pageNo별 주소 할당)
		while(!(pageNo>pageEnd||pageNo>totalPage)) { //pageEnd가 pageNo보다 크고 , totalPage가 pageNo보다 크면 반복
			if(pageNo==cPage) { //현재 페이지가 시작페이지면
				pageBar+="<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>"; //클릭 비활성화
			}else {
				//pageNo 증가 로직
				pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
						+"?cPage="+pageNo+"&numPerpage="+numPerpage+"&search-type="+searchType+"&keyword="+keyword+"'>"
						+pageNo+"</a></li>";
			}pageNo++;
		}
		//다음페이지
		if(pageNo>totalPage) { //===pageNo가 전체페이지보다 커지면===
			pageBar+="<li class='page-item'><a class='page-link' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>"; //클릭 비활성화
		}else {
			pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
			+"?cPage="+pageNo+"&numPerpage="+numPerpage+"&search-type="+searchType+"&keyword="+keyword+"'aria-label='Next'>"
					+ " <span aria-hidden='true'>&raquo;</span></a></li>";
		}
		request.setAttribute("pageBar", pageBar);
		//3. 출력할 화면을 선택(이동)
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
