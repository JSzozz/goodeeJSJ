<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- 헤더 영역 시작 -->
<%@ include file="/views/common/header.jsp"%>
		<!-- 헤더 영역 종료 -->

		<!-- 카테고리별 이미지 -->
		<div class="category-image">
			<img src="<%=request.getContextPath() %>/images/reservation.png" width="100%" height="200px">
			<div>RESERVATION</div>
		</div>
		
<br><br><br>
		
		<!-- 컨텐츠/내용 시작 -->
		<div class="reserve_step3 offset-md-1 col-md-10">
			<form id="frm" name="frm" method="post">
				<input type="hidden" name="RESV_NO" id="RESV" value="#">
				<input type="hidden" name="RESV_NO" id="RESV_NO" value="#">
				<input type="hidden" name="CARD_RESULT" id="CARD_RESULT" value="#">
				<input type="hidden"  id="NUM_PER_DIV_ADT" value="성인">
				<input type="hidden"  id="NUM_PER_DIV_KID" value="아동/유아">
				<input type="hidden"  id="NUM_PER_DIV_INF" value="영아(24개월 미만)">
				<div class="roundbox">
	<!-- 01 예약정보 확인 -->
					<div class="container container-1">
						<h3>
							<span>01</span>
							<span>예약정보 확인</span>
						</h3>
						<table class="table-1">
							<tbody>
								<tr>
									<th>객실</th>
									<td id="typeNm">#typeNm(ex. ocean terrace101)</td>
								</tr>
								<tr>
									<th>날짜</th>
									<td id="period">#period(ex.2023.07.06 ~2023.07.07)</td>
								</tr>
								<tr>
									<th>숙박인원</th>
									<td id="person">#person(ex.성인 = 2명, 아동/유아 = 1명, 영아(24개월 미만) = 1명)</td>
								</tr>
								<tr>
									<th>옵션</th>
									<td id="optionList">#optionList(ex.실내바베큐(전용건물-공용)이용료-4인기준 [2회]
										야외숯불바베큐이용료-4인기준 [1회]
										미니풀이용 [1회])</td>
								</tr>
								<tr>
									<th>가격</th>
									<td>
										<span id="detailPrice">
											객실금액=
											<em id="roomPirce">#roomPirce</em>
											원 + 인원추가금액=
											<em id="persPirce">#persPirce</em>
											원 + 옵션추가금액=
											<em id="optnPrice">#optnPrice</em>
											원 ::
										</span>
										<span>
											합계=
											<em id="totPirce">#totPirce</em>
											원
										</span>
									</td>
								</tr>
							</tbody>
						</table>
					</div>

	<!-- 02 개인정보 확인 -->
					<div class="container container-2">
						<h3>
							<span>02</span>
							<span>개인정보 확인</span>
						</h3>
						<table class="table-1">
							<tbody>
								<tr>
									<th>예약자명</th>
									<td>
										<input type="text" name="RESV_NM" id="RESV_NM" placeholder="#RESV_NM">
									</td>
								</tr>
								<tr>
									<th>연락처</th>
									<td>
										<input type="text" name="RESV_PHONE" id="RESV_PHONE" placeholder="#RESV_PHONE">
									</td>
								</tr>
								<tr>
									<th>비상연락처</th>
									<td>
										<input type="text" name="RESV_PHONE2" id="RESV_PHONE2" placeholder="#RESV_PHONE2">
									</td>
								</tr>
								<tr>
									<th>요청사항</th>
									<td>
										<textarea name="RESV_COMT" id="RESV_COMT" cols="40" rows="4" placeholder="#RESV_COMT"></textarea>
									</td>
								</tr>
							</tbody>
						</table>
					</div>

					<!-- <div class="contaoner contaoner-3">
						<h3>

						</h3>
						<table class="table-1">
							
						</table>

					</div> -->
					
	<!-- 3. 03 환불규정확인 -->
					<div class="container container">
						<h3>
							<span id="refundNum">03</span>
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
				</div>
				<div class="buttons">
					<button class="buttons-1 btn btn-secondary btn-lg" 
	        		onclick="location.href='<%=request.getContextPath() %>/views/reserve/reserveList3.jsp' "
					type="button" id="saveBtn">객실 예약하기</button>
				</div>
			</form>
		</div>
<!-- 		<div class="customLayerPopup" style="display: block;">
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