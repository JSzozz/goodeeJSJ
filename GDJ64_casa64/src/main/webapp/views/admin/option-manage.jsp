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
																		</div>
																		<ul class="nav justify-content-center">
																			<li class="nav-item">
																				<h4>
																					<button type="button" class="btn btn-outline-dark me-4 btn-lg">무료옵션</button>
																				</h4>
																			</li>
																			<li class="nav-item">
																				<h4>
																					<button type="button" class="btn btn-outline-dark btn-lg">유료옵션</button>
																				</h4>
																			</li>
																		</ul>
                                    <div class="col-12">
                                      <form action="#" class="mt-3">
                                      <!-- 테이블 -->
                                      <table class="table text-center mt-5">
                                        <thead>
                                            <th>옵션명</th>
                                            <th>옵션관리</th>
                                        </thead>
                                        <tbody id="seasonTableBody">
                                        </tbody>
                                      </table>                                      
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
<script type="text/javascript" src="<%=request.getContextPath()%>/nara_publish/js/nara.js"></script>
<%@ include file="/views/admin/common/footer.jsp"%>