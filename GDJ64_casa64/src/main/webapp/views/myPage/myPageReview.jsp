<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.List,com.btc.review.model.vo.Review" %>
<%
	List<Review> reviews = (List)request.getAttribute("MypageReviewList");
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
				<table class="table table-hover text-center align-middle">
					<colgroup>
						<col width="10%">
						<col width="15%">
						<col width="30%">
						<col width="10%">
						<col width="10%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>객실사진</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><img alt=""
								src="<%=request.getContextPath()%>/images/22.jpg"
								class="img-fluid"></td>
							<td>후기1</td>
							<td>user1</td>
							<td>25</td>
							<td>2023-01-12</td>
							<td><button type="button" class="btn btn-primary btn-sm">리뷰수정</button></td>
						</tr>

						<tr>
							<td>2</td>
							<td><img alt=""
								src="<%=request.getContextPath()%>/images/44.jpg"
								class="img-fluid"></td>
							<td>후기1</td>
							<td>user1</td>
							<td>25</td>
							<td>2023-01-12</td>
							<td><button type="button" class="btn btn-primary btn-sm">리뷰수정</button></td>
						</tr>

						<tr>
							<td>3</td>
							<td><img alt=""
								src="<%=request.getContextPath()%>/images/33.jpg"
								class="img-fluid"></td>
							<td>후기1</td>
							<td>user1</td>
							<td>25</td>
							<td>2023-01-12</td>
							<td><button type="button" class="btn btn-primary btn-sm">리뷰수정</button></td>
						</tr>

						<tr>
							<td>4</td>
							<td><img alt=""
								src="<%=request.getContextPath()%>/images/5.jpeg"
								class="img-fluid"></td>
							<td>후기1</td>
							<td>user1</td>
							<td>25</td>
							<td>2023-01-12</td>
							<td><button type="button" class="btn btn-primary btn-sm">리뷰수정</button></td>
						</tr>


						<!-- 리뷰가 없을 때  -->
						<!-- <tr>
                              <td colspan="6" >
                                작성한 리뷰가 없습니다
                              </td>
                            </tr>
                            <tr>
                              <td colspan="6">
                                <button type="button" class="btn btn-outline-dark">리뷰작성</button>
                              </td>
                            </tr> -->

					</tbody>
				</table>
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
			<% for(Review r : reviews){%>
				<tr>
					<td><%= r.getNo() %></td>
					<td><%= r.getRoomName() %></td>
					<td>
						<a href="<%=request.getContextPath()%>/review/reviewView.do?no=<%= r.getNo() %>">
						<%= r.getTitle() %>
						</a>
					</td>
					<td><%= r.getViews() %></td>
					<td><%= r.getDateCreated() %></td>
					<td><button type="button" class="btn btn-primary btn-sm">리뷰수정</button></td>
				</tr>
				<% }%>
			</tbody>
		</table>
			</div>

		</div>
	</div>
</div>
<%@ include file="/views/common/footer.jsp"%>
