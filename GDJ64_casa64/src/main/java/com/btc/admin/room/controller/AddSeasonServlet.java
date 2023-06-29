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
		String season = request.getParameter("seasonName");
		String seasonStart = request.getParameter("seasonStart");
		String seasonEnd = request.getParameter("seasonEnd");
		Double weekdayPrice = Double.parseDouble(request.getParameter("weekdayPrice"));
		Double weekEndPrice = Double.parseDouble(request.getParameter("weekEndPrice"));
		
		int result = new AdminRoomService().insertSeason(season, weekdayPrice, weekEndPrice, seasonStart, seasonEnd);
		
		String msg="",loc ="";
		if(result == 0) {
			msg="추가 실패!";
			loc="/allSeason.do";
		}else {
			msg="추가 성공!";
			loc="/allSeason.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
