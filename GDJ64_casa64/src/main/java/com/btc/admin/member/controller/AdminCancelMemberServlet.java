package com.btc.admin.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminMemberService;
import com.btc.member.model.dto.Member;

import oracle.jdbc.driver.parser.util.Service;

/**
 * Servlet implementation class AdminCancelMemberServlet
 */
@WebServlet("/admin/member/cancelMember.do")
public class AdminCancelMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdminMemberService service=new AdminMemberService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCancelMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo=Integer.parseInt(request.getParameter("cancelM"));

		Member m=service.selectMember(memberNo);
		int insertResult=service.insertCancelMember(m.getMemberName(), m.getNickName(), m.getEmail(), m.getPhone());
		PrintWriter out=response.getWriter();
		if(insertResult>0) {
			int cancelResult=service.memberCancel(memberNo);
			if(cancelResult>0) {
				out.print(1+"");
			}else {
				out.print(0+"");
			}
		}else {
			out.print(0+"");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
