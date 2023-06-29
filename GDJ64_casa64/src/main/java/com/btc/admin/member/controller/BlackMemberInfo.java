package com.btc.admin.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.ApplicationBufferHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.btc.admin.model.service.AdminMemberService;
import com.btc.member.model.dto.BlackFile;
import com.btc.member.model.dto.BlackMember;



/**
 * Servlet implementation class BlackMemberInfo
 */
@WebServlet("/admin/member/blackView.do")
public class BlackMemberInfo extends HttpServlet {
	
	private AdminMemberService service=new AdminMemberService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlackMemberInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		BlackMember m=service.selectBlackMember(memberNo);
		String reason=m.getReason();
		BlackFile mf=service.selectBlackFile(memberNo);
		String imgs=null;
		if(mf!=null) {
			imgs=mf.getFileName();
		}
		
		JSONObject data=new JSONObject();
		data.put("imgs", imgs);
		data.put("reason", reason);
		
		
		
		
		

		PrintWriter out=response.getWriter();
		out.print(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
