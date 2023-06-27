package com.btc.admin.room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;
import com.btc.rooms.model.vo.OptionFree;
import com.btc.rooms.model.vo.OptionXtra;

/**
 * Servlet implementation class ShowAllOptionServlet
 */
@WebServlet("/admin/room/showAllOption.do")
public class ShowAllOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllOptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OptionFree> frees=new AdminRoomService().selectAllFree();
		List<OptionXtra> xtras=new AdminRoomService().selectAllXtra();
		request.setAttribute("frees", frees);
		request.setAttribute("xtras", xtras);
		request.getRequestDispatcher("/views/admin/option-manage.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
