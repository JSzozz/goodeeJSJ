package com.btc.admin.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminMemberService;
import com.btc.member.model.dto.Member;
import com.btc.member.model.service.MemberService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class AdminBlackMemberServlet
 */
@WebServlet("/admin/member/blackMember.do")
public class AdminBlackMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdminMemberService service=new AdminMemberService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBlackMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String msg="",loc="";
		String path=getServletContext().getRealPath("/upload/member");
		int maxSize=1024*1024*100;
		String encode="UTF-8";
		DefaultFileRenamePolicy dfr=new DefaultFileRenamePolicy();
		MultipartRequest mr=new MultipartRequest(request, path,maxSize,encode,dfr);
		String fileRealName=mr.getOriginalFileName("fileName");
		String fileName=mr.getFilesystemName("fileName");
		String reason=mr.getParameter("reason");
		int memberNo= Integer.parseInt(mr.getParameter("memberNo"));
		Member m=service.selectMember(memberNo);
		if(m.getMemberBlack().equals("Y")) {
			msg="이미 블랙된 회원입니다";
			loc="/views/admin/list-member.jsp";
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		int insertResult=service.insertBlackMember(memberNo,m.getMemberName(),m.getNickName(),m.getEmail(),m.getPhone(),reason);
		int fileResult=service.insertBlackFile(memberNo, fileName, fileRealName);
		
		if(insertResult>0&&fileResult>0) {
			int result=new MemberService().updateBlack(memberNo);
			if(result>0) {
				msg="회원블랙성공";
				loc="/views/admin/list-member.jsp";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
			}else {
				msg="서버오류";
				loc="/views/admin/list-member.jsp";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
			}
		}else {
			msg="서버오류";
			loc="/views/admin/list-member.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
		}
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











