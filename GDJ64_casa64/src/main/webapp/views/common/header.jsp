<%@page import="com.btc.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom.css" /> --%>

<%
	Member loginMember=(Member)session.getAttribute("loginMember");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- <meta charset="UTF-8"> -->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=yes">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <title>CASA64</title>
    <script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
    <!-- 헤더,푸터 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
    <!-- swiper -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/swiper-bundle.min.css"/>
    <script src="<%=request.getContextPath()%>/js/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/sw/test.css"/>
    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
</head>

<body>
<!-- header -->
<header id="header">
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/casa64.png" width="120px"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="position: absolute;left: 50%; transform: translateX(-45%);">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown" href="../ABOUT/about.html" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            ABOUT
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a href="../ABOUT/about.html" class="dropdown-item">오시는 길</a></li>
                            <li><a href="../ABOUT/travel.html" class="dropdown-item">주변 여행지</a></li>
                            <li><a href="../ABOUT/team-introduction.html" class="dropdown-item">조원소개</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown" href="#" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            COMMUNITY
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a href="#" class="dropdown-item">FAQ</a></li>
                            <li><a href="#" class="dropdown-item">공지사항</a></li>
                            <li><a href="#" class="dropdown-item">후기</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown" href="#" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            ROOMS
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a href="#" class="dropdown-item">객실별 정보</a></li>
                            <li><a href="#" class="dropdown-item">이 객실 예약하기</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown" href="#" id="navbarDropdown" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            RESERVATION
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a href="#" class="dropdown-item">예약하기</a></li>
                            <li><a href="#" class="dropdown-item">예약 관련 정보</a></li>
                        </ul>
                    </li>
    
                </ul>
            </div>
            
            <%if(loginMember!=null&&(loginMember.getEmail().equals("admin@btc.com"))){ %>
            <div id="login-selector">
                <ul class=navbar-nav>
                
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/views/admin/admin-page.jsp">ADMIN</a>
                    </li>            
                    
                </ul>
            </div>
            <%} %>
            <div id="login-selector">
                <ul class=navbar-nav>
                <%if(loginMember==null) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/views/LOGIN/login.jsp">LOGIN</a>
                    </li>            
                <%}else{%>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">MYPAGE</a>
                    </li>
                    <li class="nav-item logout">
                        <a class="nav-link logout" onclick="location.replace('<%=request.getContextPath() %>/logout.do')">LOGOUT</a>
                    </li>            
                <%} %>
                    
                </ul>
            </div>
        </div>
    </nav>
</header>