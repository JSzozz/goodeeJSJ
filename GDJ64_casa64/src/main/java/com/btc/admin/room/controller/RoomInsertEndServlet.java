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
        List<RoomImage> filesName = new ArrayList();
		while (files.hasMoreElements()) {

			String fileName = files.nextElement();
			if (mr.getFilesystemName(fileName) != null) {
				filesName.add(RoomImage.builder().saveFilename(mr.getFilesystemName(fileName))
						.oriFilename(mr.getOriginalFileName(fileName)).build());
				System.out.println(mr.getFilesystemName(fileName) + mr.getOriginalFileName(fileName));
			}
		}
        
        System.out.println("r :" + r);
        /* int result=new AdminRoomService().; */
        int roomNo=new AdminRoomService().insertRoom(r,filesName,options);
        System.out.println("roomNo :" + roomNo);
//        int result = new AdminRoomService().insertInquiry(r,filesName);
//        System.out.println(result);
  //
//        response.setContentType("application/json;charset=utf-8");
//        new Gson().toJson(result > 0 ? true : false, response.getWriter());
        String msg="",loc="";
        if(roomNo!=0) {
           msg="객실 등록이 성공적으로 완료되었습니다.";
           loc="/admin/room/showAllRoom.do";
        }else {
           msg="객실 등록에 실패했습니다. 객실관리 화면으로 돌아갑니다.";
           loc="/admin/room/showAllRoom.do";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("loc", loc);
        request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);
        

     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
