<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>사원조회기능</title>
</head>
<body>

	<h2>사원조회기능</h2>
	<h3><a href="${pageContext.request.contextPath }/employee/searchEmployeeAll">전체사원조회하기</a></h3>
	
	<h2>사원조회기능(페이징)</h2>
	<h3><a href="${pageContext.request.contextPath }/employee/searchEmployeePageAll">전체사원조회하기(페이징)</a></h3>
	
	<h2>Data 연관관계설정하기</h2>
	<h3><a href="${pageContext.request.contextPath }/employee/association.do">join으로 객체 가져오기</a></h3>
	
	<h2>부서 조회하기</h2>
	<h3><a href="${pageContext.request.contextPath }/selectDeptAll.do">전체부서 조회하기</a></h3>
	
	<h2>다른 환경 접속하기</h2>
	<h3><a href="${pageContext.request.contextPath }/member.do">회원 가져오기(*web계정이용)</a></h3>
	
	<h2>게시글 가져오기</h2>
	<h3><a href="${pageContext.request.contextPath }/board.do?board_no=31">게시글&댓글 전체출력, 작성자 이름, 이메일 출력</a></h3>
	
	
	
	
</body>
</html>