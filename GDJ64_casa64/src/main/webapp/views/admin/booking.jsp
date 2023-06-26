<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
                    <!-- 상세조회 모달 -->
                    <div class="modal fade" id="bookingModal" tabindex="-1" aria-labelledby="bookingModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="bookingModalLabel">정보조회</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form>
                                <div class="mb-3">
                                    <span id="reservationState"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label"><strong>예약번호&nbsp;</strong></label>
                                    <span id="reservationNo"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label"><strong>결제날짜&nbsp;</strong></label>
                                    <span id="reservationPaymentDate"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label"><strong>예약자명&nbsp;</strong></label>
                                    <span id="reservation"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label"><strong>이메일&nbsp;</strong></label>
                                    <span id="reservationEmail"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label"><strong>전화번호&nbsp;</strong></label>
                                    <span id="reservationPhone"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label"><strong>예약기간&nbsp;</strong></label>
                                    <span id="reservationDate"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label"><strong>예약인원&nbsp;</strong></label>
                                    <span id="reservationPerson"></span>
                                </div>
                                <div class="mb-3">
                                    <label for="recipient-name" class="col-form-label"><strong>요청사항&nbsp;</strong></label>
                                    <textarea id="reservationComment" class="form-control" rows="3" readonly></textarea>
                                </div>
                                <div class="mb-3">
                                    <div>
                                        <table class="table text-center">
                                            <thead>
                                                <th colspan="2">결제금액</th>
                                            </thead>
                                            <tbody>
                                                <td id="reservationPayment" colspan="2"></td>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" id="isBookingCancelBtn" class="btn btn-danger d-none" data-bs-toggle="modal" data-bs-target="#bookingCancelModal">예약취소</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                            </div>
                            </div>
                        </div>
                    </div>

                    <!-- 결제취소 -->
                    <div class="modal fade" id="bookingCancelModal" tabindex="-1" aria-labelledby="bookingCancelModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="bookingCancelModalLabel">경고</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                정말로 예약을 취소합니까?
                            </div>
                            <div class="modal-footer">
                                <button type="button" id="cancelBookingBtn" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                            </div>
                            </div>
                        </div>
                    </div>           

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
                                        <div class="row mt-3 align-items-center">
                                          <label class="col-sm-1 col-form-label text-center"><strong>조회기간</strong></label>
                                          <div class="col-sm-9">
                                            <button type="button" onclick="ajaxBooking('<%=request.getContextPath()%>/admin/booking/todayBooking.do')" class="btn btn-outline-dark dateSearchBtns">오늘</button>
                                            <button type="button" onclick="ajaxBooking('<%=request.getContextPath()%>/admin/booking/oneWeekAndMonthBooking.do', '', '' ,'week')" class="btn btn-outline-dark dateSearchBtns">1주일</button>
                                            <button type="button" onclick="ajaxBooking('<%=request.getContextPath()%>/admin/booking/oneWeekAndMonthBooking.do', '', '' ,'month')" class="btn btn-outline-dark dateSearchBtns">1개월</button>
                                            <!-- <button type="button" class="btn btn-outline-dark">직접설정</button> -->
                                          </div>
                                        </div>
                                        <!-- 조건검색 -->                                 
                                        <div class="form-group row mt-3 align-items-center">
                                            <label class="col-sm-1 col-form-label text-center"><strong>조건검색</strong></label>
                                            <div class="col-sm-6 d-flex">
                                                <select id="selectState" class="form-control me-1" style="width:100px">
                                                    <option value="전체">전체</option>
                                                    <option value="취소요청">취소요청</option>
                                                    <option value="결제완료">결제완료</option>
                                                    <option value="이용완료">이용완료</option>
                                                </select>
                                                <select id="selectType" class="form-control me-1" style="width:100px">
                                                    <option value="user-name">예약자</option>
                                                    <option value="room-name">객실명</option>
                                                    <option value="user-email">이메일</option>
                                                    <option value="user-phone">전화번호</option>
                                                </select>
                                                <input hidden="hidden">
                                                <input type="text" name="keyword" placeholder="검색어를 입력해 주세요" id="selectInput" class="form-control me-1" >
                                                <button type="button" id="searchSelectBtn" class="btn btn-dark btn-sm" onclick="searchBooking('<%=request.getContextPath()%>/admin/booking/searchBooking.do')" style="width:70px">검색</button>
                                            </div>
                                        </div>
                                        <!-- 상태조회 -->
                                        <div class="form-group row mt-3 align-items-center text-center">
                                            <ul id="bookingTabs" class="nav d-flex justify-content-evenly mt-2 mb-4">
                                                <li class="nav-item list-group-item-light">
                                                    <button type="button" class="btn btn-default tabBtns" onclick="ajaxBooking('<%=request.getContextPath()%>/admin/booking/showAllBooking.do')">전체</button>
                                                </li>
                                                <li class="nav-item">
                                                    <button type="button" class="btn btn-default tabBtns" onclick="ajaxBooking('<%=request.getContextPath()%>/admin/booking/conditionBooking.do', '취소요청')">취소요청</button>
                                                </li>
                                                <li class="nav-item">
                                                    <button type="button" class="btn btn-default tabBtns" onclick="ajaxBooking('<%=request.getContextPath()%>/admin/booking/conditionBooking.do', '결제완료')">결제완료</button>
                                                </li>
                                                <li class="nav-item">
                                                    <button type="button" class="btn btn-default tabBtns" onclick="ajaxBooking('<%=request.getContextPath()%>/admin/booking/conditionBooking.do', '이용완료')">이용완료</button>
                                                </li>
                                            </ul>
                                        </div>                          
                                      </form>
                                      <!-- 예약상태 -->
                                      <div id="result">
                                        <table class="table text-center">
                                            <thead>
                                                <th class="col-1">예약번호</th>
                                                <th class="col-2">상태</th>
                                                <th class="col-2">객실명</th>
                                                <th class="col-1">예약자</th>
                                                <th class="col-2">체크인</th>
                                                <th class="col-2">체크아웃</th>
                                                <th class="col-2">관리</th>
                                            </thead>
                                            <tbody id="resultTable" class="align-middle">
                                            </tbody>
                                        </table>
                                        <!-- 페이징 -->
                                        <nav id="pagingBar" aria-label="Page navigation example">
                                            <ul id="pagination" class="pagination justify-content-center"></ul>
                                        </nav>
                                      </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
<script>
let checkBookingURL = '<%=request.getContextPath()%>/admin/booking/infoBooking.do';
let cancelBookingURL = '<%=request.getContextPath()%>/admin/booking/cancelBooking.do'
</script>
<script src="<%=request.getContextPath()%>/js/dj/admin-booking.js"></script>
<%@ include file="/views/admin/common/footer.jsp"%>