<%@page import="com.btc.member.model.dto.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
<% List<Member> members=(List)request.getAttribute("members"); %>
                    <!-- 컨테츠 -->
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
                                    <div class="row pt-md-5 mt-md-3 mb-2">
                                        <h2 class="text-center">회원리스트</h2>
                                        <div id="select-line" class="float-end">
                                            <form>
                                                <div class="search-area d-flex justify-content-end mt-2">
                                                    <select name="notice-search">
                                                        <option value="user-name">이름</option>
                                                        <option value="user-nickname">닉네임</option>
                                                        <option value="user-email">이메일</option>
                                                        <option value="user-phone">전화번호</option>
                                                    </select>
                                                    <input type="text" name="keyword" placeholder="검색어를 입력해 주세요" class="ms-1">
                                                    <button type="button" class="btn btn-primary btn-sm ms-1">검색</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <table class="table text-center">
                                        <thead>
                                            <th>선택</th>
                                            <th>No</th>
                                            <th>이름</th>
                                            <th>닉네임</th>
                                            <th>이메일</th>
                                            <th>연락처</th>
                                          
                                        </thead>
                                        <tbody class="align-middle">
                                        <%if(members.isEmpty()){ %>
                                        	<tr>
                                        		<td colspan=6>회원이 없습니다.</td>
                                        	</tr>
                                        <%}else{ 
                                        	for(Member m:members){%>
                                            <tr>
                                                <td>
                                                    <input type="radio" name="choice" value="<%=m.getMemberNo()%>">
                                                </td>
                                                <td><%=m.getMemberNo() %></td>
                                                <td><%=m.getMemberName() %></td>
                                                <td><%=m.getNickName() %></td>
                                                <td><%=m.getEmail() %></td>
                                                <td><%=m.getPhone() %></td>
                                            </tr>
                                           <%} }%>
                                        </tbody>
                                    </table>
                                    <!-- 관리자 버튼 -->
                                    <div class="d-flex justify-content-end">
                                        <button type="button" class="btn btn-dark me-2">블랙처리</button>
                                        <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#cancelMember">회원탈퇴</button>
                                    </div>
                                    <!-- 페이지바 -->
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination justify-content-center">
                                            <%=request.getAttribute("pageBar") %>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </section>
                    
                    <div class="modal fade" id="cancelMember"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">회원탈퇴 확인</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<p>선택한 회원을 정말 탈퇴시키겠습니까?</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">닫기</button>
											<button type="button" class="btn btn-primary"
												onclick="checkCancelMember();" data-bs-dismiss="modal">확인</button>
										</div>

									</div>
								</div>
							</div>

	<script>
		const checkCancelMember=()=>{
			const cancelMember=$("input[name=choice]:checked").val();
			console.log(cancelMember);
			$.ajax({
				url:"<%=request.getContextPath()%>/admin/member/cancelMember.do",
				type:"get",
				data:{
					cancelM:cancelMember,
					
				},
				success:function(data){
					if(data==1){
						alert("삭제성공");
					}else{
						alert("실패");
					}
				},error:function(){
					alert("실패ㅠ했습니다");
				}
			});
		};
	</script>
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
<%@ include file="/views/admin/common/footer.jsp"%>