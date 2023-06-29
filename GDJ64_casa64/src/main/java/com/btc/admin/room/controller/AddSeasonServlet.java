package com.btc.admin.room.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;

@WebServlet("/addSeason.do")
public class AddSeasonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddSeasonServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("seasonName");
		double weekdayPrice = Double.parseDouble(request.getParameter("weekdayPrice"));
		double weekendPrice = Double.parseDouble(request.getParameter("weekendPrice"));
		String seasonStartStr = request.getParameter("seasonStart");
		String seasonEndStr = request.getParameter("seasonEnd");
		
		SimpleDateFormat seasonSimple = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date seasonStart = null;
		Date seasonEnd = null;
		try {
			seasonEnd = (Date) seasonSimple.parse(seasonEndStr);
			seasonStart = (Date) seasonSimple.parse(seasonStartStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(seasonStart);
		System.out.println(seasonEnd); 
		int result = new AdminRoomService().insertSeason(name, weekdayPrice, weekendPrice, seasonStart, seasonEnd);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
