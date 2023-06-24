<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/nara_publish/css/about.css" />



<!-- 컨텐츠/내용 시작 -->

<div class="container">
	<%@ include file="/views/about/about_tab.jsp"%>
	<div class="team-profile">
		<div class="row d-flex justify-content-center">
			<img
				src="<%=request.getContextPath()%>/nara_publish/images/profile/ldj.png"
				alt="" class="col-3">
			<div class="col-3">
				<h5>이동제 (팀장)</h5>
				<p>
					담당기능 :<br> 1. 메인페이지 필터 <br> 2. 관리자페이지 결제관리<br> 3.
					옵션에 따라 캘린더 객실 표시 <br> 4. 옵션추가
				</p>
			</div>
			<img
				src="<%=request.getContextPath()%>/nara_publish/images/profile/ynr.png"
				alt="" class="col-3">
			<div class="col-3">
				<h5>윤나라</h5>
				<p>
					담당기능 :<br> 
					1. 이용후기 작성/수정/삭제 (스마트에디터)<br>
					2. 이용후기 게시글관리/관리자댓글<br>
					3. 이용후기 첨부파일 관리<br>
					4. MyPage 예약/문의/후기조회<br>
					5. About 카테고리 (지도 API)
				</p>
			</div>
		</div>

		<div class="row d-flex justify-content-center">
			<img
				src="<%=request.getContextPath()%>/nara_publish/images/profile/cs.png"
				alt="" class="col-3">
			<div class="col-3">
				<h5>최솔</h5>
				<p>
					담당 기능 : <br> 1. 객실추가 <br> 2. 객실수정 <br> 3. 객실삭제
				</p>
			</div>
			<img
				src="<%=request.getContextPath()%>/nara_publish/images/profile/jsj.png"
				alt="" class="col-3">
			<div class="col-3">
				<h5>정상준</h5>
				<p>
					담당 기능 : <br> 1. 캘린더로 예약하기 <br> 2. 전체객실 및 필터로 조회 <br>
					3. 예약가능/불가능객실 표시 <br> 4. 객실 결제 및 예약관리 (회원)
				</p>
			</div>
		</div>

		<div class="row d-flex justify-content-center">
			<img
				src="<%=request.getContextPath()%>/nara_publish/images/profile/yjh.png"
				alt="" class="col-3">
			<div class="col-3">
				<h5>윤지환</h5>
				<p>
					담당 기능 : <br> 1. 공지사항 작성/수정/삭제 (관리자)<br> 2. Q&A 작성/수정/삭제
					(회원)<br> 3. Q&A 댓글/게시글관리 (관리자)
				</p>
			</div>
			<img
				src="<%=request.getContextPath()%>/nara_publish/images/profile/hsw.png"
				alt="" class="col-3">
			<div class="col-3">
				<h5>홍승우</h5>
				<p>
					담당 기능 : <br> 1. 소셜로그인 / 회원가입 <br> 2. 일반 회원관리 <br> 3.
					블랙리스트 회원관리 <br> 4. 회원 탈퇴
				</p>
			</div>
		</div>
	</div>
</div>

<!-- 내용 종료 -->