package com.btc.booking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btc.booking.model.service.BookingService;
import com.btc.booking.model.vo.Booking;
import com.btc.member.model.dto.Member;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class ReserveList1ToList2Servlet
 */
@WebServlet("/booking/bookingList3ToList4.do")
public class BookingList3ToList4Servlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public BookingList3ToList4Servlet() {

      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      int memberNo = Integer.parseInt(request.getParameter("memberNo"));
      int roomNo = Integer.parseInt(request.getParameter("roomNo"));
      java.sql.Date checkIn = java.sql.Date.valueOf(request.getParameter("checkin"));
      java.sql.Date checkOut = java.sql.Date.valueOf(request.getParameter("checkout"));
      int guestAdult = Integer.parseInt(request.getParameter("guestAdult"));
      int guestChild = Integer.parseInt(request.getParameter("guestChild"));
      int guestInfant = Integer.parseInt(request.getParameter("guestInfant"));
      int bookingPrice = Integer.parseInt(request.getParameter("bookingPrice"));
      String bookingComment = request.getParameter("bookingComment");
      
      Booking b = Booking.builder().member(Member.builder().memberNo(memberNo).build()).room(Room.builder().roomNo(roomNo).build()).checkIn(checkIn).checkOut(checkOut).guestAdult(guestAdult)
            .guestChild(guestChild).guestInfant(guestInfant).bookingPrice(bookingPrice).bookingComment(bookingComment).build();
      HttpSession session=request.getSession();
      session.setAttribute("booking",b);      

      int result = new BookingService().insertBooking(b);
      
      Booking recentBooking = new BookingService().searchBookingByMemberNo(memberNo);
      session.setAttribute("recentBooking",recentBooking);      

      
      request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}