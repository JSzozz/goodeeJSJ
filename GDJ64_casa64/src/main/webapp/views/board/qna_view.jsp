<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.btc.notice.model.dto.Qna" %>
<%@page import="java.util.List,com.btc.notice.model.dto.QnaComment,java.text.SimpleDateFormat"%>
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
		<form action="<%=request.getContextPath()%>/qna/updateQna.do" method="post"> <!-- 수정하기 누르면 기존 값 이동 -->
		<input type="hidden" name="categoryName" value="<%=q.getCategoryName()%>">
		<input type="hidden" name="questionTitle" value="<%=q.getQuestionTitle()%>">
		<input type="hidden" name="memberNo" value="<%=q.getMemberNo()%>">
		<input type="hidden" name="questionContent" value="<%=q.getQuestionContent()%>">
		<input type="hidden" name="no" value="<%=request.getAttribute("no")%>">
		<%if (loginMember!=null && loginMember.getMemberName().equals(request.getAttribute("memberName"))) {%>
				<button type="submit" class="btn btn-primary btn-sm ms-1" >수정하기</button>
				<button type="button" class="btn btn-primary btn-sm ms-1" 
					onclick="removeCheck()">삭제하기</button>
		<%} %>
		<button type="button" class="btn btn-primary btn-sm ms-1" 
			onclick="location.assign('<%=request.getContextPath()%>/qna/insertQna.do')" >목록</button>
		</form>
	</div>

	<!--게시판 상세보기-->
	<div class="border-view">
		<div class="row border-top border-bottom">
			<div class="col-12 qnaview-category border-bottom pt-3 pb-3">
				<button type="button" class="btn btn-secondary qnaview-button">구분</button>&nbsp;
				<small class="text-secondary qnaview-caterogy"><%=q.getCategoryName()%></small>
			</div>
			<div class="col-10 title-inner">
				<p class="title">
					<b><%=q.getQuestionTitle()%></b>
				</p>
				<p class="writer">
					<%=request.getAttribute("memberName")%><span class="date-created">&nbsp;&nbsp;<%=q.getQuestionDate()%></span>
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
		
		<!-- 댓글 로직 -->
		<div class="row comment-inner">
			<form action="<%=request.getContextPath()%>/qna/insertQnaComment.do
						?no=<%=q.getQuestionNo()%>
						&categoryName=<%=request.getAttribute("categoryName")%>
						&communityTitle=<%=request.getAttribute("communityTitle")%>">
				<%-- <input type="hidden" name="QnaRef" value="<%=q.getQuestionNo()%>"> <!-- 질문의 번호 --> --%>
				<input type="hidden" name="QnaCommentLevel" value="1"> <!-- 댓글,답글여부 -->
				<input type="hidden" name="QnaCommentWriter" value="<%=loginMember!=null?loginMember.getMemberName():""%>"> <!-- 로그인되어있으면=>지금아이디 -->
				<div class="row">
					<div class="col-11">
						<textarea class="comment form-control w-100 qnaCommentInsert" name="QnaCommentContent"
							id="exampleFormControlTextarea1" rows="3"></textarea> <!-- 댓글내용 -->
					</div>
					<div class="col-1 float-end">
						<input type="submit" class="comment-btn btn btn-dark" value="등록">
					</div>
				</div>
			</form>
				<!-- 답글 로직 (없으면 안보여야됨)-->
				<%if(comments!=null) {
				for(QnaComment qc: comments){
					if(qc.getQnaCommentLevel()==1){%>
						<div class="col-12 admin-comment border-bottom level1">
							<div class="row">
								<div class="col-11">
									<p class="writer">
										<%=qc.getQnaCommentWriter()%>
										<span class="date-created">&nbsp;&nbsp;<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(qc.getQnaCommentDate())%></span>
									</p>
									<p class="comment-data"><%=qc.getQnaCommentContent() %></p>
								</div>
								<div class="commentContainer delete-btn col-2 float-end">
								<%if(loginMember!=null) {%>
									<button type="button"  class="insertComment btn btn-dark btn-sm ms-1">답글</button>
									<%if(loginMember.getMemberName().equals("admin")
									||loginMember.getMemberName().equals(qc.getQnaCommentWriter())) {%>
										<button class="btn btn-dark btn-sm ms-1">수정</button>
										<button class="btn btn-dark btn-sm ms-1">삭제</button>
									<%} %>															
								<%} %>
								</div>
							</div>
						</div>
				<%}else{ %>
					<div class="col-12 admin-comment border-bottom level2">
							<div class="row">
								<div class="col-11">
									<p class="writer">
										<%=qc.getQnaCommentWriter()%>
										<span class="date-created">&nbsp;&nbsp;
										<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(qc.getQnaCommentDate())%></span>
									</p>
									<p class="comment-data"><%=qc.getQnaCommentContent() %></p>
								</div>
								<div class="delete-btn col-2 float-end">
									<button class="btn btn-dark btn-sm ms-1">답글</button>
									<%if(loginMember!=null&&(loginMember.getMemberName().equals("admin")
									||loginMember.getMemberName().equals(qc.getQnaCommentWriter()))) {%>
										<button class="btn btn-dark btn-sm ms-1">수정</button>
										<button class="btn btn-dark btn-sm ms-1">삭제</button>
									<%} %>							
								</div>
							</div>
						</div>
				<%} 
				}}%>
		</div>

	</div>
</div>
<button onClick="insertComment();" class="goNextBtn" >
다음단계
</button>
<script>
	
$(function insertComment() {
     $(document).on("click","button[class=goNextBtn]",()=>{
    	 alert("GDGD");
    });
    
 });   
	
	
	
	
	$("#testasdasd").click(e=>{
		alert("sdfs");
		const flag = $(".commentContainer").find("button").val("답글");
	});
	$(".qnaCommentInsert").click(e=>{
		console.log(<%=loginMember%>);
		if(<%=loginMember%>==null){
			alert("로그인 후 이용가능");
			location.assign("<%=request.getContextPath()%>/views/LOGIN/login.jsp");
		}
	});

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