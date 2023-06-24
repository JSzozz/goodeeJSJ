package com.btc.admin.booking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminBookingService;
import com.btc.booking.model.vo.Booking;

@WebServlet("/admin/booking/showAllBooking.do")
public class ShowAllBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowAllBooking() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Booking> allBookingList = AdminBookingService.getBookingService().allBookingList();
		
		String csv = "";
		for(int i=0; i<allBookingList.size(); i++) {
			if(i!=0) {
				csv += "\n";
			}
			csv += allBookingList.get(i);
		}
		System.out.println(csv);
		response.getWriter().print(csv);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
