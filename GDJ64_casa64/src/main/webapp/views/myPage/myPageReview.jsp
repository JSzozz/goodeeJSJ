<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.List,com.btc.review.model.vo.Review"%>
<%
List<Review> reviews = (List) request.getAttribute("MypageReviewList");
%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>나의 이용후기</h2>
		</div>

	</div>
	<div class="row text-dark min-vh-100">
		<div class="mt-5 col-2 border-end">
			<%@ include file="/views/common/asideNav.jsp"%>
		</div>
		<div class="mt-5 col-9 mx-auto">

			<div class="tb">
				<table class="table table-hover text-center">
					<colgroup>
						<col width="40px" />
						<col width="100px" />
						<col width="150px" />
						<col width="50px" />
						<col width="60px" />
						<col width="60px" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>이용객실</th>
							<th>제목</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<%
						if(reviews != null && !reviews.isEmpty()) {
							for (Review r : reviews) {
						%>
							<tr>
								<td><%=r.getNo()%></td>
								<td><%=r.getRoomName()%></td>
								<td><a
									href="<%=request.getContextPath()%>/review/reviewView?no=<%=r.getNo()%>">
										<%=r.getTitle()%>
								</a></td>
								<td><%=r.getViews()%></td>
								<td><%=r.getDateCreated()%></td>
								<td><a href="<%=request.getContextPath() %>/review/reviewWrite?no=<%=r.getNo() %>" class="btn btn-primary btn-sm">리뷰수정</a></td>
							</tr>
						<%
							} 
						} else { %>
							<tr>
								<td colspan="6">작성된 게시물이 없습니다.</td>
							</tr>
						<% } %>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</div>
<%@ include file="/views/common/footer.jsp"%>
