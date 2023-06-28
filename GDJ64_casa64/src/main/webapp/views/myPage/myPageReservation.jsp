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
			<h2>나의 예약내역</h2>
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
						<col width="25%">
						<col width="15%">
						<col width="25%">
						<col width="15%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>객실명</th>
							<th>예약상태</th>
							<th>이용날짜</th>
							<th>결제일</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
                     
                        <% if(bookings != null && !bookings.isEmpty()) {
                        	int count = 1;
                  			for(Booking b : bookings){%>
						<tr>
							<td><%= count %></td>
							<td><%=b.getRoomName() %></td>
							<td><%=b.getBookingState() %></td>
							<td><%=b.getCheckIn() %> ~ <%=b.getCheckOut() %></td>
							<td><%=b.getPaymentDate() %></td>
							<td>
							<% if(b.getBookingState().equals("결제완료")) { %>
								<button type="button" class="btn btn-primary btn-sm" onclick="cancellation(<%=b.getBookingNo() %>)">결제취소</button>
							<% } else if(b.getBookingState().equals("이용완료")) { 
								 if(b.getReviewNo() > 0 ){ %>
									<a href="<%=request.getContextPath() %>/review/reviewWrite?no=<%=b.getReviewNo() %>" class="btn btn-primary btn-sm">후기수정</a>
							<% 	} else { %>
							 		<a href="<%=request.getContextPath() %>/review/reviewWrite" class="btn btn-primary btn-sm">후기작성</a>
							<%   }
							  } 
							%>
							</td>
						</tr>
						<%
							count++;
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
<script>
	function cancellation(bookingNo) {
		if(bookingNo > 0 && confirm('취소하시겠습니까?')){
			$.ajax({
			    type : 'post',           // 타입 (get, post, put 등등)
			    url : "<%=request.getContextPath()%>/myPage/cancellation",           
			    dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
			    data : {  // 보낼 데이터 (Object , String, Array)
			      "bookingNo" : bookingNo,
			    },
			    success : function(result) { // 결과 성공 콜백함수
			    	if(result > 0) {
			    		alert('결제취소 요청되었습니다.');
			    		window.location.reload();
			    	} else {
			    		alert('취소실패! 관리자에게 문의바랍니다.');
			    	}
			    },
			    error : function(request, status, error) { // 결과 에러 콜백함수
			        console.log(error)
			    }
			})
		}
	}
</script>
<%@ include file="/views/common/footer.jsp"%>