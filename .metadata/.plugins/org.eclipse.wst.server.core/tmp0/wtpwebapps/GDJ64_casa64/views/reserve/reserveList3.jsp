<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- 헤더 영역 시작 -->
<%@ include file="/views/common/header.jsp"%>
		<!-- 헤더 영역 종료 -->

		<!-- 카테고리별 이미지 -->
		<div class="category-image">
			<img src="<%=request.getContextPath() %>/images/reserve/reservation.png" width="100%" height="200px">
			<div>RESERVATION</div>
		</div>
		
<br><br><br>
		
	<!-- 컨텐츠/내용 시작 -->
		<div class="reserve_step4 offset-md-1 col-md-10">
			<form>
				<!-- 추호 RESV_NO의 value값 js로 받아오기 필요 -->
				<input type="hidden" name="RESV_NO" id="RESV_NO" value="#RESEV_NO">
				<!-- 옵션값 넣어주기  -->
				<input type="hidden" name="BEEF" id="BEEF" value="N">
			</form>
				<div class="container container-1" style="width: 100%; float: none;">
					<h3>
						<span>01</span>
						<span>금액 및 입금계좌 확인</span>
					</h3>
					<table class="table-1">
						<tbody>
							<tr>
								<th>입금금액</th>
								<td id="totalPrice">#totalPrice</td>
							</tr>
							<tr>
								<th>예약자 성함</th>
								<td>이전페이지값</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td>이전페이지값</td>
							</tr>
							<tr>
								<th>비상연락처</th>
								<td>이전페이지값</td>
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
				<button class="buttons-1 btn btn btn-outline-dark btn-lg" type="button" id="saveBtn">결제하기</button>
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

		<!-- 푸터 영역 -->
<%@ include file="/views/common/footer.jsp"%>