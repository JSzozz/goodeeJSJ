package com.ajax.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.admin.model.service.AdminService;
import com.web.member.model.dto.Member;

/**
 * Servlet implementation class GsonTestServlet
 */
@WebServlet("/gsontest.do")
public class GsonTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GsonTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Member> list=new AdminService()
				.selectMemberByKeyword("userId", "a", 1, 30);
		Member m=list.get(0);
		System.out.println(m);
		//Gson라이브러리를 이용해서 json파싱하기
		//Gson클래스를 생성한다.
		Gson gson=new Gson();
		//파싱해주는 매소드를 제공 -> toJson(json으로 변경할 객체[,outputStream]);
		response.setContentType("application/json;charset=utf-8");
		//gson.toJson(m,response.getWriter());
		gson.toJson(list,response.getWriter());
//		gson.fromJson(); vo객체로 만들어줌 -> JSON형태로 전송된 데이터를....
		String data=request.getParameter("data");
		Member requestData=gson.fromJson(data,Member.class);
		System.out.println(requestData);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
