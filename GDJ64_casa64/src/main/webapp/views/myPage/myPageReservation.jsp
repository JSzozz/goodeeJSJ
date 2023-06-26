<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.List,com.btc.review.model.vo.Review" %>
<%@ page import="java.util.List,com.btc.mypage.model.vo.Booking" %>
<%@ page import="java.util.List,com.btc.rooms.model.vo.Room" %>
<%
	List<Booking> bookings = (List)request.getAttribute("MyPageBookingList");
%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>예약내역</h2>
		</div>
	</div>
	<div class="row text-dark min-vh-100">
		<div class="mt-5 col-2 border-end">
			<%@ include file="/views/common/asideNav.jsp"%>
		</div>

		<div class="mt-5 col-9 mx-auto">

			<div class="tb">
				<table class="table table-hover text-center align-middle">
					<colgroup>
						<col width="10%">
						<col width="20%">
						<col width="15%">
						<col width="25%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>객실명</th>
							<th>예약상태</th>
							<th>이용날짜</th>
							<th>비고</th>
							<th>결제일</th>
						</tr>
					</thead>
					<tbody>
                     
                        <% if(bookings != null && !bookings.isEmpty()) {
                        	int count = bookings.size();
                  			for(Booking b : bookings){%>
						<tr>
							<td><%= count %></td>
							<td><%=b.getRoomName() %></td>
							<td><%=b.getBookingState() %></td>
							<td><%=b.getCheckIn() %> ~ <%=b.getCheckOut() %></td>
							<td>
							<% if(b.getBookingState().equals("예약완료")) { %>
								<button type="button" class="btn btn-primary btn-sm">결제취소</button>
							<% } else if(b.getBookingState().equals("이용완료")) { 
								if(b.getReviewNo() > 0 ){ %>
								<a href="<%=request.getContextPath() %>/review/reviewWrite?no=<%=b.getReviewNo() %>" class="btn btn-primary btn-sm">리뷰수정</a>
							<% } else { %>
							 	<a href="<%=request.getContextPath() %>/review/reviewWrite" class="btn btn-primary btn-sm">리뷰작성</a>
							<%}} %>
							</td>
							<td><%=b.getPaymentDate() %></td>
						</tr>
						<%
							count--;
							} 
                  		} else { %>
						<tr>
							<td colspan="6">예약내역이 없습니다.</td>
						</tr>
						<% } %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<%@ include file="/views/common/footer.jsp"%>