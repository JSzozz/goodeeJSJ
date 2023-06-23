package com.btc.booking.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.booking.model.vo.Booking;

/**
 * Servlet implementation class ReserveList1ToList2Servlet
 */
@WebServlet("/booking/bookingList1ToList2.do")
public class BookingList1ToList2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingList1ToList2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeNm = request.getParameter("typeNm");
		String checkinDt = request.getParameter("checkinDt");
		LocalDate date = LocalDate.parse(checkinDt);
		String checkoutDt = request.getParameter("checkoutDt");
		int adultPers = Integer.parseInt(request.getParameter("adultPers"));
		int kidsPers = Integer.parseInt(request.getParameter("kidsPers"));
		int infPers = Integer.parseInt(request.getParameter("infPers"));
		int totPrice = Integer.parseInt(request.getParameter("totPrice"));
		System.out.println(typeNm);
		System.out.println(checkinDt);
		System.out.println(checkoutDt);
		System.out.println(adultPers);
		System.out.println(kidsPers);
		System.out.println(infPers);
		System.out.println(totPrice);
		
		
		/*
		 * Booking part = Booking.builder().memberId(typeNm).
		 */		
		request.getRequestDispatcher("/views/booking/booking-list2.jsp").forward(request, response);
		
		
//		   private int bookingNo;
//		   private int memberId;
//		   private int roomNo;
//		   private String bookingCode;
//		   private Date checkIn;
//		   private Date checkOut;
//		   private int guestAdult;
//		   private int guestChild;
//		   private int guestInfant;
//		   private int xtraNo;
//		   private int bookingPrice;
//		   private String paid;
		/*
		 * MemberDTO m=
		 * MemberDTO.builder().userId(userId).password(password).userName(userName)
		 * .age(age).email(email).gender(gender.charAt(0)).phone(phone).address(address)
		 * .hobby(hobbies).build(); int result=new MemberService().insertMember(m);
		 * 
		 * String msg=""; String loc=""; if(result>0) { //회원가입 입력 실패
		 * msg="회원 가입을 축하드립니다."; loc="/";
		 * 
		 * 
		 * }else{ //회원가입 입력 실패(*msg.jsp) msg="회원 가입에 실패하였습니다. \n 다시 시도헤주세요.";
		 * loc="/member/enrollMember.do";
		 * 
		 * } request.setAttribute("loc", loc); request.setAttribute("msg", msg);
		 * request.getRequestDispatcher("/views/common/msg.jsp").forward(request,
		 * response);
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
