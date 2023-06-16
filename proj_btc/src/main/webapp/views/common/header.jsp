<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ 
page import="com.web.model.dto.MemberDTO"%>
<%
/* (*LoginfrmServlet)(로그인 기능)client-DB 유효성 검사 */
MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
/* (*LoginfrmServlet)(로그인 기능)아이디 저장 */
Cookie[] cookies = request.getCookies();
String saveId = null;
if (cookies != null) {
	for (Cookie c : cookies) {
		if (c.getName().equals("saveId")) {
	saveId = c.getValue();
	break;
		}
	}
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cosa64</title>

<!-- (경로에 '?after' 추가) -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css?after">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>

<!-- bootstrap css cdn처리-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

<!-- Failed to load resource: the server responded with a status of 404 (Not Found) 오류메세지 제거용 -->
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">

</head>
<body>
	<!-- bootstrap cdn처리 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
		
	<nav class="navbar navbar-expand-lg navbar-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"><img src="<%=request.getContextPath() %>/images/casa64.png"
				width="120px"></a>

			<button class="navbar-toggler black" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item dropdown"><a class="nav-link dropdown"
						href="#" id="navbarDropdown" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> about </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a href="#" class="dropdown-item">오시는 길</a></li>
							<li><a href="#" class="dropdown-item">주변 여행지</a></li>
							<li><a href="#" class="dropdown-item">조원소개</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown"
						href="#" id="navbarDropdown" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> customer </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a href="#" class="dropdown-item">FAQ</a></li>
							<li><a href="#" class="dropdown-item">공지사항</a></li>
							<li><a href="#" class="dropdown-item">후기</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown"
						href="#" id="navbarDropdown" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> rooms </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a href="#" class="dropdown-item">객실별 정보</a></li>
							<li><a href="#" class="dropdown-item">이 객실 예약하기</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a class="nav-link dropdown"
						href="#" id="navbarDropdown" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> reserve </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a href="<%=request.getContextPath() %>/views/reserve/reserveList1.jsp" class="dropdown-item">예약하기</a></li>
							<li><a href="#" class="dropdown-item">예약 관련 정보</a></li>
						</ul></li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">mypage</a></li>
					<li class="nav-item"><a class="nav-link" href="#">logout</a></li>
				</ul>
			</div>
		</div>
	</nav>