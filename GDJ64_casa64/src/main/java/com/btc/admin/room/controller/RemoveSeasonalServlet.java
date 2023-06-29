package com.btc.admin.room.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;

@WebServlet("/removeSeason.do")
public class RemoveSeasonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveSeasonalServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("removeName");
		
		int result = new AdminRoomService().removeSeasonal(name);
		String msg="",loc ="";
		if(result == 0) {
			msg="삭제 실패..";
			loc="/allSeason.do";
		}else {
			msg="삭제 성공!!";
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
