package com.btc.booking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.booking.model.service.BookingService;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class BookingRoomFilterServlet
 */
@WebServlet("/booking/roomFilterServlet.do")
public class BookingRoomFilterServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingRoomFilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String[] options=request.getParameterValues("option");
//      System.out.println("options : "+Arrays.toString(options));
      List<String> optionList = new ArrayList();
      
      // for문을 이용한 split -> list 변환
      for(int i=0; i<options.length; i++){
         optionList.add(options[i]);
//         System.out.println(options[i]);
      }
      
      List<Room> filterRooms= new BookingService().selectFilteringRoom(optionList);
      List<Room> oriRooms=new BookingService().selectAllRoom();
      List<Room> rooms=new ArrayList();
      System.out.println("gd"+filterRooms.get(0).getRoomNo());
      for(int i=0;i<filterRooms.size();i++) {
         System.out.println(filterRooms.get(i).getRoomNo());
         int roomNo=filterRooms.get(i).getRoomNo()-1;//룸번호=get(i)-1 
         rooms.add(oriRooms.get(roomNo));
      }
      System.out.println(rooms);
      HttpSession session=request.getSession();
      session.setAttribute("rooms", rooms);
      
      request.getRequestDispatcher("/views/booking/booking-list1.jsp")
      .forward(request, response);
      
      
      // Array.asList를 이용한 변환 ( 위에 방법보다 빠름 )
      // List<String> list = Arrays.asList(str.split(","));
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}