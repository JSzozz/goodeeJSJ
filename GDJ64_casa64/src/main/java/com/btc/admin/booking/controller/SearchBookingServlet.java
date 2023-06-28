package com.btc.admin.booking.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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

@WebServlet("/admin/booking/searchBooking.do")
public class SearchBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchBookingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage, numPerPage;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage = 1;
		}
		
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerPage = 10;
		}
		
		StringBuffer pageBar = new StringBuffer();
		int totalData = AdminBookingService.getBookingService().bookingCount();
		int totalPage = (int)Math.ceil((double)totalData/numPerPage);
		int pageBarSize = 5;
		int startPage = ((cPage-1)/pageBarSize) * pageBarSize + 1;
		int endPage = startPage + pageBarSize - 1;
		
		if(startPage == 1) {
			pageBar.append("<li class='page-item'><a class='page-link' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
		}else {
			pageBar.append("<li class='page-item'>"
					+ "<a class='page-link' aria-label='Previous' href='javascript:void(0);'" 
					+ "onclick=\"ajaxBooking('" + request.getRequestURI() + "','','',''," + (startPage - 1) + "," + numPerPage + ");\">" + numPerPage + "<span aria-hidden='true'>&laquo;</span></a></li>");
		}
		
		while(!(startPage > endPage || startPage > totalPage)) {
			if(startPage == cPage) {
				pageBar.append("<li class='page-item active'><a class='page-link'>" + startPage + "</a></li>");
			}else {
				pageBar.append("<li class='page-item'>"
						+ "<a class='page-link' href='javascript:void(0);'"
						+ "onclick=\"ajaxBooking('" + request.getRequestURI() + "','','',''," + startPage + "," + numPerPage + ");\">" +startPage + "</a></li>");
			} startPage++;
		}
		
		if(startPage > totalPage) {
			pageBar.append("<li class='page-item'><a class='page-link' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");
		}else {
			pageBar.append("<li class='page-item'>"
					+ "<a class='page-link' aria-label='Next' href='javascript:void(0);'"
					+ "onclick=\"ajaxBooking('" + request.getRequestURI() + "','','',''," + startPage + ","+ numPerPage + ");\"><span aria-hidden='true'>&raquo;</span></a></li>");
		}
		
		String state = request.getParameter("state");
		String type = request.getParameter("type");
		String value = request.getParameter("value");
						
		List<Booking> bookingList = AdminBookingService.getBookingService().searchBookingList(state, type, value, cPage, numPerPage);
		
		Map<String, Object> responseData = Map.of("bookingList", bookingList);
		
		JsonSerializer<Date> json = new JsonSerializer<Date>() {
			
			@Override
			public JsonElement serialize(Date arg0, Type arg1, JsonSerializationContext arg2) {
				// TODO Auto-generated method stub
				return new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd").format(arg0));
			}
		};
		
		Gson gson = new Gson().newBuilder().registerTypeAdapter(Date.class,json).create();
		
		
		response.setContentType("application/json;charset=utf-8");
		gson.toJson(responseData, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
