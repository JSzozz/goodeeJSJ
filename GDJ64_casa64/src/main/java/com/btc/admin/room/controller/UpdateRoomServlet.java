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
import com.btc.rooms.model.vo.RoomImage;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UpdateRoomServlet
 */
@WebServlet("/admin/room/updateRoom.do")
public class UpdateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/upload/rooms/");
		MultipartRequest mr=new MultipartRequest(request, path, 1024*1024*500,"utf-8",new DefaultFileRenamePolicy());
		int roomNo=Integer.parseInt(mr.getParameter("roomNo"));
		String roomName=mr.getParameter("roomName");
		int roomPrice=Integer.parseInt(mr.getParameter("roomPrice"));
		int roomSize=Integer.parseInt(mr.getParameter("roomSize"));
		int roomCap=Integer.parseInt(mr.getParameter("roomCap"));
		int roomMaxCap=Integer.parseInt(mr.getParameter("roomMaxCap"));
		char bookable=mr.getParameter("bookable").charAt(0);
		String roomDescription=mr.getParameter("roomDescription");
		String[] frees=mr.getParameterValues("optionFree");
		
		Room r=Room.builder().roomNo(roomNo).roomName(roomName).roomPrice(roomPrice).roomSize(roomSize).roomCap(roomCap).roomMaxCap(roomMaxCap).bookable(bookable).roomDescription(roomDescription).build();
		Enumeration<String> files=mr.getFileNames();
		List<RoomImage> filesName=new ArrayList();
		while(files.hasMoreElements()) {
			String fileName=files.nextElement();
			filesName.add(
					RoomImage.builder()
					.roomNo(roomNo)
					.saveFilename(mr.getFilesystemName(fileName))
					.oriFilename(mr.getOriginalFileName(fileName))
					.build());
		}
		int result=new AdminRoomService().updateInquiry(r,filesName,frees);
//		int deleteRO=new AdminRoomService().deleteOldOption(roomNo);
//		int num=new AdminRoomService().updateRoomOption(roomNo,frees);
		
		response.setContentType("application/json;charset=utf-8");
//		new Gson().toJson(result>0?true:false,response.getWriter());
		
		//객실정보 수정
//		Room r=Room.builder().roomNo(Integer.parseInt(request.getParameter("roomNo"))).roomName(request.getParameter("roomName")).roomSize(Integer.parseInt(request.getParameter("roomSize"))).roomPrice(Integer.parseInt(request.getParameter("roomPrice")))
//				.bookable(request.getParameter("bookable").charAt(0)).roomCap(Integer.parseInt(request.getParameter("roomCap"))).roomMaxCap(Integer.parseInt(request.getParameter("roomMaxCap"))).roomDescription(request.getParameter("roomMDescription")).build();
//		//roomImage임의로 뺴둔 상태
//		int result=new AdminRoomService().updateRoom(r);
		String msg="",loc="";
		if(result>0) {
			msg="객실정보가 수정되었습니다";
			loc="/admin/room/roomDetail.do?roomNo="+r.getRoomNo();
		}else {
			msg="객실정보 등록에 실패했습니다. 다시 입력해주세요.";
			loc="/admin/room/roomDetail.do?roomNo="+r.getRoomNo();
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
