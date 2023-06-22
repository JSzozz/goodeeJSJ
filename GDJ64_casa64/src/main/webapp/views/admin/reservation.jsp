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
                                            <ul id="reservationTabs" class="nav nav-tabs d-flex text-center justify-content-evenly mt-2">
                                                <li class="nav-item">
                                                    <span class="nav-link active">전체</span>
                                                </li>
                                                <li class="nav-item">
                                                    <span class="nav-link">결제취소요청</span>
                                                </li>
                                                <li class="nav-item">
                                                    <span class="nav-link">예약완료</span>
                                                </li>
                                                <li class="nav-item">
                                                    <span class="nav-link" onclick='completeUse("<%=request.getContextPath()%>/completeUseRoom.do")'>이용완료</span>
                                                </li>
                                            </ul>
                                        </div>                          
                                      </form>
                                      <!-- 예약상태 -->
                                      <div class="col-sm-12 p-2">
                                          <div class="card card-reservation">
                                              <div id="cardBody" class="card-body">
                                                <!--  내용부분 -->
                                                  <p>결제대기</p>
                                                  <div class="d-flex text-center">
                                                      <div class="col-sm-4">
                                                          <img src="#" alt="객실사진" class="img-fluid">
                                                          <div class="mt-2 fs-5">오션뷰 테라스</div>
                                                          <div class="mt-2 bg-light">옵션: 바베큐, 온수</div>
                                                      </div>
                                                      <div class="col-sm-7 ms-5">
                                                        <div><strong>예약자명:&nbsp;&nbsp;</strong>
                                                            <span>이동제</span>
                                                        </div>
                                                        <div class="mt-2"><strong>이메일:&nbsp;&nbsp;</strong>
                                                            <span>abcd@naver.com</span>
                                                        </div>
                                                        <div class="mt-2"><strong>휴대번호:&nbsp;&nbsp;</strong>
                                                            <span>010-0000-0000</span>
                                                        </div>
                                                        <div class="mt-2"><strong>예약기간:&nbsp;&nbsp;</strong>
                                                            <span>23/06/20</span><span>&#126;</span><span>23/06/23</span>
                                                        </div>
                                                        <div>
                                                            <table class="table">
                                                                <thead>
                                                                    <th>결제방법</th>
                                                                    <th>결제금액</th>
                                                                </thead>
                                                                <tbody>
                                                                    <td>계좌이체</td>
                                                                    <td>200,000원</td>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <div class="d-grid justify-content-center">
                                                            <button type="button" class="btn btn-danger mt-2" style="width: 250px;">결제취소</button>
                                                        </div>
                                                      </div>
                                                  </div>
                                                  <!-- 내용 끝 -->
                                              </div>
                                          </div>
                                        </div>                          
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
<script src="<%=request.getContextPath()%>/js/dj/admin-reservation.js"></script>
<%@ include file="/views/admin/common/footer.jsp"%>