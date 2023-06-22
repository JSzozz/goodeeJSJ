package com.btc.admin.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.dao.AdminMemberDao;
import com.btc.admin.model.service.AdminMemberService;
import com.btc.member.model.dto.Member;

/**
 * Servlet implementation class AdminMemberList
 */
@WebServlet("/admin/memberList.do")
public class AdminMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage, numPerpage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {
			numPerpage=10;
		}
		
		StringBuffer pageBar=new StringBuffer();
		int totolData=new AdminMemberService().memberCount();
		int totalPage=(int)Math.ceil((double)totolData/numPerpage);
		int pageBarSize=5;
		int pageStart=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageStart+pageBarSize-1;
		
		if(pageStart==1) {
			pageBar.append("<span>[이전]</span>");
		}else {
			pageBar.append("<a href='"+request.getRequestURI()+"?cPage="+(pageStart-1)+"&numPerpage="+numPerpage+"'>[이전]</a>");
		}
		while(!(pageStart>pageEnd||pageStart>totalPage)) {
			if(pageStart==cPage) {
				pageBar.append("<span>"+pageStart+"</span>");
			}else {
				pageBar.append("<a href='"+request.getRequestURI()+"?cPage="+pageStart+"&numPerpage="+numPerpage+"'>"+pageStart+"</a>");
			}
			pageStart++;
		}
		if(pageStart>totalPage) {
			pageBar.append("<span>[다음]</span>");
		}else {
			pageBar.append("<a href='"+request.getRequestURI()+"?cPage="+pageStart+"&numPerpage="+numPerpage+"'>[다음]</a>");
		}
		request.setAttribute("pageBar", pageBar);
//		List<Member> list=new AdminMemberService().memberList();
//		request.setAttribute("members", list);
		request.getRequestDispatcher("/views/admin/admin-page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
