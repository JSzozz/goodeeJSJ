package com.btc.admin.booking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminBookingService;

@WebServlet("/admin/booking/cancelBooking.do")
public class CancelBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CancelBookingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingNo = Integer.parseInt(request.getParameter("bookingNo"));
		
		int result = AdminBookingService.getBookingService().cancelBooking(bookingNo);
		
		response.getWriter().print(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
