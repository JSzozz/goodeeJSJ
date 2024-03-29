<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Arrays" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>


<section id="content">
	<div id="demo-container">
		<form id="devFrm" method="post" action="${path }/demo/update.do">
			<div class="form-group row">
			<label for="devNo" class="col-sm-2 col-form-label">번호</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="devNo" name="devNo" readonly
				<c:if test="${not empty demo.devNo }">
					value="${demo.devNo }"
				</c:if>				
				>
			</div>
			</div>
			<div class="form-group row">
			<label for="devName" class="col-sm-2 col-form-label">이름</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="devName" name="devName" 
				<c:if test="${not empty demo.devName }">
					value="${demo.devName }"
				</c:if>				
				>
			</div>
			</div>
			<div class="form-group row">
			<label for="devAge" class="col-sm-2 col-form-label">나이</label>
			<div class="col-sm-10">
				<input type="number" class="form-control" id="devAge" name="devAge"
				<c:if test="${not empty demo.devAge }">
					value="${demo.devAge }"
				</c:if>	
				>
			</div>
			</div>
			<div class="form-group row">
			<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="devEmail" name="devEmail"
				<c:if test="${not empty demo.devEmail }">
					value="${demo.devEmail }"
				</c:if>	
				>
			</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="devGender" id="devGender0" value="M"
					${demo.devGender=='M'?"checked":""} >
					<label class="form-check-label" for="devGender0" >남</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="devGender" id="devGender1" value="F"
					${demo.devGender=='F'?"checked":""} >
					<label class="form-check-label" for="devGender1">여</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">개발언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="devLang0" value="Java"
					${demo.devLang.stream().anyMatch(e->e=="Java").get()?"checked":"" }>
					<label class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="devLang1" value="C"
					${demo.devLang.stream().anyMatch(e->e=="C").get()?"checked":"" }>
					<label class="form-check-label" for="devLang1">C</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="devLang2" value="Javascript"
					${demo.devLang.stream().anyMatch(e->e=="Javascript").get()?"checked":"" }>
					<label class="form-check-label" for="devLang2">Javascript</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label for="birthDay" class="col-sm-2 col-form-label">생년월일</label>
				<div class="col-sm-10">
					<input type="date" class="form-control" id="birthDay" name="birthDay">
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="submit" class="col-sm-12 btn btn-outline-primary">
						수정하기
					</button>
				</div>
			</div>					
		</form>
	</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<script>
	const requestSend=(url)=>{
		$("#devFrm").attr("action","${path}/"+url);
		$("#devFrm").submit();
	}
	
/* 	if('${demo.devGender}'!=null&&'${demo.devGender}'=='M'){
		$("#devGender0").attr("checked", true);
	}
	if('${demo.devGender}'!=null&&'${demo.devGender}'=='F'){
		$("#devGender1").attr("checked", true);
	} */	

</script>
<style>
div#demo-container{
		width:50%;
		padding:15px;
		margin:0 auto;
		border:1px solid lightgray;
		border-radius:10px;
	}
</style>