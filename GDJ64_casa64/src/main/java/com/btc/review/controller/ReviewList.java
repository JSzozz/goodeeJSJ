package com.btc.review.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.btc.mypage.model.vo.Paginator;
import com.btc.review.model.service.ReviewService;
import com.btc.review.model.vo.Review;
import com.btc.rooms.model.vo.Room;

/**
 * Servlet implementation class ReviewList
 */
@WebServlet("/review/reviewList")
public class ReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Integer POSTS_PER_PAGE = 10; // 한 페이지에서 보여지는 게시글 개수
    private static final Integer PAGES_PER_BLOCK = 5; // 페이지바에서 보여줄 페이지 개수

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
		
		String type = (request.getParameter("search-type") != null ) ? request.getParameter("search-type") : "";
		String keyword = (request.getParameter("keyword")!= null ) ? request.getParameter("keyword") : "";
		String roomNo = (request.getParameter("room-no") != null ) ? request.getParameter("room-no") : "";
		List<Room> rooms = new ReviewService().selectAllRoom();
		
        // 총 게시글 수
        int totalPostCount = new ReviewService().selectReviewsTotalCount(type, keyword, roomNo); // 검색어 포함된 총 게시글 수
        
        int page;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(NumberFormatException e) {
			page = 1;
		}
		
		String pageBar = "<div class='page-area'><nav aria-label=\"Page navigation example\"><ul class=\"pagination justify-content-center\">";
		
		int totalPage = (int)Math.ceil((double)totalPostCount/POSTS_PER_PAGE);
		int pageNo = ((page-1)/PAGES_PER_BLOCK) * PAGES_PER_BLOCK+1;
		int pageEnd = pageNo + PAGES_PER_BLOCK-1;
		int pageStartRowNum = totalPostCount - (page-1)*POSTS_PER_PAGE;

		if(page > 1) { // 현재 페이지가 1보다 클 경우 이전 버튼
			pageBar += "<li class=\"page-item\">"
					+  "<a class='page-link' href='"+request.getRequestURI()
					+ "?search-type="+type
					+ "&keyword="+keyword
					+ "&room-no="+roomNo
					+ "&page="+(page-1)
					+ "'><span aria-hidden='true'>&lt;</span></a></li>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			String active = "";
			if(pageNo == page) {
				active = "active";
			}
			pageBar += "<li class='page-item "+active+"'>";
			pageBar += "<a class=\"page-link\" href='"+request.getRequestURI()
					+"?search-type="+type
					+"&keyword="+keyword
					+"&room-no="+roomNo
					+"&page="+pageNo
					+"'>"+pageNo+"</a></li>";
			pageNo++;
		}
		if(page < totalPage) { // 현재 페이지가 마지막 페이지가 아닐 경우 다음 버튼
			pageBar += "<li class=\"page-item\"><a class=\"page-link \" href='"+request.getRequestURI()
			+"?search-type="+type
			+"&keyword="+keyword
			+"&room-no="+roomNo
			+"&page="+(page+1)
			+"'><span aria-hidden=\"true\">&gt;</span></a></li>";
		}
		pageBar+= "</ul></nav></div>";
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("pageStartRowNum", pageStartRowNum);

        List<Review> list = new ReviewService().selectReviews(type, keyword, roomNo, page, POSTS_PER_PAGE); // Review 클래스에 있는 값만 담길 list를 생성
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
