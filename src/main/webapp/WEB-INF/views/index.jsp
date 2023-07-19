<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메인화면"/>
</jsp:include>    

<section id="content">
	<h2>Hello Spring</h2>
	<h3>ajax통신하기</h3>
	<h4><button class="btn btn-outline-primary" onclick="basicAjax();">기본ajax처리</button></h4>
	<div id="ajaxContainer"></div>
	
	<script>
		const basicAjax=()=>{
			$.get('${pageContext.request.contextPath}/ajax/basicTest.do',
					(data)=>{
					console.log(data);
					$("#ajaxContainer").html("<h2>"+data+"</h2>");
				});
			}
	</script>
	
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>