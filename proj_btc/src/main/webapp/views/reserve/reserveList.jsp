<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   	String type=request.getParameter("searchType");
   	String keyword=request.getParameter("searchKeyword");
%>
<script src="<%=request.getContextPath()%>/js/reserve.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reserve.css">

<%@ include file="/views/common/header.jsp"%>

<section>
	<h3>COSA64 펜션 객실 예약</h3>
	
	<div id="numPerpage-container">
        	<%-- <form id="numPerFrm" action="<%=request.getContextPath()%>/admin/memberList.do"> --%>
        	
        		<select class="form-select" >
					<option selected>객실이용인원</option>
        			<option value="10">최대 2인</option>
        			<option value="5" >최대 4인</option>
        			<option value="3" >최대 8인</option>
        		</select>
        	<!-- </form> -->
    </div>
    
	<div id="search-container" style="background-color: #f3f3da;">
		객실 조회하기 : 
		<select id="searchType">
			<option value="filter"
				<%=type!=null&&type.equals("gender")?"selected":""%>>객실 뷰</option>
			<option value="filter2"
				<%=type!=null&&type.equals("gender")?"selected":""%>>객실 옵션</option>
			<option value="filter3"
			<%=type!=null&&type.equals("gender")?"selected":""%>>객실 옵션2</option>
		</select>
		<div id="search-filter">
			<form action="<%=request.getContextPath()%>/admin/searchMember">
				<input type="hidden" name="searchType" value="gender"> 
				<label>
					<input type="radio" name="searchKeyword" value="F" 
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("F")?"checked":""%>>오션뷰
				</label>
				<label>
					<input type="radio" name="searchKeyword" value="M"
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("M")?"checked":""%>>시티뷰
				</label>
				<label>
					<input type="radio" name="searchKeyword" value="M" 
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("M")?"checked":""%>>오션뷰&시티뷰
				</label>				
				<button type="submit" class="btn btn-dark">검색</button>
			</form>
		</div>
		<div id="search-filter2">
			<form action="<%=request.getContextPath()%>/admin/searchMember">
				<input type="hidden" name="searchType" value="gender"> 
				<label>
					<input type="checkbox" name="searchKeyword" value="F" 
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("F")?"checked":""%>>프라이빗 풀
				</label>
				<label>
					<input type="checkbox" name="searchKeyword" value="M" 
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("M")?"checked":""%>>자쿠지
				</label>
				<label>
					<input type="checkbox" name="searchKeyword" value="M" 
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("M")?"checked":""%>>테라스
				</label>				
				<button type="submit" class="btn btn-dark">검색</button>
			</form>
		</div>
		<div id="search-filter3">
			<form action="<%=request.getContextPath()%>/admin/searchMember">
				<input type="hidden" name="searchType" value="gender"> 
				<label>
					<input type="checkbox" name="searchKeyword" value="F"
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("F")?"checked":""%>>빔 프로젝터
				</label>
				<label>
					<input type="checkbox" name="searchKeyword" value="M"
					<%=type!=null&&type.equals("gender")&&keyword!=null&&keyword.equals("M")?"checked":""%>>노래방
				</label>				
				<button type="submit" class="btn btn-dark">검색</button>
			</form>
		</div>
	</div>
	
	<div class="reserve_step1">
		<table class="scriptCalendar table">
			<thead style="background-color: #f3f3da;">
				<tr>
					<td class="calendarBtn" id="btnPrevCalendar">&#60;&#60;</td>
					<td colspan="5">
						<span id="calYear">YYYY</span>년 
						<span id="calMonth">MM</span>월
					</td>
					<td class="calendarBtn" id="nextNextCalendar">&#62;&#62;</td>
				</tr>
				<tr style="background-color: #f3f3da;">
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
	<div class="reserve_step2">
		<h1>Hi</h1>
		<h1>Hi</h1>	
		<h1>Hi</h1>
	</div>
</section>

<script>
	$("#searchType").change(e=>{
 	/* alert("이벤트발생"); */
 	const type=$(e.target).val();
 	console.log(type);
 	$(e.target).parent().find("div").css("display","none");
 	$("#search-"+type).css("display","inline-block");
	});

	$(document).ready(function () {
 	 $("div[id='room']").click(function () {
  	  $(".reserve_step2").show();
 	 });
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
<%--  <%@ include file="/views/common/footer.jsp"%>  --%> 
<!-- footer바닥 고정법 찾아보기 -->