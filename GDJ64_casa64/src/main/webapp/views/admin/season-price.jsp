<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
                    <!-- 컨테츠 -->
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
                                    <div class="row pt-md-5 mt-md-3 mb-4">
                                        <h2 class="text-center">요금관리</h2>
                                    </div>
                                    <div class="col-12">
                                      <form action="#" class="mt-3">
                                      <!-- 테이블 -->
                                      <table class="table text-center mt-5">
                                        <thead>
                                            <th>이름</th>
                                            <th>평일요금</th>
                                            <th>주말요금</th>
                                            <th>시작시즌</th>
                                            <th>종료시즌</th>
                                            <th>삭제</th>
                                        </thead>
                                        <tbody id="seasonTableBody">
                                            <tr>
                                                <td colspan="6">
                                                    <button type="button" id="insertSeasonBtn" class="btn btn-dark">+ 시즌추가</button>
                                                </td>
                                            </tr>
                                        </tbody>
                                      </table>                                      
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
<script src="<%=request.getContextPath()%>/js/dj/season-price.js"></script>
<script>
	$("#insertRowBtn").click(e=>{
		console.log($(".seaason-name"));
	});
</script>
<%@ include file="/views/admin/common/footer.jsp"%>