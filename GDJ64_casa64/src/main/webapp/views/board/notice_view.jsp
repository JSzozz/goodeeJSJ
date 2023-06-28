<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.btc.notice.model.dto.Notice" %>
<%@page import="com.btc.notice.model.dto.Notice_images"%>
<%@ include file="/views/common/header.jsp"%>
<%
	Notice n = (Notice)request.getAttribute("notice"); //boardViewServlet
	int ref=Integer.parseInt(request.getParameter("no")); //
	Notice_images i = (Notice_images)request.getAttribute("Notice_images");
%>
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--목록버튼-->
	<div class="list-btn">
	<form action="<%=request.getContextPath()%>/notice/updateNotice.do" method="post"> <!-- 수정하기 누르면 기존 값 이동 -->
		<input type="hidden" name="title" value="<%=n.getNoticeTitle() %>">
		<input type="hidden" name="content" value="<%=n.getNoticeContent() %>">
		<%if(i==null) {%>
		<%}else {%>
			<input type="hidden" name="file" value="<%=i.getFileName()%>">
		<%} %>
		<input type="hidden" name="no" value="<%=request.getAttribute("no")%>">
		<%if (loginMember!=null && loginMember.getMemberName().equals("admin")) {%>
				<button type="submit" class="btn btn-primary btn-sm ms-1" >수정하기</button>
				<button type="button" class="btn btn-primary btn-sm ms-1" 
					onclick="removeCheck()">삭제하기</button>
		<%} %>
		<button type="button" class="btn btn-primary btn-sm ms-1" 
			onclick="location.assign('<%=request.getContextPath()%>/notice/insertNotice.do')" >목록</button>
	</form>
	</div> $("<form>").submit()

	<!--  수정정보 보내기 -->
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
				<%if(i==null) {%>
				<%}else {%>
					<img class="img-data" src="<%=request.getContextPath() %>/images/notice/<%=i.getFileName()%>" height="300px">
				<%} %>
			</div>
		</div>

		<div class="comment-inner"></div>

	</div>
</div>
<script>
	function removeCheck() {
		 if (confirm("정말 삭제하시겠습니까??") == true){
		     location.assign('<%=request.getContextPath()%>/notice/deleteNotice.do?no=<%=request.getAttribute("no")%>');
		 }else{
		     return false;
		 }
	}
</script>
	<!-- 내용 종료 -->

	<%@ include file="/views/common/footer.jsp"%>