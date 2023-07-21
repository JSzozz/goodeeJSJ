<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boot메인화면</title>
</head>
<body>
	<h2>나의 첫 boot 화면</h2>
	<h3><a href="${pageContext.request.contextPath }/member/memberAll">전체회원조회</a></h3>
	<form action="${pageContext.request.contextPath }/fileUpload"
	method="post" enctype="multipart/form-data">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="submit" Value="파일저장">
	</form>
	
	<form action="${pageContext.request.contextPath }/datatest" method="post">
		<input type="text" name="data">
		<input type="submit" value="전송">
	</form>
	
	<!-- 아이디로 조회 -->
	<form action="${pageContext.request.contextPath }/memberId" method="post">
		<input type="text" name="userId">
		<input type="submit" value="아이디조회">
	</form>
</body>
</html>