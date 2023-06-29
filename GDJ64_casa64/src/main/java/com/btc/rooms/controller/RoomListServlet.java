package com.btc.rooms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.rooms.model.service.RoomService;
import com.btc.rooms.model.vo.Room;
import com.btc.rooms.model.vo.RoomImage;

/**
 * Servlet implementation class RoomListServlet
 */
@WebServlet("/RoomListServlet.do")
public class RoomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Room> rooms=new RoomService().selectAllRoom();
		Map<Object,List<Room>> groupRoom=rooms.stream().collect(Collectors.groupingBy((r)->{
			if(r.getRoomName().contains("OCEAN")) return "ocean";
			else if(r.getRoomName().contains("SUNSET")) return "sunset";
			else if(r.getRoomName().contains("SPA")) return "spa";
			else return "etc";
		},Collectors.toList()));
		
		List<RoomImage> images=new RoomService().selectAllImages();
		
		request.setAttribute("images", images);
		request.setAttribute("rooms", rooms);
		request.setAttribute("ocean", groupRoom.get("ocean"));
		request.setAttribute("sunset", groupRoom.get("sunset"));
		request.setAttribute("spa", groupRoom.get("spa"));
		
		request.getRequestDispatcher("/views/rooms/roomsMain.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
