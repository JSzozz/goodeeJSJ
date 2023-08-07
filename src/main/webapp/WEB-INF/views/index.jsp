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
	
	<button onclick="openchatting();">채팅하기</button>
	
	<script>
		function openchatting(){
			open("/chattingpage","_blank","width=400,height=500");
		}
	</script>
	
	<br>
	<button onclick="memberAll();">요청처리하기</button>
	<script>
		const memberAll=()=>{
			fetch("${pageContext.request.contextPath}/ajax/memberAll")
				.then(response=>{
					if(!response.ok) 
						throw new Error("요청에러"); 
					return response.json();}
				)
				.then(data=>{
					console.log(data);
				})
				.catch(error=>{
					console.log(error);
				});
		}
	</script>
	
	<h2>jpa적용하기</h2>
	<h3><a href="${pageContext.request.contextPath }/jpa/member/admin">관리자 조회</a></h3>
	<h3><a href="${pageContext.request.contextPath }/jpa/member/insertMember">회원정보저장</a></h3>
	<h3><a href="${pageContext.request.contextPath }/board">전체 게시글조회(페이징처리추가)</a></h3>
	<h3><a href="${pageContext.request.contextPath }/board?title=안녕">제목으로 게시글조회</a></h3>

</body>
</html>