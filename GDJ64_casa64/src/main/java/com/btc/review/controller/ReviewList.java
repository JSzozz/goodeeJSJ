
package com.btc.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Reviews;
import com.btc.rooms.model.service.RoomService;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class ReviewList
 */
@WebServlet("/board/reviewList.do")
public class ReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type = request.getParameter("search-type");
		String keyword = request.getParameter("keyword");
		String roomNo = request.getParameter("room-no");

		
		List<Reviews> list = new ReviewService().selectReviews(type, keyword, roomNo); // Review 클래스에 있는 값만 담길 list를 생성
		List<Room> rooms = new RoomService().selectAllRoom();
		request.setAttribute("ReviewList", list);
		request.setAttribute("selectRooms", rooms);
		
		request.setAttribute("categoryName", "COMMUNITY");
		request.setAttribute("communityTitle", "이용후기");
		
		request.getRequestDispatcher("/views/review/review.jsp").forward(request, response);
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
=======
package com.btc.review.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Review;
import com.btc.rooms.model.service.RoomService;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class ReviewList
 */
@WebServlet("/board/reviewList.do")
public class ReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type = request.getParameter("search-type");
		String keyword = request.getParameter("keyword");
		String roomNo = request.getParameter("room-no");

		
		List<Review> list = new ReviewService().selectReviews(type, keyword, roomNo); // Review 클래스에 있는 값만 담길 list를 생성
		List<Room> rooms = new RoomService().selectAllRoom();
		request.setAttribute("ReviewList", list);
		request.setAttribute("selectRooms", rooms);
		
		request.setAttribute("categoryName", "COMMUNITY");
		request.setAttribute("communityTitle", "이용후기");
		
		request.getRequestDispatcher("/views/review/review.jsp").forward(request, response);
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

