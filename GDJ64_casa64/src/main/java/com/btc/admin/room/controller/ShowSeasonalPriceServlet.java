package com.btc.admin.room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;
import com.btc.booking.model.vo.SeasonalPrice;

@WebServlet("/allSeason.do")
public class ShowSeasonalPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowSeasonalPriceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	      List<SeasonalPrice> prices=new AdminRoomService().allSeason();
//	      request.setAttribute("prices", prices);
	      request.getRequestDispatcher("/views/admin/season-price.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
