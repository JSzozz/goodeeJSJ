package com.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.AESEncryptor;
import com.web.model.dto.MemberDTO;
import com.web.model.service.MemberService;

/**
 * Servlet implementation class EnrollMemberEndServlet
 */
@WebServlet("/member/enrollMemberEnd.do")
public class EnrollMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String userName=request.getParameter("userName");
		String email=request.getParameter("email");
		try {
			email=AESEncryptor.encryptData(email);
		}catch(Exception e) {
			System.out.println("email 암호화실패(*EnrollMemberEndServlet)");
		}
		int age=Integer.parseInt(request.getParameter("age"));//v : Integer.parseInt
		String phone=request.getParameter("phone");
		try {
			phone=AESEncryptor.encryptData(phone);
		}catch(Exception e){
			System.out.println("phone 암호화실패(*EnrollMemberEndServlet)");
		}
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		String[] hobbies=request.getParameterValues("hobby");//v : String[], getParameterValues()
//		System.out.println("userId_123 : "+userId);
//		System.out.println("password : "+password);
//		System.out.println("userName : "+userName);
//		System.out.println("email : "+email);
//		System.out.println("age : "+age);
//		System.out.println("phone : "+phone);
//		System.out.println("address : "+address);
//		System.out.println("gender : "+gender);
//		System.out.println("hobby : "+Arrays.toString(hobbies));
//		try{ 
//			m.setEmail(AESEncryptor.encryptData(gender))//중간에 빠짐
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		
		MemberDTO m= MemberDTO.builder().userId(userId).password(password).userName(userName)
					.age(age).email(email).gender(gender.charAt(0)).phone(phone).address(address)
					.hobby(hobbies).build();
		int result=new MemberService().insertMember(m);

		String msg="";
		String loc="";
		if(result>0) {
			//회원가입 입력 실패
			 msg="회원 가입을 축하드립니다.";
			 loc="/";
			
			
		}else{
			//회원가입 입력 실패(*msg.jsp)
			 msg="회원 가입에 실패하였습니다. \n 다시 시도헤주세요.";
			 loc="/member/enrollMember.do";
			
		}
		request.setAttribute("loc", loc);
		request.setAttribute("msg", msg);
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
