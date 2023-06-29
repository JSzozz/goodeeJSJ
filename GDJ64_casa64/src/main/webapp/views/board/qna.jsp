<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.btc.notice.model.dto.Qna" %>
<%@ include file="/views/common/header.jsp"%>
<%
	List<Qna> Qnas = (List)request.getAttribute("Qnas");
	String searchType = request.getParameter("search-type");
	String keyword = request.getParameter("keyword");
	/* List<String> members = (List)request.getAttribute("memberName"); */
%> 

<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--검색창-->
	<div id="select-line">
		<form action="<%=request.getContextPath()%>/qna/filterQna.do" class="filterQna">
			<div class="select-rooms text-center mt-5 mb-5">
				<div>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-1"
						autocomplete="off" name="search-type" value="전체보기" checked> 
						<label class="btn btn-outline-primary me-2" for="btn-check-outlined-1">전체보기</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-2"
						autocomplete="off" name="search-type" value="회원가입/정보"> 
						<label class="btn btn-outline-primary me-2" for="btn-check-outlined-2">회원가입/정보</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-3"
						autocomplete="off" name="search-type" value="예약/결제"> 
						<label class="btn btn-outline-primary me-2" for="btn-check-outlined-3">예약/결제</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-4"
						autocomplete="off" name="search-type" value="취소/환불"> 
						<label class="btn btn-outline-primary me-2" for="btn-check-outlined-4">취소/환불</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-5"
						autocomplete="off" name="search-type" value="기타문의"> 
						<label class="btn btn-outline-primary me-2" for="btn-check-outlined-5">기타문의</label>
				</div>
		</form>
		<script>

		</script>

		<!--검색창-->
		<form action="<%=request.getContextPath()%>/qna/searchQna.do" method="get" id="search-form">
			<div id="select-line" class="float-end">
				<div id="select-line">
					<!--검색창-->
					<div class="float-end mt-5">
						<div class="search-area d-flex">
							<select name="search-type" id="checkSelect" class="ms-1" >
								<option value="QUESTION_TITLE" <%= (searchType != null && searchType.equals("QUESTION_TITLE")) ? "selected" : "" %>>제목</option>
								<option value="QUESTION_CONTENT" <%= (searchType != null && searchType.equals("QUESTION_CONTENT")) ? "selected" : "" %>>내용</option>
								<option value="MEMBER_NAME" <%= (searchType != null && searchType.equals("MEMBER_NAME")) ? "selected" : "" %>>작성자</option>
							</select> 
							<input type="text" name="keyword" class="ms-1" value="<%= (keyword != null ) ? keyword : "" %>" placeholder="검색어를 입력해 주세요.">
							<input type="hidden" name="categoryName" value='<%=request.getAttribute("categoryName") %>'>
							<input type="hidden" name="communityTitle" value='<%=request.getAttribute("communityTitle") %>'>
							<button type="submit" class="btn btn-primary btn-sm ms-1">검색</button>
						</div>
					</div>
				</div>  
			</div>
		</form>
		<!-- <div class="float-end mt-5">
			<div class="search-area d-flex">
				<select name="search-type" aria-label="">
					<option value="QUESTION_TITLE">제목</option>
					<option value="QUESTION_CONTENT">내용</option>
					<option value="MEMBER_NAME">작성자</option>
				</select> <input type="text" name="keyword" placeholder="검색어를 입력해 주세요"
					class="ms-1">
				<button type="button" class="btn btn-primary btn-sm ms-1">검색</button>
			</div>
		</div> -->
	</div>


	<!--테이블-->
	<div class="list-area">
		<table class="table table-hover text-center">
			<colgroup>
				<col width="40px" />
				<col width="90px" />
				<col width="250px" />
				<col width="50px" />
				<col width="70px" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>구분</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<%if(Qnas.isEmpty()||Qnas==null){ %>
				<tr>
					<td colspan="5">조회된 QnA가 없습니다.</td>
				</tr>
			<%} else { 
				for(Qna q : Qnas){%>
				<tr>
					<td><%=q.getQuestionNo()%></td>
					<td><%=q.getCategoryName()%></td>
					<td>
						<a href="<%=request.getContextPath()%>/qna/viewQna.do?no=<%=q.getQuestionNo()%>
						&categoryName=<%=request.getAttribute("categoryName")%>
						&communityTitle=<%=request.getAttribute("communityTitle")%>"><%=q.getQuestionTitle()%></a>
					</td>
					<td><%=q.getMemberName()%></td>					
					<td><%=q.getQuestionDate()%></td>
				</tr>
			<% }
			} %>
			</tbody>
		</table>
	</div>
	<div class="write-area text-end">
		<%if(loginMember!=null) {%>
			<a href="<%=request.getContextPath()%>/qna/qnaWrite.do"
			class="btn btn-primary btn-sm ms-1">작성하기</a>
		<%}%>
	</div>

	<div class="">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<%=request.getAttribute("pageBar") %>
			</ul>
		</nav>
	</div>
</div>

<!-- 내용 종료 -->

<%@ include file="/views/common/footer.jsp"%>