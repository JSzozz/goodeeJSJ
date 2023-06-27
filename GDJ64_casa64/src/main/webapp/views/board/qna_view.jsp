<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.btc.notice.model.dto.Qna" %>
<%@page import="java.util.List,com.btc.notice.model.dto.QnaComment"%>
<%
	Qna q = (Qna)request.getAttribute("qna"); //boardViewServlet
	int ref=Integer.parseInt(request.getParameter("no")); //
	List<QnaComment> comments = (List)request.getAttribute("comments");
%>
<%@ include file="/views/common/header.jsp"%>

<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--목록버튼-->
	<div class="list-btn">
		<form action="<%=request.getContextPath()%>/notice/updateNotice.do" method="post"> <!-- 수정하기 누르면 기존 값 이동 -->
		<input type="hidden" name="title" value="<%=q.getQuestionTitle()%>">
		<input type="hidden" name="content" value="<%=q.getQuestionContent()%>">
		<input type="hidden" name="no" value="<%=request.getAttribute("no")%>">
		<%if (loginMember!=null && loginMember.getMemberName().equals("admin")) {%>
				<button type="submit" class="btn btn-primary btn-sm ms-1" >수정하기</button>
				<button type="button" class="btn btn-primary btn-sm ms-1" 
					onclick="removeCheck()">삭제하기</button>
		<%} %>
		<button type="button" class="btn btn-primary btn-sm ms-1" 
			onclick="location.assign('<%=request.getContextPath()%>/notice/insertNotice.do')" >목록</button>
	</form>
	</div>

	<!--게시판 상세보기-->
	<div class="border-view">
		<div class="row border-top border-bottom">
			<div class="col-12 qnaview-category border-bottom pt-3 pb-3">
				<button type="button" class="btn btn-secondary qnaview-button">구분</button>&nbsp;
				<small class="text-secondary qnaview-caterogy">회원가입/정보</small>
			</div>
			<div class="col-10 title-inner">
				<p class="title">
					<b><%=q.getQuestionTitle()%></b>
				</p>
				<p class="writer">
					<%=q.getMemberNo()%><span class="date-created"><%=q.getQuestionDate()%></span>
				</p>
			</div>
			<!-- <div class="count-inner col-2">
				<p class="count">
					조회수 <span class="count-num">999</span>
				</p>
			</div> -->
		</div>
		<div class="row border-bottom">
			<div class="col board-data">
				<p class="text-data"><%=q.getQuestionContent()%></p>
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