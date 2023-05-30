package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.model.dto.MemberDTO;
import com.web.model.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/updateEndMember.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		회원정보를 수정하는 서비스
//		1. 클라이언트가 보낸 데이터를 받아오기
		MemberDTO m=MemberDTO.builder()
				.userId(request.getParameter("userId"))
				.userName(request.getParameter("userName"))
				.age(Integer.parseInt(request.getParameter("age")))
				.gender(request.getParameter("gender").charAt(0))
				.email(request.getParameter("email"))
				.phone(request.getParameter("phone"))
				.address(request.getParameter("address"))
				.hobby(request.getParameterValues("hobby"))
				.build();
//		2. DB데이터 수정하기
		int result=new MemberService().updateMember(m);
//		3. 결과 전송하기
		String msg="",loc="";
		if(result>0) {
			msg="회원정보가 수정되었습니다";
			loc="/";
			
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", new MemberService().selectByUserId(m.getUserId()));
//			ㄴ 정보 수정 버튼 클릭 후 메인페이지의 내 이름 바꿈처리
		}else {
			msg="회원정보 수정 실패헀습니다. 다시 시도하세요";
			/* loc="/views/member/memberView.jsp"; */
			loc="/member/memberView.do?userId="+m.getUserId();
//			V : ?userId="+m.getUserId() : infoMember에 null오류 막기 위함! 
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
