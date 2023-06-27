package com.btc.admin.room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class SearchRoomByKeywordServlet
 */
@WebServlet("/admin/room/searchRoomByKeyword.do")
public class SearchRoomByKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRoomByKeywordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword=request.getParameter("keyword");
		
		int cPage;
		int numPerpage=5;
	      try {
	         cPage=Integer.parseInt(request.getParameter("cPage"));
	      }catch(NumberFormatException e) {
	         cPage=1;
	      }
	          
	      List<Room> keywordRooms=new AdminRoomService().selectRoomByKeyword(keyword,cPage,numPerpage);
	      
	      request.setAttribute("rooms", keywordRooms);
	      String pageBar="";
	      int totalData=new AdminRoomService().selectRoomByKeywordCount(keyword);
	      int totalPage=(int)Math.ceil((double)totalData/numPerpage);
	      int pageBarSize=5;
	      int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
	      int pageEnd=pageNo+pageBarSize-1;
	      
	      if(pageNo==1) {
	         pageBar+="<span>[이전]</span>";
	      }else {
	         pageBar+="<a href='"+request.getRequestURI()+"?keyword="+keyword+"&cPage="+(pageNo-1)+"&numPerpage="+numPerpage+"'>[이전]</a>";
	      }
	      
	      while(!(pageNo>pageEnd||pageNo>totalPage)) {
	         if(pageNo==cPage) {
	            pageBar+="<span>"+pageNo+"</span>";
	         }else {
	            pageBar+="<a href='"+request.getRequestURI()+"?keyword="+keyword+"&cPage="+pageNo+"&numPerpage="+numPerpage+"'>"+pageNo+"</a>";
	         }
	         pageNo++;
	      }
	      if(pageNo>totalPage) {
	         pageBar+="<span>[다음]</span>";
	      }else {
	         pageBar+="<a href='"+request.getRequestURI()+"?keyword="+keyword+"&cPage="+pageNo+"&numPerpage="+numPerpage+"'>[다음]</a>";

	      }
	      request.setAttribute("pageBar", pageBar);
	      request.getRequestDispatcher("/views/admin/rooms.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
