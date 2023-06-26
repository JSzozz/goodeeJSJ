package com.btc.admin.booking.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminBookingService;
import com.btc.booking.model.vo.Booking;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@WebServlet("/admin/booking/infoBooking.do")
public class InfoBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InfoBookingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingNo = Integer.parseInt(request.getParameter("bookingNo"));
		
		Booking bookingInfo = AdminBookingService.getBookingService().infoBooking(bookingNo);
		
		JsonSerializer<Date> json = new JsonSerializer<Date>() {
			
			@Override
			public JsonElement serialize(Date arg0, Type arg1, JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				return new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd").format(arg0));
			}
		};
		
		Gson gson = new Gson().newBuilder().registerTypeAdapter(Date.class,json).create();
		
		
		response.setContentType("application/json;charset=utf-8");
		gson.toJson(bookingInfo, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
