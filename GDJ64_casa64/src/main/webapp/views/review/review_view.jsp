<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/nara_publish/css/common.css" />
<%@ page import="java.util.List,com.btc.review.model.vo.Review,com.btc.review.model.vo.ReviewImages" %>
<%
	Review reviews = (Review)request.getAttribute("review");
	List<ReviewImages> imgList = (List)request.getAttribute("reviewImages");
%> 

<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--목록버튼-->
	<div class="list-btn">
		<a href="<%=request.getContextPath()%>/review/reviewList" class="btn btn-primary btn-sm ms-1">목록</a>
		<%if(loginMember != null && loginMember.getMemberNo()==reviews.getMemberNo()) {%>
			<a href="<%=request.getContextPath()%>/review/reviewWrite?no=<%= reviews.getNo() %>" class="btn btn-primary btn-sm ms-1">수정하기</a>
		<%} %>
		<%if(loginMember != null && (loginMember.getMemberNo() == reviews.getMemberNo() || loginMember.getMemberType() == 1) ) {%>
			<a href="<%=request.getContextPath()%>/review/reviewWrite?no=<%= reviews.getNo() %>" class="btn btn-primary btn-sm ms-1">삭제하기</a>
		<%} %>
	</div>

	<!--게시판 상세보기-->
	<div class="border-view">
		<div class="title-inner row border-top border-bottom">
			<div class="col-12 qnaview-category border-bottom pt-3 pb-3">
				<button type="button" class="btn btn-secondary qnaview-button">객실</button>
				&nbsp; <small class="text-secondary qnaview-caterogy"><%= reviews.getRoomName() %></small>
			</div>
			<div class="col-10 title-inner">
				<p class="title">
					<b><%= reviews.getTitle() %></b>
				</p>
				<p class="writer">
					<%= reviews.getNickName() %><span class="date-created ms-2"><%= reviews.getDateCreated() %></span>
				</p>
			</div>
			<div class="count-inner col-2">
				<p class="count">
					조회수 <span class="count-num"><%= reviews.getViews() %></span>
				</p>
			</div>
		</div>
		<div class="row border-bottom">
			<div class="col board-data">
				<p class="text-data"><%= reviews.getContents() %></p>
				<!-- 파일이 있을 경우 보여줘야함 -->
				<%
					if(imgList != null && !imgList.isEmpty()){
						for(ReviewImages ri : imgList){
				%>
					<img class="img-data" src="<%= ri.getSaveFileName()%>" style="max-width:300px">
				<%}} %>
			</div>
		</div>

		<div class="row comment-inner">
			<%if(loginMember != null && loginMember.getMemberType() == 1 && reviews.getIsReply() < 1) {%>
				<div class="row">
					<div class="col-11">
						<textarea class="comment form-control w-100" id="adminReply" name="adminReply" rows="3"></textarea>
					</div>
					<div class="col-1 float-end">
						<button type="button" class="comment-btn btn btn-dark" onClick="addComment()">등록</button>
					</div>
				</div>
			<%} else if( reviews.getIsReply() == 1 ) {%>
				<div class="col-12 admin-comment">
					<p class="writer">
						관리자아이디 <span class="date-created">2023-06-01 12:32:45</span>
					</p>
					<p class="comment-data">관리자가 입력한 댓글이 들어갑니다.</p>
				</div>
			<% 	if(loginMember != null && loginMember.getMemberType() == 1) {%>
				<div class="delete-btn col-2">
					<button class="btn btn-dark btn-sm ms-1">수정</button>
					<button class="btn btn-dark btn-sm ms-1">삭제</button>
				</div>
			<%}} %>
		</div>
	</div>
</div>
<script>
	function addComment(){
		if($('#adminReply').val().length < 1 ){
			alert('내용을 입력해 주세요!');
			return;
		}
		$.ajax({
		    type : 'post',           // 타입 (get, post, put 등등)
		    url : '/review/reply',           // 요청할 서버url
		    dataType : 'text',       // 데이터 타입 (html, xml, json, text 등등)
		    data : JSON.stringify({  // 보낼 데이터 (Object , String, Array)
		      "adminReply" : $('#adminReply').val(),
		      "reviewNo" : <%= reviews.getNo() %>,
		    }),
		    success : function(result) { // 결과 성공 콜백함수
		        console.log(result);
		    },
		    error : function(request, status, error) { // 결과 에러 콜백함수
		        console.log(error)
		    }
		})
	}
</script>



<!-- 내용 종료 -->


<%@ include file="/views/common/footer.jsp"%>