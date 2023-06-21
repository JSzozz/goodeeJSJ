package com.btc.rooms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.rooms.model.service.RoomService;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class RoomViewServlet
 */
@WebServlet("/RoomViewServlet.do")
public class RoomViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RoomViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* int roomNo=Integer.parseInt(request.getParameter("roomNo")); */
		String type=request.getParameter("type");
		List<Room> rooms=new RoomService().selectRoomByType(type);
		request.setAttribute("rooms", rooms);
		request.getRequestDispatcher("/views/rooms/roomsDetail.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
