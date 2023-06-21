<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="java.util.List,com.web.board.model.vo.Notice" %> --%>
<%@ include file="/views/common/header.jsp"%>
<%
	/* List<Notice> notices = (List)request.getAttribute("list"); */
%> 
<%
	String title = request.getParameter("title");
	String name = request.getParameter("name");
%>
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--검색창-->
	<div id="select-line" class="float-end">
		<form>
			<div class="search-area d-flex">
				<select name="search-type" aria-label="">
					<option value="title">제목</option>
					<option value="contents">내용</option>
				</select> 
				<input type="text" name="keyword" placeholder="검색어를 입력해 주세요" class="ms-1">
				<button type="button" class="btn btn-primary btn-sm ms-1">검색</button>
			</div>
		</form>
	</div>
	<!--테이블-->
	<div class="list-area">
		<table class="table table-hover text-center">
			<colgroup>
			    <col width="50px"  />
			    <col width="200px" />
			    <col width="100px" />
			    <col width="100px" />
			    <col width="100px" />
			  </colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
			<%-- <%if(notices.isEmpty()){ %>
			<tr>
				<td colspan="5">조회된 공지사항이 없습니다.</td>
			<tr>
			<%} else { 
				for(Notice n : notices){%> --%>
				<tr>
					<td><%-- <%=n.getNo() %> --%>01</td>
					<td><%-- <a href="<%=request.getContextPath()%>/board/noticeView.do?no=<%=n.getNo() %>"><%=n.getTitle() %> --%>제목이들어감</a></td>
					<td>관리자</td>
					<td>1</td>
					<td><%-- <%=n.getDateCreated() %> --%>오늘</td>
				</tr>
		<%-- <%} 
				
		} %> --%>
			</tbody>
		</table>
	</div>

	<div class="board-pasing">
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