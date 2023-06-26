package com.btc.admin.booking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminBookingService;
import com.btc.booking.model.vo.Booking;

@WebServlet("/admin/booking/infoBooking.do")
public class InfoBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InfoBookingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingNo = Integer.parseInt(request.getParameter("bookingNo"));
		
		Booking bookingInfo = AdminBookingService.getBookingService().infoBooking(bookingNo);
		
		response.getWriter().print(bookingInfo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
