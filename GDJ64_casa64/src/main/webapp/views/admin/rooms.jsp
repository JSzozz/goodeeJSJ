<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
<%@ page import="java.util.List,com.btc.rooms.model.vo.Room"%>
<%
	List<Room> rooms=(List<Room>)request.getAttribute("rooms");
	String keyword=request.getParameter("keyword");

%>
<!-- 컨테츠 -->
<section>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
				<div class="row pt-md-5 mt-md-3 mb-4">
					<h2 class="text-center">객실관리</h2>
					<div id="select-line" class="float-end">
						<div class="search-area d-flex justify-content-end mt-2">
							<form
								action="<%=request.getContextPath()%>/admin/room/searchRoomByKeyword.do">
							<select name="notice-search">
								<option value="roomName">객실명</option>
							</select> <input type="text" name="keyword" placeholder="검색어를 입력해 주세요"
								class="ms-1" value="">
							<button type="submit" class="btn btn-primary btn-sm ms-1">검색</button>
							<!-- onclick에 /admin/room/searchRoomByKeyword.do로 keyword와 함께 넘겨. 이름은 SearchRoomByKeywordServlet-->
							</form>
						</div>
					</div>
				</div>
				<table class="table text-center">
					<thead>
						<th>No</th>
					<!-- 	<th>객실대표사진</th> -->
						<th>객실명</th>
						<th>공개여부</th>
						<th>가격</th>
						<th>상세조회</th>
					</thead>
					<tbody class="align-middle">
						<%
						if (rooms == null) {
						%>
						<tr>
							<td colspan="6">등록된 객실이 존재하지 않습니다. 추가 버튼을 눌러 객실을 등록해주세요.</td>
						</tr>
						<%
						} else {
						for (Room r : rooms) {
						%>

						<tr>
							<td><%=r.getRoomNo()%></td>
							<!-- <td><img src="#" alt="객실사진" width="150" height="100">
							</td> -->
							<td><%=r.getRoomName()%></td>
							<td><%=r.getBookable() == 'Y' ? "공개" : "비공개"%></td>
							<td><%=r.getRoomPrice()%></td>
							<td>
								<button type="submit" class="btn btn-dark btn-sm"
									onclick="location.assign('<%=request.getContextPath()%>/admin/room/roomDetail.do?roomNo=<%=r.getRoomNo()%>')">상세조회</button>
							</td>
						</tr>
						<%
						}
						}
						%>
					</tbody>
				</table>
				<!-- 관리자 버튼 -->
				<div class="d-flex justify-content-end">
					<button type="submit" class="btn btn-dark"
						onclick="location.assign('<%=request.getContextPath()%>/admin/room/insertRoom.do')">객실추가</button>
				</div>
				<!-- 페이지바 -->
				<div id="pageBar" class="pagination justify-content-center">
					<%=request.getAttribute("pageBar")%>
				</div>
			</div>
		</div>
	</div>
</section>
<style>
#pageBar a, #pageBar span {
	text-decoration: none;
	font-size: 1rem;
	margin-left: 1%;
	margin-right: 1%;
}
</style>

<%@ include file="/views/admin/common/footer.jsp"%>