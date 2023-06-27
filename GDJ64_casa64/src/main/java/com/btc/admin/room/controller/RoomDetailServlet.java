package com.btc.admin.room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;
import com.btc.rooms.model.vo.OptionFree;
import com.btc.rooms.model.vo.OptionXtra;
import com.btc.rooms.model.vo.Room;
import com.btc.rooms.model.vo.RoomOption;

/**
 * Servlet implementation class RoomDetailByNoServlet
 */
@WebServlet("/admin/room/roomDetail.do")
public class RoomDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roomNo=Integer.parseInt(request.getParameter("roomNo")); 
		Room r=new AdminRoomService().viewRoom(roomNo);
		List<OptionFree> frees=new AdminRoomService().selectAllFree();
		List<OptionXtra> xtras=new AdminRoomService().selectAllXtra();
		List<RoomOption> options=new AdminRoomService().selectCheckedOption(roomNo);
		request.setAttribute("room", r);
		request.setAttribute("frees", frees);
		request.setAttribute("xtras", xtras);
		request.setAttribute("options", options);
		request.getRequestDispatcher("/views/admin/room-check.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
