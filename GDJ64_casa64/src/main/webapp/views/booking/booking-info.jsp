<%@page import="com.btc.booking.model.vo.Booking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 상준css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sj/style.css"/>
      <!-- 헤더 영역 시작 -->
<%@ include file="/views/common/header.jsp"%>
      <!-- 헤더 영역 종료 -->
<%Booking booking=(Booking)session.getAttribute("booking");%>


      <!-- 카테고리별 이미지 -->
      <div class="category-image">
         <img src="<%=request.getContextPath() %>/images/booking/reservation.png" width="100%" height="200px">
         <div>RESERVATION</div>
      </div>
      
<br><br><br>
      
   <!-- 컨텐츠/내용 시작 -->
            <div class="container container-2" >
               <h3>
                  <span>01</span>
                  <span>객실이용시 확인사항</span>
               </h3>
               <ul>
                  <li>
                     입실시간: <b>체크인 오후3시</b>                                             
                  </li>
                  <li>
                     퇴실시간: <b>체크아웃 오전 11시</b>                                                
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
            <br>
            <div class="container container">
						<h3>
							<span id="refundNum">02</span>
							<span>환불규정확인</span>
						</h3>
						<ul>
							<li>
								고객님의 사정에 의해 예약을 취소하시거나, 예약 날짜를 변경하실 경우 예약 취소에 해당되며, 아래와 같이 위약금이 적용되므로 신중히 고려하여 예약해주시기 바랍니다. 
							</li>
							<li>
								천재지변으로 인한 당일 예약취소를 하실 경우에도 환불이 불가능합니다.
							</li>
							<li>
								당일 예약하시고 입금한 후 당일 취소하셔도 예약일 기준으로 아래 환불규정에 해당됩니다.
							</li>
							<li>
								예약 당일부터 24시까지는 1일 전이며, 그 이후부터 24시간 경과 시 1일씩 경과하는 것으로 계산합니다.
							</li>
							<li>
								아래 환불규정은 고객님과 리조트의 공정한 계약이므로 동의하신다면 진행하시기 바랍니다.
							</li>
							<li>
								환불은 입금자명으로 되며, 입금 시 송금수수료는 제외한 후 입금됩니다.
							</li>
							<li>
								예약취소시 입실일과 관계없이 기본 취소 수수료 10%가 부과됩니다.
							</li>
						</ul>
						<table class="table-2">
							<thead>
								<tr>
									<th>이용일 당일</th>
									<th>이용일1일전</th>
									<th>이용일2일전</th>
									<th>이용일3일전</th>
									<th>이용일4일전</th>
									<th>이용일5일전</th>
									<th>이용일6일전</th>
									<th>이용일7일전</th>
									<th>이용일8일전</th>
									<th>이용일9일전</th>
									<th>이용일10일전</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>환불불가</td>
									<td>환불불가</td>
									<td>10% 환불</td>
									<td>20% 환불</td>
									<td>30% 환불</td>
									<td>40% 환불</td>
									<td>50% 환불</td>
									<td>60% 환불</td>
									<td>70% 환불</td>
									<td>80% 환불</td>
									<td>90% 환불</td>
								</tr>
							</tbody>
						</table>
					</div>
            
            
            <!-- 내용 종료 -->

   </div>
   
</section>

      <!-- 푸터 영역 -->
<%@ include file="/views/common/footer.jsp"%>