<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   	String type=request.getParameter("searchType");
   	String keyword=request.getParameter("searchKeyword");
%> 
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/reserve.css">
<script src="<%=request.getContextPath()%>/js/reserve.js"></script>

<div>
	<h3>■ JavaScript 캘린더</h3>
	<div id="search-container">
		검색타입 : <select id="searchType">
			<option value="userId"
				<%=type!=null&&type.equals("userId")?"selected":""%>>아이디</option>
			<option value="userName"
				<%=type!=null&&type.equals("userName")?"selected":""%>>회원이름</option>
			<option value="gender"
				<%=type!=null&&type.equals("gender")?"selected":""%>>성별</option>
		</select>
		<div id="search-userId">
			<form action="<%=request.getContextPath()%>/admin/searchMember">
				<input type="hidden" name="searchType" value="userId"> <input
					type="text" name="searchKeyword" size="25"
					placeholder="검색할 아이디를 입력하세요"
					value=<%=type!=null&&type.equals("userId")?keyword:""%>>
				<button type="submit">검색</button>
			</form>
		</div>
		<div id="search-userName">
			<form action="<%=request.getContextPath()%>/admin/searchMember">
				<input type="hidden" name="searchType" value="userName"> <input
					type="text" name="searchKeyword" size="25"
					placeholder="검색할 이름을 입력하세요"
					value=<%=type!=null&&type.equals("userName")?keyword:""%>>
				<button type="submit">검색</button>
			</form>
		</div>
		<div id="search-gender">
			<form action="<%=request.getContextPath()%>/admin/searchMember">
				<input type="hidden" name="searchType" value="gender"> <label><input
					type="radio" name="searchKeyword" value="M"
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("M")?"checked":""%>>남</label>
				<!-- keyword!=null는 로직상 생기지 않는 오류기는함(url입력 상황 예외) -->
				<label><input type="radio" name="searchKeyword" value="F"
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("F")?"checked":""%>>여</label>
				<button type="submit">검색</button>
			</form>
		</div>
	</div>
	<br />
	<table class="scriptCalendar">
		<thead>
			<tr>
				<td class="calendarBtn" id="btnPrevCalendar">&#60;&#60;</td>
				<td colspan="5"><span id="calYear">YYYY</span>년 <span
					id="calMonth">MM</span>월</td>
				<td class="calendarBtn" id="nextNextCalendar">&#62;&#62;</td>
			</tr>
			<tr>
				<td>일</td>
				<td>월</td>
				<td>화</td>
				<td>수</td>
				<td>목</td>
				<td>금</td>
				<td>토</td>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</div>

<script>
	$("#searchType").change(e=>{
 	/* alert("이벤트발생"); */
 	const type=$(e.target).val();
 	console.log(type);
 	$(e.target).parent().find("div").css("display","none");
 	$("#search-"+type).css("display","inline-block");
	});
	
	$(()=>{
		$("#searchType").change();
		$("#numPerpage").change(e=>{
			let url=location.href;
			/* console.log(url.substring(0,url.indexOf("?")+1));  */
			if(url.includes("?")){
 			url=url.substring(0,url.indexOf("?")+1)
 			+'searchType=<%=type%>'
 			+'&searchKeyword=<%=keyword%>'
 			+'&cPage=<%=request.getParameter("cPage") != null ? request.getParameter("cPage") : 1%>'
 			+'&numPerpage='+e.target.value;
 			/* url+='&numPerpage='+e.target.value; */
			}else{
				url+='?';
				url+=+'cPage=<%=request.getParameter("cPage") != null ? request.getParameter("cPage") : 1%>'
 			+'&numPerpage='+e.target.value;
			}
			location.assign(url); 
			console.log(url);
		})
	})//ready함수 : searchKeyword input태그 변동 방지
	
</script>

<%-- <%@ include file="/views/common/footer.jsp"%> --%>