package com.btc.admin.room.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;

/**
 * Servlet implementation class DeleteRoomServlet
 */
@WebServlet("/admin/room/deleteRoom.do")
public class DeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roomNo=Integer.parseInt(request.getParameter("roomNo"));
		int result=new AdminRoomService().deleteRoom(roomNo);
		String msg="";
		String loc="";
		if(result>0) {
			msg="객실이 성공적으로 삭제되었습니다.";
			loc="/admin/room/showAllRoom.do";
		}else {
			msg="객실 삭제에 실패했습니다.";
			loc="/admin/room/roomDetail.do?roomNo="+roomNo;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		request.setAttribute("result", result);
		//msg와 loc
		//성공시 "객실을 성공적으로 삭제했습니다", room.jsp로 이동
		//실패시 "객실 삭제에 실패했습니다., room-check.jsp로 이동
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
