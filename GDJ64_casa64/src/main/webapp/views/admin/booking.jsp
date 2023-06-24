<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
                    <!-- 컨테츠 -->
                    <section>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
                                    <div class="row pt-md-5 mt-md-3 mb-4">
                                        <h2 class="text-center">예약관리</h2>
                                    </div>
                                    <div class="col-12">
                                      <form action="#" class="mt-3">
                                        <!-- 기간설정 -->
                                        <div class="form-group row mt-3 align-items-center">
                                          <label for="roomInfo" class="col-sm-1 col-form-label text-center"><strong>조회기간</strong></label>
                                          <div class="col-sm-9">
                                            <button type="button" class="btn btn-outline-dark">오늘</button>
                                            <button type="button" class="btn btn-outline-dark">1주일</button>
                                            <button type="button" class="btn btn-outline-dark">1개월</button>
                                            <button type="button" class="btn btn-outline-dark">직접설정</button>
                                          </div>
                                        </div>
                                        <!-- 조건검색 -->                                 
                                        <div class="form-group row mt-3 align-items-center">
                                            <label for="roomInfo" class="col-sm-1 col-form-label text-center"><strong>조건검색</strong></label>
                                            <div class="col-sm-5 d-flex">
                                                <select class="form-control me-1" style="width:100px">
                                                    <option value="user-name">이름</option>
                                                    <option value="room-name">객실명</option>
                                                    <option value="user-email">이메일</option>
                                                    <option value="user-phone">전화번호</option>
                                                </select>
                                                <input type="text" name="keyword" placeholder="검색어를 입력해 주세요" class="form-control me-1">
                                                <button type="button" class="btn btn-primary btn-sm form-control" style="width:70px">검색</button>
                                            </div>
                                        </div>
                                        <!-- 상태조회 -->
                                        <div class="form-group row mt-3 align-items-center text-center">
                                            <ul id="bookingTabs" class="nav d-flex justify-content-evenly mt-2 mb-4">
                                                <li class="nav-item list-group-item-light">
                                                    <button type="button" class="btn btn-default" onclick="showAllBooking('<%=request.getContextPath()%>/admin/booking/showAllBooking.do')">전체</button>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="#">결제취소요청</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="#">예약완료</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="#">이용완료</a>
                                                </li>
                                            </ul>
                                        </div>                          
                                      </form>
                                      <!-- 예약상태 -->
                                      <div id="result">
                                    <table class="table text-center">
                                        <thead>
                                            <th>상태</th>
                                            <th>객실명</th>
                                            <th>예약자</th>
                                            <th>전화번호</th>
                                            <th>체크인</th>
                                            <th>체크아웃</th>
                                            <th>관리</th>
                                        </thead>
                                        <tbody class="align-middle">
                                            <tr>
                                                <td>예약완료</td>
                                                <td>오션뷰 테라스</td>
                                                <td>이동제</td>
                                                <td>010-1234-4321</td>
                                                <td>23/06/17</td>
                                                <td>23/06/19</td>
                                                <td>상세조회</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                      </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
<script src="<%=request.getContextPath()%>/js/dj/admin-booking.js"></script>
<%@ include file="/views/admin/common/footer.jsp"%>