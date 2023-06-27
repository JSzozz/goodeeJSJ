<%@page import="com.btc.member.model.dto.BlackMember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>

<%
List<BlackMember> members = (List) request.getAttribute("members");
String type = (String) request.getAttribute("type");
String search = (String) request.getAttribute("search");
%>
<!-- 컨테츠 -->
<section>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
				<div class="row pt-md-5 mt-md-3 mb-2">
					<h2 class="text-center">블랙회원리스트</h2>
					<div id="select-line" class="float-end">
						<form action="<%=request.getContextPath()%>/admin/searchBMember.do">
							<div class="search-area d-flex justify-content-end mt-2">
								<select name="member-search">
									<option value="member_name"
										<%=type != null && type.equals("member_name") ? "selected" : ""%>>이름</option>
									<option value="nickname"
										<%=type != null && type.equals("nickname") ? "selected" : ""%>>닉네임</option>
									<option value="email"
										<%=type != null && type.equals("email") ? "selected" : ""%>>이메일</option>
									<option value="phone"
										<%=type != null && type.equals("phone") ? "selected" : ""%>>전화번호</option>
								</select> <input type="text" name="keyword" placeholder="검색어를 입력해 주세요"
									class="ms-1" value="<%=search != null ? search : ""%>">
								<button type="summit" class="btn btn-primary btn-sm ms-1">검색</button>
							</div>
						</form>
					</div>
				</div>
				<table class="table text-center">
					<thead>
					<tr>
						<th>No</th>
						<th>닉네임</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>블랙날짜</th>
						<th>사유</th>
						<th>비고</th>
					</tr>
					</thead>
					<tbody class="align-middle">
					<%if (members.isEmpty()) {%>
						<tr>
							<td colspan="7">블랙된 회원이 없습니다</td>
						</tr>
					<%}else{
						for(BlackMember m:members){%>
						<tr>
							<td><%=m.getMemberNo() %></td>
							<td><%=m.getNickName() %></td>
							<td><%=m.getEmail() %></td>
							<td><%=m.getPhone() %></td>
							<td><%=m.getBlackDate() %></td>
							<td>
								<button type="button" class="btn btn-dark btn-sm" onclick="memberInfo();" name="info" value="<%=m.getMemberNo()%>">상세조회</button>
							</td>
							<td>
								<button type="button" class="btn btn-dark btn-sm" data-bs-toggle="modal" data-bs-target="#cancelBlackMember" name="info2" value="<%=m.getMemberNo()%>">블랙해제</button>
							</td>
						</tr>
						<%} }%>
					
					</tbody>
				</table>
				<!-- 페이지바 -->
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<%=request.getAttribute("pageBar") %>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	
						<div class="modal fade" id="blackMemberInfo"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">블랙회원 상세정보</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											
											
										</div>
										<div class="modal-footer">
											
											<button type="button" class="btn btn-dark" data-bs-dismiss="modal">확인</button>
										</div>

									</div>
								</div>
							</div>
							
							<div class="modal fade" id="cancelBlackMember"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">블랙회원 해제확인</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<p>정말 블랙을 해제하겠습니까?</p>
											
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-dark" data-bs-dismiss="modal">취소</button>
											<button type="button" class="btn btn-dark" data-bs-dismiss="modal" onclick="cancelBlack();">확인</button>
										</div>

									</div>
								</div>
							</div>
							
							
</section>
<script>
	const memberInfo=()=>{
		const nov=$("button[name=info]").val();
		
		$.ajax({
			url: "<%=request.getContextPath()%>/admin/member/blackView.do",
			type:"post",
			data:{
				memberNo:nov
			},
			success:function(data){
				var obj=JSON.parse(data);
					$(".modal-body").html("");
					
					$(".modal-body").append("<img src='<%=request.getContextPath()%>/upload/member/"+obj.imgs+"' width='400' height='300'><br><br>");
					$(".modal-body").append(obj.reason+"<br>");
				
				
			},error:function(){
				alert("서버통신실패");
			}
		});
		$("#blackMemberInfo").modal("show");
		
	};
	
	const cancelBlack=()=>{
		const nov=$("button[name=info2]").val();
		$.ajax({
			url: "<%=request.getContextPath()%>/admin/member/cancelblack.do",
			type:"post",
			data:{
				memberNo:nov
			},
			success:function(data){
				if(data==1){
					alert("블랙해제성공");
					location.replace("<%=request.getContextPath()%>/admin/member/blackList.do");
				}else{
					alert("해제실패");
				}
				
				
			},error:function(){
				alert("서버통신실패");
			}
		});
		
	};
	
</script>





<%@ include file="/views/admin/common/footer.jsp"%>