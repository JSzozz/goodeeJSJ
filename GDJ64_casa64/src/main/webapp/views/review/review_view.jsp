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
			<a href="<%=request.getContextPath()%>/review/reviewWrite?no=<%= reviews.getNo() %>" class="btn btn-primary btn-sm ms-1">수정</a>
		<%} %>
		<%if(loginMember != null && (loginMember.getMemberNo() == reviews.getMemberNo() || loginMember.getMemberType() == 1) ) {%>
			<a href="#" class="btn btn-primary btn-sm ms-1" onclick="reviewDelete(<%= reviews.getNo() %>)">삭제</a>
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
		<style>
			.show {display:block;}
			.hide {display:none;}
		</style>
		<div class="row comment-inner">
			<%
				String isWriteShowClass = "hide"; // 작성할 수 있는 영역
				String isCommentShowClass = "hide"; // 작성된 댓글
				String isAdmin = "hide"; // 삭제, 수정 버튼
				if(loginMember != null && loginMember.getMemberType() == 1 && reviews.getIsReply() < 1) {
					isWriteShowClass = "show";
				}
				if( reviews.getIsReply() == 1 ) {
					isCommentShowClass = "show";
				}
				if(loginMember != null && loginMember.getMemberType() == 1 && reviews.getIsReply() == 1) {
					isAdmin = "show";
				}
			%>
				<div class="comment-write-area <%= isWriteShowClass %>" id="comment-write-area">
					<div class="row ">
						<div class="col-11">
							<textarea class="comment form-control w-100" id="adminReply" name="adminReply" rows="3"></textarea>
						</div>
						<div class="col-1 float-end">
							<button type="button" class="comment-btn btn btn-dark" onClick="addComment(1)">등록</button>
						</div>
					</div>
				</div>
			
				<div class="admin-comment <%= isCommentShowClass %>">
					<div class="col-12">
						<p class="writer">
							관리자<span class="date-created ms-2"><%= reviews.getLastReplyDate() %></span>
						</p>
						<p class="comment-data"><%= reviews.getAdminReply() %></p>
					</div>
					<div class="delete-btn col-2 <%=isAdmin%>">
						<button class="btn btn-dark btn-sm ms-1" onClick="showUpdateComment()">수정</button>
						<button class="btn btn-dark btn-sm ms-1" onClick="addComment(0)">삭제</button>
					</div>
				</div>
		</div>
	</div>
</div>
<script>
	/* 수정하기 버튼 클릭 시 댓글 작성 영역 보여주기 */
	function showUpdateComment(){
		$('.comment-write-area').show();
		$('#adminReply').val($('.comment-data').text());
		$('#adminReply').focus();
	}
	/* 관리자 댓글 작성, 수정, 삭제 기능 (ajax) */
	function addComment(isReply){
		var msg = "댓글이 삭제 되었습니다.";
		if(isReply > 0) {
			if($('#adminReply').val().length < 1 ){
				alert('내용을 입력해 주세요!');
				return;
			}
			msg = "댓글이 작성 되었습니다.";
		}
		
		let today = new Date();   

		today.toLocaleString()
		
		$.ajax({
		    type : 'post',           // 타입 (get, post, put 등등)
		    url : "<%=request.getContextPath()%>/review/reply",        
		    dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
		    data : {  // 보낼 데이터 (Object , String, Array)
		      "adminReply" : $('#adminReply').val(),
		      "reviewNo" : <%= reviews.getNo() %>,
		      "isReply" : isReply,
		    },
		    success : function(result) { // 결과 성공 콜백함수
		    	if(result > 0) {
		    		alert(msg);
		    		if(isReply > 0){
			    		$('.comment-data').text($('#adminReply').val());
			    		$('.admin-comment').show();
			    		$('.comment-write-area').hide();
			    		$('.date-created').text(today.toLocaleString());
		    		} else {
		    			$('.admin-comment').hide();
		    			$('.comment-write-area').show();
		    			$('#adminReply').val('');
		    		}
		    	} else {
		    		alert('댓글 작성을 실패하였습니다.');
		    	}
		    },
		    error : function(request, status, error) { // 결과 에러 콜백함수
		        console.log(error)
		    }
		})
	}
	/* 리뷰 삭제 */
	function reviewDelete(reviewNo) {
		if(confirm('삭제하시겠습니까?')){
			$.ajax({
			    type : 'post',           // 타입 (get, post, put 등등)
			    url : "<%=request.getContextPath()%>/review/reviewDelete",           // 요청할 서버url
			    dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
			    data : {  // 보낼 데이터 (Object , String, Array)
			      "reviewNo" : reviewNo,
			    },
			    success : function(result) { // 결과 성공 콜백함수
			    	if(result > 0) {
			    		alert('삭제되었습니다.');
			    		location.href="<%=request.getContextPath()%>/review/reviewList";
			    	} else {
			    		alert('게시물 삭제 실패!');
			    	}
			    },
			    error : function(request, status, error) { // 결과 에러 콜백함수
			        console.log(error)
			    }
			})
			
		}
	}
</script>



<!-- 내용 종료 -->


<%@ include file="/views/common/footer.jsp"%>