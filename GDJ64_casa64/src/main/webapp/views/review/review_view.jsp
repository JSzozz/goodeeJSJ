<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/nara_publish/css/common.css" />
<%@ page import="java.util.List,com.btc.review.model.vo.Reviews" %>
<%
	Reviews reviews = (Reviews)request.getAttribute("review");
%> 

<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--목록버튼-->
	<div class="list-btn">
		<button type="button" class="btn btn-primary btn-sm ms-1">목록</button>
		<a href="<%=request.getContextPath()%>/board/reviewWrite.do?no=<%= reviews.getNo() %>" class="btn btn-primary btn-sm ms-1">수정하기</a>
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
					<%= reviews.getNickName() %><span class="date-created"><%= reviews.getDateCreated() %></span>
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
				<img class="img-data"
					src="<%=request.getContextPath()%>/publish/images/ex_img.png">
			</div>
		</div>

		<div class="row comment-inner">
			<form>
				<div class="row">
					<div class="col-11">
						<textarea class="comment form-control w-100"
							id="exampleFormControlTextarea1" rows="3"></textarea>
					</div>
					<div class="col-1 float-end">
						<button class="comment-btn btn btn-dark">등록</button>
					</div>
				</div>

				<div class="col-12 admin-comment">
					<p class="writer">
						관리자아이디 <span class="date-created">2023-06-01 12:32:45</span>
					</p>
					<p class="comment-data">관리자가 입력한 댓글이 들어갑니다.</p>
				</div>
				<div class="delete-btn col-2">
					<button class="btn btn-dark btn-sm ms-1">수정</button>
					<button class="btn btn-dark btn-sm ms-1">삭제</button>
				</div>
			</form>
		</div>
	</div>

</div>
</div>



<!-- 내용 종료 -->


<%@ include file="/views/common/footer.jsp"%>