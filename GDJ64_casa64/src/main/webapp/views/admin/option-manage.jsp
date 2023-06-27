<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
<%@ page import="com.btc.rooms.model.vo.*,java.util.List" %>
<%

	List<OptionFree> frees=(List<OptionFree>)request.getAttribute("frees");
	List<OptionXtra> xtras=(List<OptionXtra>)request.getAttribute("xtras");

%>
<section>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
				<div class="row pt-md-5 mt-md-3 mb-4">
					<h2 class="text-center">옵션관리</h2>
					<div class="category-tab">
						<ul>
							<li><a href="<%=request.getContextPath()%>/notice/insertNotice.do"
								class="category-tab-a category-tab-notice">기본옵션</a></li>
							<li><a href="<%=request.getContextPath()%>/qna/insertQna.do"
								class="category-tab-a category-tab-qna">유료옵션</a></li>
						</ul>
					</div>
					
</section>
<script type="text/javascript" src="<%=request.getContextPath()%>/nara_publish/js/nara.js"></script>
<%@ include file="/views/admin/common/footer.jsp"%>