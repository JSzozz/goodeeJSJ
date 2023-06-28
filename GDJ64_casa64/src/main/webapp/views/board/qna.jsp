<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.btc.notice.model.dto.Qna" %>
<%@ include file="/views/common/header.jsp"%>
<%
	List<Qna> Qnas = (List)request.getAttribute("Qnas");
	String searchType = request.getParameter("search-type");
	String keyword = request.getParameter("keyword");
	List<String> members = (List)request.getAttribute("memberName");
%> 

<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--검색창-->
	<div id="select-line">
		<form>
			<div class="select-rooms text-center mt-5 mb-5">
				<div>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-1"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-1">전체보기</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-2"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-2">회원가입/정보</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-3"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-3">예약/결제</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-4"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-4">취소/환불</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-5"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-5">기타문의</label>
				</div>


				<div class="float-end mt-5">
					<div class="search-area d-flex">
						<select name="search-type" aria-label="">
							<option value="title">제목</option>
							<option value="contents">내용</option>
							<option value="writer">작성자</option>
						</select> <input type="text" name="keyword" placeholder="검색어를 입력해 주세요"
							class="ms-1">
						<button type="button" class="btn btn-primary btn-sm ms-1">검색</button>
					</div>
				</div>
		</form>
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
					<td colspan="5">조회된 공지사항이 없습니다.</td>
				</tr>
			<%} else { 
				for(int i=0;i<Qnas.size();i++){%>
				<tr>
					<td><%=Qnas.get(i).getQuestionNo()%></td>
					<td><%=Qnas.get(i).getCategoryName()%></td>
					<td>
						<a href="<%=request.getContextPath()%>/qna/viewQna.do?no=<%=Qnas.get(i).getQuestionNo()%>
						&categoryName=<%=request.getAttribute("categoryName")%>
						&communityTitle=<%=request.getAttribute("communityTitle")%>"><%=Qnas.get(i).getQuestionTitle()%></a>
					</td>
					<td><%=members.get(i)%></td>					
					<td><%=Qnas.get(i).getQuestionDate()%></td>
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