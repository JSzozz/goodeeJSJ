package com.btc.admin.room.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class UpdateRoomServlet
 */
@WebServlet("/admin/room/updateRoom.do")
public class UpdateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//객실정보 수정
		Room r=Room.builder().roomNo(Integer.parseInt(request.getParameter("roomNo"))).roomName(request.getParameter("roomName")).roomSize(Integer.parseInt(request.getParameter("roomSize"))).roomPrice(Integer.parseInt(request.getParameter("roomPrice")))
				.bookable(request.getParameter("bookable").charAt(0)).roomCap(Integer.parseInt(request.getParameter("roomCap"))).roomMaxCap(Integer.parseInt(request.getParameter("roomMaxCap"))).roomDescription(getServletInfo()).roomImage(getServletInfo()).build();
		int result=new AdminRoomService().updateRoom(r);
		String msg="",loc="";
		if(result>0) {
			msg="객실정보가 수정되었습니다";
			loc="/admin/room/roomDetail.do?roomNo="+r.getRoomNo();
		}else {
			msg="객실정보 등록에 실패했습니다. 다시 입력해주세요.";
			loc="/admin/room/roomDetail.do?roomNo="+r.getRoomNo();
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
