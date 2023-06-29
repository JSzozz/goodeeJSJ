package com.btc.booking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.booking.model.service.BookingService;
import com.btc.booking.model.vo.Booking;
import com.btc.booking.model.vo.OptionXtra;
import com.btc.booking.model.vo.SeasonalPrice;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class RoomSearchServlet
 */
@WebServlet("/booking/roomSearchFilterServlet.do")
public class RoomSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int roomNo=Integer.parseInt(request.getParameter("roomNo"));
		System.out.println("GD"+roomNo);
		
		HttpSession session= request.getSession();
	
		Room room = new BookingService().selectRoomByRoomNo(roomNo);
	    List<Room> rooms=new ArrayList();
	    rooms.add(room);
	    session.setAttribute("rooms", rooms);

		List<Booking> bookings=new BookingService().selectAllBooking();
//		request.setAttribute("bookings", bookings);
		session.setAttribute("bookings", bookings);


		List<SeasonalPrice> seasons=new BookingService().selectAllSeason();
//		request.setAttribute("seasons", seasons);
		session.setAttribute("seasons", seasons);
		
		List<OptionXtra> xtraOptions= new BookingService().selectAllOption();
//		request.setAttribute("xtraOptions", xtraOptions);
		session.setAttribute("xtraOptions", xtraOptions);
		
		request.getRequestDispatcher("/views/booking/booking-list1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
