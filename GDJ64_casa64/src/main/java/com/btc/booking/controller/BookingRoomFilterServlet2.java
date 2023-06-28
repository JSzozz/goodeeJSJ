package com.btc.booking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
@WebServlet("/booking/roomFilterServlet2.do")
public class BookingRoomFilterServlet2 extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingRoomFilterServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String[] options=request.getParameterValues("options");
    System.out.println("options : "+Arrays.toString(options));
    String optionList="";
    for(int i=0;i<options.length;i++) {
    	System.out.println(options[i]);
    	optionList = options[i].substring(1, options[i].length());
    	System.out.println(optionList);
    }
    
    
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}