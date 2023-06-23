<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.btc.notice.model.dto.Notice" %>
<%
	Notice n=(Notice)request.getAttribute("notice"); //boardViewServlet
	int ref=Integer.parseInt(request.getParameter("no")); //
%>
<%@ include file="/views/common/header.jsp"%>
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--목록버튼-->
	<div class="list-btn">
		<button type="button" class="btn btn-primary btn-sm ms-1" onclick="location.assign('<%=request.getContextPath()%>/notice/insertNotice.do')" >목록</button>
	</div>

	<!--게시판 상세보기-->
	<div class="border-view">
		<div class="row border-top border-bottom">
			<div class="col-10 title-inner">
				<p class="title">
					<b><%=n.getNoticeTitle() %></b>
				</p>
				<p class="writer">
					작성자(관리자) <span class="date-created"><%=n.getDateCreated() %></span>
				</p>
			</div>
			<div class="count-inner col-2">
				<p class="count">
					조회수 <span class="count-num"><%=n.getNoticeReadCount() %></span>
				</p>
			</div>
		</div>
		<div class="row border-bottom">
			<div class="col board-data">
				<p class="text-data"><%=n.getNoticeContent() %></p>
				<img class="img-data" src="<%=request.getContextPath() %>/nara_publish/images/ex_img.png">
			</div>
		</div>

		<div class="comment-inner"></div>

	</div>

	<!-- 내용 종료 -->

	<%@ include file="/views/common/footer.jsp"%>