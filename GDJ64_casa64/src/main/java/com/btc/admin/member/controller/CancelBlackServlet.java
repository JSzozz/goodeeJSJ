package com.btc.admin.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminMemberService;
import com.btc.member.model.dto.BlackFile;
import com.btc.member.model.service.MemberService;

/**
 * Servlet implementation class CancelBlackServlet
 */
@WebServlet("/admin/member/cancelblack.do")
public class CancelBlackServlet extends HttpServlet {
	AdminMemberService service=new AdminMemberService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelBlackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no=Integer.parseInt(request.getParameter("memberNo"));
		new MemberService().updateBlack(no, "N");
		BlackFile bf=service.selectBlackFile(no);
		String bfName=null;
		if(bf!=null) {
			bfName=bf.getFileName();
		}
		
		String path=getServletContext().getRealPath("/upload/member/");
		File file=new File(path+bfName);
		
		PrintWriter out=response.getWriter();
		if(file.exists()) {
			file.delete();
			service.deleteBlackFile(no);
		}
		
		int deleteBlackResult=service.deleteBlackMember(no);
		if(deleteBlackResult>0) {
			out.print(1+"");
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
