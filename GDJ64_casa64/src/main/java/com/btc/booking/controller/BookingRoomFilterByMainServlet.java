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
import com.btc.booking.model.vo.Booking;
import com.btc.booking.model.vo.OptionXtra;
import com.btc.booking.model.vo.SeasonalPrice;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class BookingRoomFilterServlet
 */
@WebServlet("/booking/roomFilterByMainServlet.do")
public class BookingRoomFilterByMainServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingRoomFilterByMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	BookingService service = new BookingService();
    String[] options=request.getParameterValues("options");//#oo#oo#oo
//    System.out.println("options : "+Arrays.toString(options));
    String optionList="";
    for(int i=0;i<options.length;i++) {
//    	System.out.println(options[i]);
    	optionList = options[i].substring(1, options[i].length());//oo#oo#oo
    }
    List<String> list = Arrays.asList(optionList.split("#"));//{oo,oo,oo}
    
    String OptionNumbers="";
    for(int i=0;i<list.size();i++) {
//    	System.out.println(list.get(i).getClass().getName());//string
//    	System.out.println(list.get(i));
		int roomNo = service.searchOptNo(list.get(i));// FREE_NAME->FREE_NO
		OptionNumbers+=roomNo+",";
//		System.out.println(OptionNumbers);
    }
    OptionNumbers = OptionNumbers.substring(0, OptionNumbers.length() - 1);

    List<Room> filterRooms= new BookingService().selectFilteringRoom(OptionNumbers);
    List<Room> rooms=new ArrayList();
//  System.out.println("gd"+filterRooms.get(0).getRoomNo());
    for(int i=0;i<filterRooms.size();i++) {
//     System.out.println(filterRooms.get(i).getRoomNo());
    	int $roomNo=filterRooms.get(i).getRoomNo();//룸번호=get(i)-1 
//     System.out.println(roomNo);
    	Room $room= new BookingService().selectRoomByRoomNo($roomNo);
    	rooms.add($room);
    }
//  System.out.println(rooms);
  
    HttpSession session=request.getSession();
    session.setAttribute("rooms", rooms);
    
	List<Booking> bookings=new BookingService().selectAllBooking();
//	request.setAttribute("bookings", bookings);
	session.setAttribute("bookings", bookings);


	List<SeasonalPrice> seasons=new BookingService().selectAllSeason();
//	request.setAttribute("seasons", seasons);
	session.setAttribute("seasons", seasons);
	
	List<OptionXtra> xtraOptions= new BookingService().selectAllOption();
//	request.setAttribute("xtraOptions", xtraOptions);
	session.setAttribute("xtraOptions", xtraOptions);
    
    request.getRequestDispatcher("/views/booking/booking-list1.jsp")
    .forward(request, response);
    
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}