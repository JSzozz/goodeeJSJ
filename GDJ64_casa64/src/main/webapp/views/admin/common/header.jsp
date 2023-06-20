<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CASA64</title>
<!-- 부트스트랩 CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/custom.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

<!-- 부트스트랩 아이콘 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
    <!-- 로그아웃 모달 -->
    <div class="modal fade" id="logout" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="logoutModalLabel">경고</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                정말로 로그아웃합니까?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger">로그아웃</button>
                <button type="button" class="btn btn-dark" data-bs-dismiss="modal">취소</button>
            </div>
            </div>
        </div>
    </div>

    <!-- 네비게이션 -->
    <nav class="navbar navbar-expand-md navbar-light">
        <button type="button" class="navbar-toggler ms-auto mb-2 bg-light" data-bs-toggle="collapse" data-bs-target="#sidebar">
            <span class="navbar-toggle-icon"></span>
        </button>
        <div id="sidebar" class="collapse navbar-collapse">
            <div class="container-fluid">
                <div class="row">
                    <!-- 사이드바 -->
                    <div class="col-xl-2 col-lg-3 sidebar fixed-top bg-dark">
                        <div class="p-3 mt-3 border-bottom text-center">
                            <img src="<%=request.getContextPath()%>/images/logo.png" alt="로고사진" width="200" height="100">
                            <span class="navbar-brand text-white d-block m-0 fs-2 mt-2">CASA64</span>
                        </div>
                        <ul class="navbar-nav flex-column text-center mx-auto mt-4">
                            <li class="nav-item mb-1">
                                <a href="#" class="nav-link text-white admin-link sidebar-link"><i class="bi bi-person-fill me-2"></i>회원관리</a>
                            </li>
                            <li class="nav-item mb-1">
                                <a href="#" class="nav-link text-white admin-link sidebar-link"><i class="bi bi-person-fill-lock me-2"></i>블랙회원</a>
                            </li>
                            <li class="nav-item mb-1">
                                <a href="#" class="nav-link sidebar-link text-white"><i class="bi bi-houses-fill me-2"></i>객실관리</a>
                            </li>
                            <li class="nav-item mb-1">
                                <a href="#" class="nav-link sidebar-link text-white"><i class="bi bi-currency-exchange me-2"></i>요금관리</a>
                            </li>
                            <li class="nav-item mb-1">
                                <a href="#" class="nav-link sidebar-link text-white"><i class="bi bi-megaphone-fill me-2"></i>게시판</a>
                            </li>
                            <li class="nav-item mb-1">
                                <a href="#" class="nav-link sidebar-link text-white"><i class="bi bi-calendar-fill me-2"></i>예약관리</a>
                            </li>
                            <li class="nav-item mb-1">
                                <a href="#" class="nav-link text-white admin-link sidebar-link"><i class="bi bi-person-fill-dash me-2"></i>탈퇴회원</a>
                            </li>
                        </ul>
                    </div>
                    <!-- 상단바 -->
                    <div class="col-xl-10 col-lg-9 ms-auto">
                        <div class="row d-flex justify-content-end align-items-center">
                            <div class="col-auto">
                                <ul class="navbar-nav ms-auto">
                                    <li class="nav-item"><a href="<%=request.getContextPath()%>/" class="nav-link top-link">메인으로</a></li>
                                    <li class="nav-item"><a href="#" class="nav-link top-link">홈페이지</a></li>
                                    <li class="nav-item"><a href="#" class="nav-link top-link" data-bs-toggle="modal" data-bs-target="#logout">로그아웃</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
