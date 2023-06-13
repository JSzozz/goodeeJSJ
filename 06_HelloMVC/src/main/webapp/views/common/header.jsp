<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.model.dto.Member" %>

<%
/* (*LoginfrmServlet)(로그인 기능)client-DB 유효성 검사 */
	Member loginMember=(Member)session.getAttribute("loginMember");
	
	/* (*LoginfrmServlet)(로그인 기능)아이디 저장 */
	Cookie[] cookies=request.getCookies();
	String saveId=null;
	if(cookies!=null){
		for(Cookie c : cookies)
		{
	if(c.getName().equals("saveId"))
	{
		saveId=c.getValue();
		break;
	}
	/* else
	{
		saveId="";
	} */
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMVC</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script src="<%=request.getContextPath() %>/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<a href="<%=request.getContextPath() %>" style="text-decoration-line: none; color:blue;"><h1> Hello MVC </h1></a>
			
			<div class="login-container">
			 <%
			if(loginMember==null){
			%> 
				<form id="loginFrm" action="<%=request.getContextPath()%>/loginfrm.do" method="post"
				 onsubmit="return fn_validation();"> 
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" 
								placeholder="아이디 입력" value="<%=saveId!=null?saveId:""%>">
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="패스워드 입력">
							</td>
							<td>
								<input type="submit" value="로그인">
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId" <%=saveId!=null?"checked":"" %>>
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입" onclick="location.assign('<%=request.getContextPath() %>/member/enrollMember.do')">
								<!--  assign 은 다음 페이지로 이동하면서 현재 페이지를 히스토리에 남기고 replace 는 남기지 않습니다.
   									  이는 브라우저의 뒤로가기를 눌렀을 때 그 차이를 확실히 알 수 있습니다. -->
							</td>
						</tr>
					</table>
				</form>
				<%} else{%>
					<table id="logged-in">
						<tr>
							<td colspan="2">
								<%=loginMember.getUserName() %>님 환영합니다.
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" onclick="location.assign('<%=request.getContextPath() %>/member/memberView.do?userId=<%=loginMember.getUserId()%>')" value="내 정보보기">
							</td>
							<td>
								<input type="submit" value="로그아웃" onclick="location.replace('<%=request.getContextPath() %>/logout.do')">
							</td>
						</tr>
					</table>
				<%} %>
			</div>
			
			
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="">home</a>
					<li id="notice"><a href="<%=request.getContextPath()%>/notice/noticeList.do">공지사항</a>
					<li id="board"><a href="<%=request.getContextPath()%>/board/boardList.do">게시판</a>
					<%
					if(loginMember!=null&&loginMember.getUserId().equals("admin")){
					%>
					<li id="memberManage"><a href="<%=request.getContextPath() %>/admin/memberList.do">회원관리</a>
					<%} %>
				</ul>
			</nav>
		</header>


		
		<script>
			const fn_validation=()=>{ /* form태그 logonFrm의 onsubmit */
				/* alert("제출함"); */
				const userId=$("#userId").val();
				if(userId.length<4){
					alert("아이디는 4글자 이상입니다.")
					$("#userId").val("");
					/* 공간 비우기 : 안 비우면 값이 남아있는 상태임*/
					$("#userId").focus();
					return false; 
					/* form실행을 막음 */
				}
				if($("#password").val().length<3){
					return false;
				}
			}

		</script>
	