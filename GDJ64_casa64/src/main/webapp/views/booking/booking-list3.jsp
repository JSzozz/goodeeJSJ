<%@page import="com.btc.booking.model.vo.Booking"%>
<%@page import="java.util.List"%>
<%@page import="com.btc.rooms.model.vo.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 상준css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sj/style.css"/>
      <!-- 헤더 영역 시작 -->
<%@ include file="/views/common/header.jsp"%>
      <!-- 헤더 영역 종료 -->
<%!
	public Room room;
%>
<%
	Booking booking=(Booking)request.getAttribute("booking");
	List<Room> roomList = (List<Room>)session.getAttribute("rooms");
		
	for(Room r : roomList) {
		if(r.getRoomNo() == booking.getRoom().getRoomNo()) {
			room = r;
		}
	}
%>

<%-- <%=booking %><br>
 --%>
<!-- 결제 API -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<!-- 동제 JS -->
<script src="<%=request.getContextPath()%>/js/dj/payment.js"></script>
      <!-- 카테고리별 이미지 -->
      <div class="category-image">
         <img src="<%=request.getContextPath() %>/images/booking/reservation.png" width="100%" height="200px">
         <div>RESERVATION</div>
      </div>
      
<br><br><br>
      
   <!-- 컨텐츠/내용 시작 -->
      <div class="reserve_step4 offset-md-1 col-md-10">
           <form name="bookFrm" id="paymentForm" action="<%=request.getContextPath()%>/booking/bookingList3ToList4.do" method="post">
	           <input type="hidden" name="memberNo" value="<%=loginMember.getMemberNo()%>">
	           <input type="hidden" name="roomNo" value="<%=booking.getRoom().getRoomNo()%>">
	           <input type="hidden" name="checkin" value="<%=booking.getCheckIn()%>">
	           <input type="hidden" name="checkout" value="<%=booking.getCheckOut()%>">
	           <input type="hidden" name="guestAdult" value="<%=booking.getGuestAdult()%>">
	           <input type="hidden" name="guestChild" value="<%=booking.getGuestChild()%>">
	           <input type="hidden" name="guestInfant" value="<%=booking.getGuestInfant()%>">
	           <input type="hidden" name="bookingPrice" value="<%=booking.getBookingPrice()%>">
	           <input type="hidden" name="bookingComment" value="<%=booking.getBookingComment()%>">
            </form>
            <!-- <input type="hidden" name="BEEF" id="BEEF" value="N"> -->
            <div class="container container-1" style="width: 100%; float: none;">
               <h3>
                  <span>01</span>
                  <span>금액 및 입금계좌 확인</span>
               </h3>
               <table class="table-1">
                  <tbody>
                     <tr>
                        <th>입금금액</th>
                        <td id="totPirce">#totalPrice</td>
                     </tr>
                     <tr>
                        <th>예약자</th>
                        <td id="RESV_NM">#RESV_NM</td>
                     </tr>
                     <tr>
                        <th>연락처</th>
                        <td id="RESV_PHONE">#RESV_PHONE</td>
                     </tr>
                     <tr>
                        <th>요청사항</th>
                        <td id="RESV_COMMENT">#RESV_COMMENT</td>
                     </tr>
                  </tbody>
               </table>
               <p>
                  * 예약자명과 입금자명이 다를 경우 숙박업체로 확인전화 부탁드리며, 당일예약은 출발전 꼭 입금하시고 예약을 완료하시기 바랍니다.
               </p>

            </div>
            <div class="container container-2" >
               <h3>
                  <span>02</span>
                  <span>객실이용시 확인사항</span>
               </h3>
               <ul>
                  <li>
                     입실시간: 오후3시 퇴실시간: 오전 11시                                                
                  </li>
                  <li>
                     예약된 인원 외 다른사람의 방문을 제한합니다.                           
                  </li>
                  <li>
                     입실이 불가능하며 당일 예약 취소와 동일시 되는 상황 : 예약된 인원 이상 왔을 때, 애완동물과 동반입실할 때                           
                  </li>
                  <li>
                     객실 내에서 해산물 및 고기요리와 튀김류 조리는 삼가주시기 바랍니다.                           
                  </li>
                  <li>
                     전 객실의 실내는 금연구역이며, 화재 위험이 있는 촛불 및 폭죽사용, 부탄가스 사용은 금지입니다.                           
                  </li>
                  <li>
                     시설물 훼손 및 분실시 책임은 고객님께 부과하오니 객실사용에 신경써 주시기 바랍니다.                           
                  </li>
                  <li>
                     부주의로 인해 일어난 안전사고, 귀중품(현금포함) 분실 및 파손에 대해서는 리조트에 책임을 물을 수 없습니다.                           
                  </li>
                  <!-- <li>
                     입금대기인 경우 예약을 원하는 문의가 많으므로 가급적 6시간 이내에 입금 부탁드립니다. 6시간 이후 자동 취소됩니다.                           
                  </li> -->
                  <li>
                     입금하신 후 12시간 이내에 예약완료 문자를 전송해드립니다. 문자를 받지 못하셨을 경우, 반드시 리조트 측에 연락주시기 바랍니다.                           
                  </li>
                  <li>
                     타인 명의로 입금 시, 확인전화 부탁드리며 당일예약은 출발 전 꼭 입금을 하셔야 합니다.                           
                  </li>
                  <li>
                     마지막 퇴실시에는 실내에 있는 쓰레기는 분리수거해 버려 주시고 사용하신 식기도 세척해 주시기 바랍니다.                           
                  </li>
                  <li>
                     시설물을 이용하시며 개인 안전관리에 각별히 유념해 주시고 본인 부주의에 의한 안전사고는 본인이 책임지게 됩니다.                           
                  </li>
               </ul>
               <!-- <div class="roundbox">
                  <p>정상적으로 예약신청이 접수되었습니다.</p>
               </div> -->
            </div>
            <div class="buttons">
               <button class="buttons-1 btn btn btn-outline-dark btn-lg" onclick="requestPay('<%=loginMember%>', '<%=room%>', '<%=booking%>')">결제하기</button>
            </div>
      </div>
      <!-- <div class="customLayerPopup" style="display: block;">
         <p>#point를 정확히 입력하세요</p>
         <div style="width: 5rem;">
            <strong>확인</strong>
         </div>
      </div> -->

      <!-- 내용 종료 -->

   </div>
   
</section>
<script>
   $("#totPirce").text("<%=booking.getBookingPrice()%>원");
   $("#RESV_NM").text("<%=loginMember.getMemberName()%>");
   $("#RESV_PHONE").text("<%=loginMember.getPhone() %>");
   $("#RESV_COMMENT").text("<%=booking.getBookingComment() %>");
</script>
      <!-- 푸터 영역 -->
<%@ include file="/views/common/footer.jsp"%>