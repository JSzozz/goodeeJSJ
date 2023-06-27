package com.btc.admin.room.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.admin.model.service.AdminRoomService;
import com.btc.rooms.model.vo.Room;
import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class RoomInsertEndServlet
 */
@WebServlet("/admin/room/insertRoomEnd.do")
public class RoomInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* int roomNo=Integer.parseInt(request.getParameter("roomNo")); */
		String path = getServletContext().getRealPath("/upload/rooms/");// 실제 사진을 저장할 장소

		MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 500, "utf-8",
				new DefaultFileRenamePolicy());
		String roomName = mr.getParameter("roomName");//mr이야 request야
		int roomPrice = Integer.parseInt(mr.getParameter("roomPrice"));
		int roomSize = Integer.parseInt(mr.getParameter("roomSize"));
		int roomCap = Integer.parseInt(mr.getParameter("roomCap"));
		int roomMaxCap = Integer.parseInt(mr.getParameter("roomMaxCap"));
		char bookable = mr.getParameter("bookable").charAt(0);
		String roomDescription = mr.getParameter("roomDescription");
		String[] options=mr.getParameterValues("optionFree");
		Room r=Room.builder().roomName(roomName).roomPrice(roomPrice).roomSize(roomSize).roomCap(roomCap).roomMaxCap(roomMaxCap).bookable(bookable).roomDescription(roomDescription).build();

		Enumeration<String> files = mr.getFileNames();// 인풋에서 넣은 파일들의 이름
		List<String> filesName = new ArrayList();
		while (files.hasMoreElements()) {

			String fileName = files.nextElement();
			filesName.add(mr.getFilesystemName(fileName));

		}

		int result = new AdminRoomService().insertInquiry(r,filesName);
		System.out.println(result);

		response.setContentType("application/json;charset=utf-8");
		new Gson().toJson(result > 0 ? true : false, response.getWriter());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
