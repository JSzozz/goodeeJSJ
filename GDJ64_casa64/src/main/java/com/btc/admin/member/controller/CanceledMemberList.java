package com.btc.admin.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminMemberService;
import com.btc.member.model.dto.CancelMember;
import com.btc.member.model.dto.Member;

/**
 * Servlet implementation class CanceledMemberList
 */
@WebServlet("/admin/cancelMemberList.do")
public class CanceledMemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CanceledMemberList() {
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
			numPerpage=5;
		}
		StringBuffer pageBar=new StringBuffer();
		int totolData=new AdminMemberService().canceledMemberCount();
		int totalPage=(int)Math.ceil((double)totolData/numPerpage);
		int pageBarSize=5;
		int pageStart=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageStart+pageBarSize-1;
		//이전
				if(pageStart==1) {
					pageBar.append("<li class='page-item'><a class='page-link' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
				}else {
					pageBar.append("<li class='page-item'><a class='page-link' aria-label='Previous' href='"+request.getRequestURI()+"?cPage="+(pageStart-1)+"&numPerpage="+numPerpage+"'><span aria-hidden='true'>&laquo;</span></a></li>");
				}
				//현재
				while(!(pageStart>pageEnd||pageStart>totalPage)) {
					if(pageStart==cPage) {
						pageBar.append("<li class='page-item active'><a class='page-link'>"+pageStart+"</a></li>");
					}else {
						pageBar.append("<li class='page-item'><a class='page-link' href='"+request.getRequestURI()+"?cPage="+pageStart+"&numPerpage="+numPerpage+"'>"+pageStart+"</a></li>");
					}
					pageStart++;
				}
				//다음
				if(pageStart>totalPage) {
					pageBar.append("<li class='page-item'><a class='page-link' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");
				}else {
					pageBar.append("<li class='page-item'><a class='page-link' aria-label='Next' href='"+request.getRequestURI()+"?cPage="+pageStart+"&numPerpage="+numPerpage+"'><span aria-hidden='true'>&raquo;</span></a></li>");
				}
				request.setAttribute("pageBar", pageBar);
				List<CancelMember> list=new AdminMemberService().canceledMemberList(cPage, numPerpage);
				request.setAttribute("members", list);
				request.getRequestDispatcher("/views/admin/leave-member.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
