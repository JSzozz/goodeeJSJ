<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%= request.getAttribute("result")%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복확인</title>

<style>
	div#checkId-container{
		text-align:center;
		padding-top:50px;
	}
	span#duplicated{
		color:red;
		font-weight:bolder;
	}
</style>
</head>
<body>
	<div id="checkId-container">
	<% if(request.getAttribute("result")==null){%>
				[<span><%=request.getParameter("userId") %></span>]는 사용가능합니다.	
				<br><br>
				<button type="button">닫기</button>
	<%}else{%>
				[<span id="duplicated"><%=request.getParameter("userId") %></span>]는 사용중입니다.
	<%}%>
				<br><br>
				<!-- 아이디 재입력창 구성 -->
				<form action="<%=request.getContextPath()%>/member/idDuplicate.do" method="get" >
					<input type="text" name="userId" id="userId">
					<input type="submit" value="중복검사" ">
				</form>
		</div>
	<script>
		const btn=document.querySelector("button[type=button]");
		btn.addEventListener("click",e=>{
			opener().document.querySelector("#UserId_").value="<%=request.getParameter("userId")%>";
			/* v : opener() */
			/* 따옴표넣어주기 - 브라우저, 서버 사이 값이 2번 읽혀지니까 ""도 2번 필요!(어렵지만 해내야지,,!) */
			alert("버튼클릭");
			close();
		})
			
	</script>
		
</body>
</html>