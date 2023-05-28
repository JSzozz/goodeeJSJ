package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.model.dto.MemberDTO;
import com.jsp.model.service.MemberService;

/**
 * Servlet implementation class MemberSearchByNameServlet
 */
@WebServlet("/memberByName.do")
public class MemberSearchByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchByNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. 클라이언트가 보낸 데이터를 가져온다.
		String name=request.getParameter("keyword");
//		System.out.println("입력값 : " +name);
		
		//2. DB의 member테이블에 member_name컬럼에 클라이언트가 보낸 데이터와 일치하는 값이 있는지 확인, 일치 데이터 가져오기
		List <MemberDTO> list=new MemberService().searchByName(name);
		
		//3. 가져온 데이터 저장하기
		request.setAttribute("list", list);
		//4. 화면설정
		request.getRequestDispatcher("/views/searchMember.jsp").forward(request, response);;
		
		
		
		
		
//		//1. DB에서 필요한 데이터를 가져온다. -> JDBC를 이용
//		List<MemberDTO> members=new MemberService().selectMemberAll(); 
//
//		//화면에 조회한 데이터를 전달하는 방법
//		request.setAttribute("members", members);
//		
//		//2. DB정보를 출력할 화면을 출력한다. -> jsp를 이용
//		RequestDispatcher rd=request.getRequestDispatcher("/views/memberList.jsp");
//		//getRequestDispatcher의 매개변수 /이하는 webapp폴더 이하이다!
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
