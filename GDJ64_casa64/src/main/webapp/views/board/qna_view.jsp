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
		<form action="<%=request.getContextPath()%>/qna/updateQnaView.do" method="post"> <!-- 수정하기 누르면 기존 값 이동 -->
		<input type="hidden" name="categoryName" value="<%=q.getCategoryName()%>">
		<input type="hidden" name="questionTitle" value="<%=q.getQuestionTitle()%>">
		<input type="hidden" name="memberNo" value="<%=q.getMemberNo()%>">
		<input type="hidden" name="questionContent" value="<%=q.getQuestionContent()%>">
		<input type="hidden" name="no" value="<%=request.getAttribute("no")%>">
		<%if (loginMember!=null && loginMember.getMemberName().equals(request.getAttribute("memberName"))) {%>
				<button type="submit" class="btn btn-primary btn-sm ms-1" >수정하기</button>
				<button type="button" class="btn btn-primary btn-sm ms-1 deleteQnaList removeCheck" 
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
		</div>
		<div class="row border-bottom">
			<div class="col board-data">
				<p class="text-data"><%=q.getQuestionContent()%></p>
			</div>
		</div>
		
		<!-- 댓글 생성 로직 -->
		<div class="row comment-inner"> <!-- ===== clone()한 대상 ====== -->
			<form action="<%=request.getContextPath()%>/qna/insertQnaComment.do">
				<input type="hidden" name="no" value="<%=q.getQuestionNo()%>">
				<input type="hidden" name="categoryName" value="<%=request.getAttribute("categoryName")%>">
				<input type="hidden" name="communityTitle" value="<%=request.getAttribute("communityTitle")%>">
				<input type="hidden" name="QnaRef" value="<%=q.getQuestionNo()%>"> <!-- 질문의 번호 -->
				<input type="hidden" name="QnaCommentLevel" value="1"> <!-- 댓글,답글여부 -->
				<input type="hidden" name="QnaCommentWriter" value="<%=loginMember!=null?loginMember.getMemberName():""%>">
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
		</div>
		<!-- 댓글 조회 로직 (없으면 안보여야됨)-->
		<%if(comments!=null) {
		for(QnaComment qc: comments){
			if(qc.getQnaCommentLevel()==1){%>
				<div class="col-12 admin-comment border-bottom level1">
					<div class="row point1">
						<div class="col-11">
							<p class="writer">
								<%=qc.getQnaCommentWriter()%>
								<span class="date-created">&nbsp;&nbsp;<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(qc.getQnaCommentDate())%></span>
							</p>
							<p class="comment-data"><%=qc.getQnaCommentContent() %></p> <!-- 답글내용 -->
						</div>
						<div class="commentContainer delete-btn col-2 float-end">
							<%if(loginMember!=null) {%>
								<button type="button"  class="insertComment btn btn-dark btn-sm ms-1">답글</button>
								<%if(loginMember.getMemberName().equals("admin")
								||loginMember.getMemberName().equals(qc.getQnaCommentWriter())) {%>
									<button type="button" class="qnaUpdateBtn btn btn-dark btn-sm ms-1" value="<%=qc.getQnaCommentNo()%>">수정</button>
									<button class="btn btn-dark btn-sm ms-1 deleteComment removeCheck">삭제</button>
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
<script>


	$(".qnaUpdateBtn").on("click", function(e){
		const update = $(e.target).parents(".point1").find(".comment-data");
		console.log(update.text());
		const form = $(".comment-inner>form").clone();
		form.attr("action",'<%=request.getContextPath()%>/qna/updateQnaComment.do');
		form.find("textarea").val(update.text());
		/* const no = $("<input>").attr({"type":"hidden","name":"QnaCommentNo"}).val($(e.target).val());
		const categoryName = $("<input>").attr({"type":"hidden","name":"categoryName"}).val($(e.target).val());
		const communityTitle = $("<input>").attr({"type":"hidden","name":"communityTitle"}).val($(e.target).val()); */
		const input = $("<input>").attr({"type":"hidden","name":"QnaCommentNo"}).val($(e.target).val());
		form.append(input);
		update.html(form);
		$(".qnaUpdateBtn").click(function(){
			$(e.target).off("click");			
		});
	});
	
	$(".qnaCommentInsert").click(e=>{
		 <%if(loginMember == null) {%>
		 	alert("로그인 후 이용가능");
			location.assign("<%=request.getContextPath()%>/views/LOGIN/login.jsp");
		 <%}%>
	});
	$(".removeCheck").on("click", function(e){
 		const i = $(e.target).attr("class");
 		alert(i);
		 if (confirm("정말 삭제하시겠습니까??") == true){
			 if($("button").hasClass("deleteComment") == true ){ //댓글지우는 로직
				alert("댓글을 제거하시겠습니까?");
			    location.assign('<%=request.getContextPath()%>/qna/deleteQnaComment.do?no=<%=request.getAttribute("no")%>');
			 }
			 if($("button").hasClass("deleteQnaList") == true ){
				alert("리스트를 제거하시겠습니까?");
			    location.assign('<%=request.getContextPath()%>/qna/deleteQnaView.do?no=<%=request.getAttribute("no")%>');
			 }
		 }else{
		     return false;
		 }		
	});
/*  	const removeCheck=()=> {
	}; */
</script>


	<!-- 내용 종료 -->

	<%@ include file="/views/common/footer.jsp"%>