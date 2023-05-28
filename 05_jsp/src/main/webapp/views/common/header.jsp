<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String headerData="header선언데이터";
	/* include한 다른 jsp파일에서도 ㄴ변수를 사용 가능하다 */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
<!-- jquery 불러오는 선언을 여기 한 번만 선언해도 된다!  -->
<style>
	header>nav>ul{
		display:flex;
		list-style:none;
		justify-content:space-between;
	}
	li>a{
		font-size:30px;
		font-weight:bolder;
		text-decoration:none;
		color:black;
	}
	section{
		height:500px;
		width=100%;
	}
</style>

</head>
<body>



	<header>
		<h1>sample header</h1>
		<nav>
			<ul>
				<li><a href="<%=request.getContextPath()%>/views/main.jsp">메인화면</a></li>
				<li><a href="">공지사항</a></li>
				<li><a href="<%=request.getContextPath()%>/views/board.jsp">게시판</a></li>
				<li><a href="">자료실</a></li>
				<li><a href="">추가메뉴</a></li>
			</ul>
		</nav>
	</header>
</body>
</html>