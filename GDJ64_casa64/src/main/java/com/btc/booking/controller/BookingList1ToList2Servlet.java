package com.btc.booking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.booking.model.service.BookingService;
import com.btc.booking.model.vo.Booking;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class ReserveList1ToList2Servlet
 */
@WebServlet("/booking/bookingList1ToList2.do")
public class BookingList1ToList2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingList1ToList2Servlet() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookingService service = new BookingService();

		String roomNm = request.getParameter("roomNm");
		int roomNo = service.searchRoomNo(roomNm);// roomNm->roomNo
		
		java.sql.Date checkIn = java.sql.Date.valueOf(request.getParameter("checkin"));
		java.sql.Date checkOut = java.sql.Date.valueOf(request.getParameter("checkout"));
		
		
		int guestAdult = Integer.parseInt(request.getParameter("guestAdult"));
		int guestChild = Integer.parseInt(request.getParameter("guestChild"));
		int guestInfant = Integer.parseInt(request.getParameter("guestInfant"));
		int bookingPrice = Integer.parseInt(request.getParameter("bookingPrice"));


		String roomPrice = request.getParameter("roomPrice");
		String persePrice = request.getParameter("persePrice");
		String optnPrice = request.getParameter("optnPrice");
        ArrayList<String> price = new ArrayList<>();
        price.add(roomPrice);
        price.add(persePrice);
        price.add(optnPrice);
		
		Booking b = Booking.builder().room(Room.builder().roomNo(roomNo).build()).checkIn(checkIn).checkOut(checkOut).guestAdult(guestAdult)
				.guestChild(guestChild).guestInfant(guestInfant).bookingPrice(bookingPrice).build();
		
		request.setAttribute("booking", b);
		request.setAttribute("roomNm", roomNm);
		request.setAttribute("roomNo", roomNo);
		request.setAttribute("price", price);


		request.getRequestDispatcher("/views/booking/booking-list2.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
