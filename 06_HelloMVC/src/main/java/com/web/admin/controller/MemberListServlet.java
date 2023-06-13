package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminService;
import com.web.board.model.service.BoardService;
import com.web.member.model.dto.Member;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/admin/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("MemberListServlet실행");

		// 페이징처리하기 : cPage, numPerpage
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));// 현재(current)페이지를 저장하는 변수
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		int numPerpage = 5;// 페이지당 출력될 데이터 수를 저장하는 변수
		try {
			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));// 현재(current)페이지를 저장하는 변수
		} catch (NumberFormatException e) {
			numPerpage = 10;
		}
		// DB의 member테이블에 저장된 전체 회원을 가져와 화면에 출력해주는 기능
		// 1. DB에서 member테이블에 있는 데이터 가져오기
		List<Member> members = new AdminService().selectMemberAll(cPage, numPerpage);

		// 2. DB에서 가져온 데이터 저장(화면출력)
		request.setAttribute("members", members);

		// 3. 페이지바를 구성
		// 1)DB에 저장된 전체 데이터의 수를 가져오기 : totalData
		int totalData = new AdminService().selectMemberCount();

		// 2)전체 페이지 수 계산하기(*소수점 주의 : 소수점 -> 올림) : totalPage
		int totalPage = (int) Math.ceil((double) totalData / numPerpage);// v : 형변환 잘 생각하기, double안하면 마지막 페이지 빠질 수 있음

		// 3) 페이지바 시작번호 계산하기 : pageBarSize, pageNo, pageEnd
		int pageBarSize = 5;// pageBar에 출력될page번호의 갯수를 저장하는 개수
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;// v : (cPage-1)/pageBarSize는 분자%분모의 몫
		int pageEnd = pageNo + pageBarSize - 1;

		// 4. 페이지바를 구성하는 html저장하기 : pageBar
		String pageBar = "";// append방식이 더 효율적
		// 이전 표시하기
//		if (pageNo == 1) {
//			pageBar += "<span>[이전]</span>";
//		} else {
//			pageBar += "<a href='" + request.getRequestURI() + "?cPage=" + (pageNo - 1) + "'>[이전]</a>";
//		}
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()+"?cPage="+(pageNo-1)
					+"&numPerpage="+numPerpage+"'>[이전]</a> ";
		}
		// 선택할 페이지 번호 출력하기
//		while (!(pageNo > pageEnd || pageNo > totalPage)) {// v: 부정연산_조건문이 다 false여야 true
//			if (pageNo == cPage) {
//				pageBar += "<span>" + pageNo + "</span>";
//			} else {
//				pageBar += "<a href='" + request.getRequestURI() + "?cPage=" + pageNo + "'>" + pageNo + "</a>";
//			}
//			pageNo++;
//		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getRequestURI()
						+"?cPage="+(pageNo)
						+"&numPerpage="+numPerpage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		// 다음 출력
//		if (pageNo > totalPage) {
//			pageBar += "<span>[다음]</span>";
//		} else {
//			pageBar += "<a href='" + request.getRequestURI() + "?cPage=" + pageNo + "'>[다음]</a>";
//		}
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()
					+"?cPage="+(pageNo)
					+"&numPerpage="+numPerpage+"'>[다음]</a>";
		}
		request.setAttribute("pageBar", pageBar);
		
		// 4.출력할 화면을 선택(이동)
		request.getRequestDispatcher("/views/admin/manageMember.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
