<%@page import="java.util.List"%>
<%@page import="com.btc.member.model.dto.CancelMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
<% 
	List<CancelMember> members=(List)request.getAttribute("members");
	String type=(String)request.getAttribute("type");
	String search=(String)request.getAttribute("search");
%>

                    <!-- 컨테츠 -->
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
                                    <div class="row pt-md-5 mt-md-3 mb-2">
                                        <h2 class="text-center">탈퇴회원리스트</h2>
                                        <div id="select-line" class="float-end">
                                            <form action="<%=request.getContextPath()%>/admin/searchCMember.do">
                                                <div class="search-area d-flex justify-content-end mt-2">
                                                    <select name="member-search">
                                                        <option value="member_name" <%=type!=null&&type.equals("member_name")?"selected":"" %>>이름</option>
                                                        <option value="nickname" <%=type!=null&&type.equals("nickname")?"selected":"" %>>닉네임</option>
                                                        <option value="email" <%=type!=null&&type.equals("email")?"selected":"" %>>이메일</option>
                                                        <option value="phone" <%=type!=null&&type.equals("phone")?"selected":"" %>>전화번호</option>
                                                    </select>
                                                    <input type="text" name="keyword" placeholder="검색어를 입력해 주세요" class="ms-1">
                                                    <button type="submit" class="btn btn-primary btn-sm ms-1">검색</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <table class="table text-center">
                                        <thead>
                                            <th>No</th>
                                            <th>이름</th>
                                            <th>닉네임</th>
                                            <th>이메일</th>
                                            <th>연락처</th>                                        
                                            <th>탈퇴날짜</th>
                                        </thead>
                                        <tbody class="align-middle">
                                        <%if(members.isEmpty()){ %>
                                        	<tr>
                                        		<td colspan=6>탈퇴회원이 없습니다</td>
                                   
                                        	</tr>
                                        <%}else{
                                        	for(CancelMember m:members){%>
                                            <tr>
                                                <td><%=m.getMemberNo() %></td>
                                                <td><%=m.getMemberName() %></td>
                                                <td><%=m.getNickName() %></td>
                                                <td><%=m.getEmail() %></td>
                                                <td><%=m.getPhone() %></td>
                                                <td><%=m.getCancelDate() %></td>
                                               
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
                    </section>
<%@ include file="/views/admin/common/footer.jsp"%>