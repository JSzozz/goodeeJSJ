<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.List,com.btc.mypage.model.vo.QnA"%>
<%
List<QnA> qna = (List) request.getAttribute("MypageQnAList");
%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>나의 문의사항</h2>
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
						<col width="40px" />
						<col width="100px" />
						<col width="150px" />
						<col width="50px" />
						<col width="60px" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리</th>
							<th>제목</th>
							<th>작성일</th>
							<th>비고</th>

						</tr>
					</thead>
					<tbody>
					<tbody>
						<%
						if (qna != null && !qna.isEmpty()) {
							int count = qna.size();
							for (QnA q : qna) {
						%>
						<tr>
							<td><%=count%></td>
							<td><%=q.getCategoryName()%></td>
							<td><a
								href="<%=request.getContextPath()%>/review/reviewView?no=<%=q.getMemberNo()%>">
									<%=q.getQuestionTitle()%>
							</a></td>
							<td><%=q.getQuestionDate()%></td>
							<td><a
								href="<%=request.getContextPath()%>/review/reviewWrite?no=<%=q.getMemberNo()%>"
								class="btn btn-primary btn-sm">문의수정</a></td>
						</tr>
						<%
						count--;
						}
						} else {
						%>
						<tr>
							<td colspan="6">작성된 게시물이 없습니다.</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<%@ include file="/views/common/footer.jsp"%>
