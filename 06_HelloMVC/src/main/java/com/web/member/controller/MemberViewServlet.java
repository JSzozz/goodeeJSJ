package com.web.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.AESEncryptor;
import com.web.member.model.dto.Member;
import com.web.member.model.service.MemberService;

/**
 * Servlet implementation class MemberViesServlet
 */
@WebServlet(name="memberView", urlPatterns="/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("MemberviewServlet실행");
//		회원정보를 보여주는 화면으로 이동시키는 서블릿
//		1. DB에 로그인한 회원의 정보를 가져와 화면에 출력
		String userId=request.getParameter("userId");
		
		Member m=new MemberService().selectByUserId(userId);

		//Email, Phone복호화
		try {
			m.setEmail(AESEncryptor.decryptData(m.getEmail()));
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			m.setPhone(AESEncryptor.decryptData(m.getPhone()));
		}catch(Exception e) {
			e.printStackTrace();
		}
//		Email, Phone는 따로 try-catch를 하라고 함
		request.setAttribute("infoMember", m);
//		2. 화면에서 전달받은 회원 데이터 출력
		request.getRequestDispatcher("/views/member/memberView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
