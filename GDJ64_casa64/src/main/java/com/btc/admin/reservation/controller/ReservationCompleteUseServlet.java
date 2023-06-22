package com.btc.admin.reservation.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/completeUseRoom.do")
public class ReservationCompleteUseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReservationCompleteUseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오늘 날짜 데이터 저장하기
		LocalDate now = LocalDate.now();
		
		response.getWriter().print("하이하이");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
