package com.btc.notice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.notice.model.dto.Qna;
import com.btc.notice.model.service.QnaService;

/**
 * Servlet implementation class CategoryInsertNameServlet
 */
@WebServlet("/qna/insertQna.do") //qna.jsp로이동하는 서블릿
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//주제, 이름을 받아 표시하기
		request.setAttribute("categoryName", "COMMUNITY");
		request.setAttribute("communityTitle", "QnA");
		
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
		int totalData=new QnaService().selectQnaCount(); //전체 게시물 수
		System.out.println("전체 개수 : "+totalData);
		List<Qna> Qnas=new QnaService().selectQna(cPage,numPerpage); //게시물 가져오기
		System.out.println("게시물 개수 : "+Qnas);
		List<String>$memberName = new ArrayList<String>();
		for(int i=0; i<totalData;i++) { //처음에는 1~5까지 하고, 그다음은 6~10까지
			System.out.println(i);
			System.out.println(Qnas.get(i).getMemberNo());
			String memberName= new QnaService().checkName(Qnas.get(i).getMemberNo()); //번호로 이름 가져오기
			$memberName.add(memberName);
			System.out.println("memberName : "+memberName+"\nQnas.get(i).getMemberNo() : "+Qnas.get(i).getMemberNo()+"\n$memberName : "+$memberName);
		}
//		System.out.println($memberName);
			request.setAttribute("memberName", $memberName);
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5; //페이지당 페이징처리할 넘버링 개수
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1; //페이징처리의 시작번호
		int pageEnd=pageNo+pageBarSize-1; //페이징처리의 끝번호
		
		//이전페이지
		if(pageNo==1) { //===현재페이지가 1이면===
			pageBar+="<li class='page-item'><a class='page-link' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
		}else {
			pageBar+="<li class='page-item'>"
			+ "<a class='page-link' href='"+request.getRequestURI()+"?cPage="+(cPage-1)+"&numPerpage="+numPerpage
			+ "' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>";
		}
		//페이징 번호 (현재페이지=1, 페이징처리의 시작번호=1 다음pageNo는 6이므로 6까지 증가하면서 증가시킨 pageNo별 주소 할당)
		while(!(pageNo>pageEnd||pageNo>totalPage)) { //pageEnd가 pageNo보다 크고 , totalPage가 pageNo보다 크면 반복
			if(pageNo==cPage) { //현재 페이지가 시작페이지면
				pageBar+="<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>"; //클릭 비활성화
			}else {
				//pageNo 증가 로직
				pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
						+"?cPage="+pageNo+"&numPerpage="+numPerpage+"'>"
						+pageNo+"</a></li>";
			}pageNo++;
		}
		//다음페이지
		if(pageNo>totalPage) { //===pageNo가 전체페이지보다 커지면===
			pageBar+="<li class='page-item'><a class='page-link' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>"; //클릭 비활성화
		}else {
			pageBar+="<li class='page-item'><a class='page-link' href='"+request.getRequestURI()
			+"?cPage="+pageNo+"&numPerpage="+numPerpage+"'aria-label='Next'>"
					+ " <span aria-hidden='true'>&raquo;</span></a></li>";
		}
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("Qnas",Qnas);

		
		request.getRequestDispatcher("/views/board/qna.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}