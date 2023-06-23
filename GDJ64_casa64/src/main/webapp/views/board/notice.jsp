<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.btc.notice.model.dto.Notice" %>
<%@ include file="/views/common/header.jsp"%>
<%
	List<Notice> notices = (List)request.getAttribute("notices");
%> 
<%-- <%
	String title = request.getParameter("title");
	String name = request.getParameter("name");
%> --%>
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
<%@ include file="/views/board/board_tab.jsp"%>
<%
	
%>
	<!--검색창-->
	<form action="<%=request.getContextPath()%>/notice/searchNotice.do" method="get" id="search-form">
		<div id="select-line" class="float-end">
			<div id="select-line">
				<!--검색창-->
				<div class="float-end">
					<div class="search-area d-flex">
						<% 
							String searchType = request.getParameter("search-type");
							String keyword = request.getParameter("keyword");
						%>
						<select name="search-type" class="ms-1" >
							<option value="NOTICE_TITLE" <%= (searchType != null && searchType.equals("title")) ? "selected" : "" %>>제목</option>
							<option value="NOTICE_CONTENT" <%= (searchType != null && searchType.equals("contents")) ? "selected" : "" %>>내용</option>
						</select> 
						<input type="text" name="keyword" placeholder='<%= (searchType != null && searchType.equals("title")) ? "검색어를 입력해 주세요." : "키워드를 입력해 주세요." %>' class="ms-1" 
							value="<%= (keyword != null ) ? keyword : "" %>">
						<input type="hidden" name="categoryName" value='<%=request.getAttribute("categoryName") %>'>
						<input type="hidden" name="communityTitle" value='<%=request.getAttribute("communityTitle") %>'>
						<button type="submit" class="btn btn-primary btn-sm ms-1">검색</button>
					</div>
				</div>
			</div>
		</div>
	</form>
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
			<%if(notices.isEmpty()){ %>
				<tr>
					<td colspan="5">조회된 공지사항이 없습니다.</td>
				</tr>
			<%} else { 
				for(Notice n : notices){%>
				<tr>
					<td><%=n.getNoticeNo()%></td>
					<td><a href="<%=request.getContextPath()%>/notice/viewNotice.do?no=<%=n.getNoticeNo()%>&categoryName=<%=request.getAttribute("categoryName")%>&communityTitle=<%=request.getAttribute("communityTitle")%>"><%=n.getNoticeTitle() %></a></td>
					<td>관리자</td>
					<td><%=n.getNoticeReadCount()%></td>
					<td><%=n.getDateCreated()%></td>
				</tr>
			<% }		
			} %>
			</tbody>
		</table>
	</div>

	<div class="board-pasing">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<%=request.getAttribute("pageBar") %>
			</ul>
		</nav>
	</div>
</div>
<!-- 내용 종료 -->
<%@ include file="/views/common/footer.jsp"%>