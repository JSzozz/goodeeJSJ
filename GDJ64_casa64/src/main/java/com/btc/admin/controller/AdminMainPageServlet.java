package com.btc.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminMainPageService;
import com.btc.admin.model.vo.Chart;
import com.btc.rooms.model.vo.Room;

@WebServlet("/admin/adminMainPage.do")
public class AdminMainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminMainPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberCount = AdminMainPageService.getAdminService().memberCount();
		int roomCount = AdminMainPageService.getAdminService().roomCount();
		int bookingRoomCount = AdminMainPageService.getAdminService().bookingRoomCount();
		int requestCancelRoomCount = AdminMainPageService.getAdminService().requestCancelRoomCount();
		
		List<Room> roomList = AdminMainPageService.getAdminService().roomList();
		List<Chart> chartBookingCount = AdminMainPageService.getAdminService().chartBookingCount();
		List<Chart> chartBookingPayment = AdminMainPageService.getAdminService().chartBookingPayment();
		
		request.setAttribute("memberCount", memberCount);
		request.setAttribute("roomCount", roomCount);
		request.setAttribute("bookingRoomCount", bookingRoomCount);
		request.setAttribute("requestCancelRoomCount", requestCancelRoomCount);
		
		request.setAttribute("roomList", roomList);
		request.setAttribute("chartBookingCount", chartBookingCount);
		request.setAttribute("chartBookingPayment", chartBookingPayment);
		
		request.getRequestDispatcher("/views/admin/admin-page.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}