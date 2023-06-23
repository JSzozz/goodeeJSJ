<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.btc.review.model.vo.Review" %>
<%@ page import="java.util.List,com.btc.rooms.model.vo.Room" %>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/nara_publish/css/common.css" />
<%
	List<Review> reviews = (List)request.getAttribute("ReviewList");
	List<Room> roomsList = (List)request.getAttribute("selectRooms");
%> 
	
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>
	<form action="" method="get" id="search-form">
		<div id="select-line">
			<!--검색창-->
			<div class="float-end">
				<div class="search-area d-flex">
					<% 
						String searchType = request.getParameter("search-type");
						String roomNo = request.getParameter("room-no");
						String keyword = request.getParameter("keyword");
					%>
					<select id="search-type" name="search-type" class="ms-1" onchange="searchType(this.value);">
						<option value="title" <%= (searchType != null && searchType.equals("title")) ? "selected" : "" %>>제목</option>
						<option value="contents" <%= (searchType != null && searchType.equals("contents")) ? "selected" : "" %>>내용</option>
						<option value="writer" <%= (searchType != null && searchType.equals("writer")) ? "selected" : "" %>>작성자</option>
						<option value="rooms" <%= (searchType != null && searchType.equals("rooms")) ? "selected" : "" %>>객실명</option>
					</select> 
					<input type="text" id="keyword" name="keyword" placeholder="검색어를 입력해 주세요" class="ms-1" 
						value="<%= (keyword != null ) ? keyword : "" %>">
					<select id="room-no" name="room-no" style="display:none" class="ms-1" onChange="autoSubmit()">
						<option value="">전체</option>
					<%
						if(roomsList != null && !roomsList.isEmpty()) {
								for(Room rm : roomsList){%>
									<option value="<%= rm.getRoomNo() %>" <%= ( roomNo != null && roomNo.equals(Integer.toString(rm.getRoomNo()))) ? "selected" : "" %>><%= rm.getRoomName() %></option>
						<%}}%>
						
					</select>
					<button type="button" class="btn btn-primary btn-sm ms-1" onClick="checkSubmit()">검색</button>
				</div>
			</div>
		</div>
	</form>
	<!--테이블-->
	<div class="list-area">
		<table class="table table-hover text-center">
			<colgroup>
				<col width="40px" />
				<col width="100px" />
				<col width="200px" />
				<col width="50px" />
				<col width="50px" />
				<col width="70px" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>이용객실</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
			<% for(Review r : reviews){%>
				<tr>
					<td><%= r.getNo() %></td>
					<td><%= r.getRoomName() %></td>
					<td>
						<a href="<%=request.getContextPath()%>/review/reviewView?no=<%= r.getNo() %>">
						<%= r.getTitle() %>
						</a>
					</td>
					<td><%= r.getNickName() %></td>
					<td><%= r.getViews() %></td>
					<td><%= r.getDateCreated() %></td>
				</tr>
				<% }%>
			</tbody>
		</table>
	</div>
	<div class="write-area text-end">
		<a href="<%=request.getContextPath()%>/review/reviewWrite"
			class="btn btn-primary btn-sm ms-1">작성하기</a>
	</div>

	<div class="">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li class="page-item active"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item"><a class="page-link " href="#"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
</div>
<!-- 내용 종료 -->
<%@ include file="/views/common/footer.jsp"%>
<script>
	$(function(){ // ready function => 페이지 로드 시 실행하는 함수
		searchType('<%= request.getParameter("search-type")%>'); // 검색 타입이 객실명이면 객실리트가 보여지도록
	});
	function searchType(value){
		if(value=="rooms"){
			$("#room-no").show();
			$("#keyword").hide();
			$("#keyword").val('');
		}else{
			$("#room-no").hide();
			$("#room-no").val('')
			$("#keyword").show();
		}
	}
	// 자동 form 검색
	function autoSubmit(){
		$('#search-form').submit();
	}
	function checkSubmit(){
		if($('#search-type').val() != 'rooms') {
			if($('#keyword').val().length < 1){
				alert('검색어를 입력해 주세요!');
				$('#keyword').focus();
				return;
			}
		}
		$('#search-form').submit();
		
	}
</script>