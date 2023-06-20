<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<%=request.getContextPath()%>/js/reserve.js"></script>
<%@ include file="/views/common/header.jsp"%>
	
		<!-- 카테고리별 이미지 -->
		<div class="category-image">
			<img src="<%=request.getContextPath() %>/images/reservation.png" width="100%" height="200px">
			<div>RESERVATION</div>
		</div>
		
	<br> 
	
<!-- 0. 필터 버튼  --> <!-- 아이콘 출처 : https://tabler-icons.io/  -->
	<!-- <h3>COSA64 펜션 객실 예약</h3> -->
	<%-- <form class="container">
	  <div class="row">
	    <div class="offset-md-1 col-md-3 <!-- col-sm-3 col-xs-12 -->">
	      <p class="font-weight-bold">Bonnet</p>
	      <div data-toggle="buttons">
	        <label class="btn btn-outline-dark">
	          <input type="checkbox" name="oil" checked autocomplete="on" value="oil"> Oil
	        </label>
	        <label class="btn btn-outline-dark ">
	          <input type="checkbox" name="coolant" checked autocomplete="off"> Coolant
	        </label>
	        <label class="btn btn-outline-dark">
	          <input type="checkbox" name="breakfluid" checked autocomplete="off"> Break Fluid
	        </label>
	      </div>
	    </div>
	    <div class="offset-md-1 col-md-3">
	      <p class="font-weight-bold">Outside</p>
	      <div data-toggle="buttons">
	        <label class="btn btn-outline-dark">
	          <input type="checkbox" name="tyres" checked autocomplete="off"> Tyres
	        </label>
	        <label class="btn btn-outline-dark">
	          <input type="checkbox" name="wiperblades" checked autocomplete="off"> Wiper Blades
	        </label>
	        <label class="btn btn-outline-dark">
	          <input type="checkbox" name="lights" checked autocomplete="off"> Lights
	        </label>
	      </div>
	    </div>
	    <div class="offset-md-1 col-md-3">
	      <p class="font-weight-bold">Inside</p>
	      <div data-toggle="buttons">
	        <label class="btn btn-outline-dark">
	          <input type="checkbox" name="horn" checked autocomplete="off"> Horn
	        </label>
	        <label class="btn btn-outline-dark">
	          <input type="checkbox" name="breaks" checked autocomplete="off"> Breaks/Handbrake
	        </label>
	        <label class="btn btn-block btn-success active">
	          <input type="checkbox" name="seatbelt" checked autocomplete="off"> Seatbelt
	        </label>
	      </div>
	    </div>
	    <div class="offset-md-5 col-md-4">
	      <button class="btn btn-lg btn btn-outline-dark" type="submit">Submit</button>
	      <div id="formValues"></div>
	    </div>
	  </div>
	</form> --%>
  

	<!-- <div id="filterBox" >
	    <div class="btn-group" role="group" aria-label="Basic radio toggle button group" >
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck1" autocomplete="off" checked="" value="ocean">
	        <label class="btn btn btn-outline-dark" for="btncheck1"> 
	            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-beach" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                <path d="M17.553 16.75a7.5 7.5 0 0 0 -10.606 0"></path>
	                <path d="M18 3.804a6 6 0 0 0 -8.196 2.196l10.392 6a6 6 0 0 0 -2.196 -8.196z"></path>
	                <path d="M16.732 10c1.658 -2.87 2.225 -5.644 1.268 -6.196c-.957 -.552 -3.075 1.326 -4.732 4.196"></path>
	                <path d="M15 9l-3 5.196"></path>
	                <path d="M3 19.25a2.4 2.4 0 0 1 1 -.25a2.4 2.4 0 0 1 2 1a2.4 2.4 0 0 0 2 1a2.4 2.4 0 0 0 2 -1a2.4 2.4 0 0 1 2 -1a2.4 2.4 0 0 1 2 1a2.4 2.4 0 0 0 2 1a2.4 2.4 0 0 0 2 -1a2.4 2.4 0 0 1 2 -1a2.4 2.4 0 0 1 1 .25"></path>
	                <br>
	                <b>오션 뷰</b>
	            </svg>
	        </label>
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck2" autocomplete="off" value="city">
	        <label class="btn btn btn-outline-dark" for="btncheck2">
	            <span class="form-selectgroup-label">Download SVG icon from http://tabler-icons.io/i/home
	                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-building-community" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                    <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                    <path d="M8 9l5 5v7h-5v-4m0 4h-5v-7l5 -5m1 1v-6a1 1 0 0 1 1 -1h10a1 1 0 0 1 1 1v17h-8"></path>
	                    <path d="M13 7l0 .01"></path>
	                    <path d="M17 7l0 .01"></path>
	                    <path d="M17 11l0 .01"></path>
	                    <path d="M17 15l0 .01"></path>
	                    <br>
	                    <b>시티 뷰</b>
	                </svg>
	            </span>
	        </label>
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck3" autocomplete="off" value="premium">
	        <label class="btn btn btn-outline-dark" for="btncheck3">
	            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-home-star" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                <path d="M19.258 10.258l-7.258 -7.258l-9 9h2v7a2 2 0 0 0 2 2h4"></path>
	                <path d="M9 21v-6a2 2 0 0 1 2 -2h1.5"></path>
	                <path d="M17.8 20.817l-2.172 1.138a.392 .392 0 0 1 -.568 -.41l.415 -2.411l-1.757 -1.707a.389 .389 0 0 1 .217 -.665l2.428 -.352l1.086 -2.193a.392 .392 0 0 1 .702 0l1.086 2.193l2.428 .352a.39 .39 0 0 1 .217 .665l-1.757 1.707l.414 2.41a.39 .39 0 0 1 -.567 .411l-2.172 -1.138z"></path>
	                <br>
	                <b>프리미엄</b>
	            </svg>
	        </label>
	    </div>
	    
	    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck4" autocomplete="off" checked="" value="privatePool">
	        <label class="btn btn btn-outline-dark" for="btncheck4"> 
	            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-bath" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                <path d="M4 12h16a1 1 0 0 1 1 1v3a4 4 0 0 1 -4 4h-10a4 4 0 0 1 -4 -4v-3a1 1 0 0 1 1 -1z"></path>
	                <path d="M6 12v-7a2 2 0 0 1 2 -2h3v2.25"></path>
	                <path d="M4 21l1 -1.5"></path>
	                <path d="M20 21l-1 -1.5"></path>
	                <br>
	                <b>프라이빗 풀</b>
	            </svg>
	        </label>
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck5" autocomplete="off" value="musicRoom">
	        <label class="btn btn btn-outline-dark" for="btncheck5">
	            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-swimming" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-music" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                    <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                    <path d="M6 17m-3 0a3 3 0 1 0 6 0a3 3 0 1 0 -6 0"></path>
	                    <path d="M16 17m-3 0a3 3 0 1 0 6 0a3 3 0 1 0 -6 0"></path>
	                    <path d="M9 17l0 -13l10 0l0 13"></path>
	                    <path d="M9 8l10 0"></path>
	                </svg>                
	                <br>
	                <b>뮤직룸</b>
	            </svg>
	        </label>
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck6" autocomplete="off" value="beamProjector">
	        <label class="btn btn btn-outline-dark" for="btncheck6">
	            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-radio" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                <path d="M14 3l-9.371 3.749a1 1 0 0 0 -.629 .928v11.323a1 1 0 0 0 1 1h14a1 1 0 0 0 1 -1v-11a1 1 0 0 0 -1 -1h-14.5"></path>
	                <path d="M4 12h16"></path>
	                <path d="M7 12v-2"></path>
	                <path d="M17 16v.01"></path>
	                <path d="M13 16v.01"></path>
	            </svg>
	            <br>
	            <b>빔 프로젝터</b>
	        </label>
	    </div>
    
	    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck7" autocomplete="off"  checked="on" value="coffeeMachine">
	        <label class="btn btn-outline-secondary" for="btncheck7">
	            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-coffee" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                <path d="M3 14c.83 .642 2.077 1.017 3.5 1c1.423 .017 2.67 -.358 3.5 -1c.83 -.642 2.077 -1.017 3.5 -1c1.423 -.017 2.67 .358 3.5 1"></path>
	                <path d="M8 3a2.4 2.4 0 0 0 -1 2a2.4 2.4 0 0 0 1 2"></path>
	                <path d="M12 3a2.4 2.4 0 0 0 -1 2a2.4 2.4 0 0 0 1 2"></path>
	                <path d="M3 10h14v5a6 6 0 0 1 -6 6h-2a6 6 0 0 1 -6 -6v-5z"></path>
	                <path d="M16.746 16.726a3 3 0 1 0 .252 -5.555"></path>
	            </svg>    
	            <br>
	            <b><small>커피 머신</small></b>
	        </label>
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck8" autocomplete="off" checked="on" value="goosedownBed" sele>
	        <label class="btn btn-outline-secondary" for="btncheck8"> 
	            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-bed-filled" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                <path d="M3 6a1 1 0 0 1 .993 .883l.007 .117v6h6v-5a1 1 0 0 1 .883 -.993l.117 -.007h8a3 3 0 0 1 2.995 2.824l.005 .176v8a1 1 0 0 1 -1.993 .117l-.007 -.117v-3h-16v3a1 1 0 0 1 -1.993 .117l-.007 -.117v-11a1 1 0 0 1 1 -1z" stroke-width="0" fill="currentColor"></path>
	                <path d="M7 8a2 2 0 1 1 -1.995 2.15l-.005 -.15l.005 -.15a2 2 0 0 1 1.995 -1.85z" stroke-width="0" fill="currentColor"></path>
	                <br>
	                <b><small>구스다운 베드</small></b>
	            </svg>
	        </label>
	        <input type="checkbox" class="btn-check" name="btncheck" id="btncheck9" autocomplete="off"  checked="on" value="Rainy">
	        <label class="btn btn-outline-secondary" for="btncheck9">
	            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-beer-filled" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
	                <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
	                <path d="M17 2a2 2 0 0 1 1.995 1.85l.005 .15v4c0 1.335 -.229 2.386 -.774 3.692l-.157 .363l-.31 .701a8.902 8.902 0 0 0 -.751 3.242l-.008 .377v3.625a2 2 0 0 1 -1.85 1.995l-.15 .005h-6a2 2 0 0 1 -1.995 -1.85l-.005 -.15v-3.625c0 -1.132 -.21 -2.25 -.617 -3.28l-.142 -.34l-.31 -.699c-.604 -1.358 -.883 -2.41 -.925 -3.698l-.006 -.358v-4a2 2 0 0 1 1.85 -1.995l.15 -.005h10zm0 2h-10v3h10v-3z" stroke-width="0" fill="currentColor"></path>
	            </svg>
	            <br>
	            <b><small>웰컴 드링크</small></b>
	        </label>
	    </div>	
	</div>  -->
	
	<br>
	
<section class="p-2 m-0 border-0 bd-example">

<!-- 0. 필터 -->
	<span class="btn-group dropend offset-md-8 col-md-2">
		<button class="btn btn-outline-dark dropdown-toggle " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-funnel-fill" viewBox="0 0 16 16">
			  <path d="M1.5 1.5A.5.5 0 0 1 2 1h12a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.128.334L10 8.692V13.5a.5.5 0 0 1-.342.474l-3 1A.5.5 0 0 1 6 14.5V8.692L1.628 3.834A.5.5 0 0 1 1.5 3.5v-2z"/>
			</svg>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    객실 조회하기
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</button>
	  <!-- modal -->
		<ul class="dropdown-menu" role="menu">
			<li class="checkbox keep-open"><label><input type="checkbox">오션 뷰</label></li>
		    <li class="checkbox keep-open"><label><input type="checkbox">시티 뷰</label></li>
		    <li class="checkbox keep-open"><label><input type="checkbox">프리미엄</label></li>
		    <hr>
		    <li class="checkbox keep-open"><label><input type="checkbox">프라이빗 풀</label></li>
		    <li class="checkbox keep-open"><label><input type="checkbox">뮤직룸</label></li>
		    <li class="checkbox keep-open"><label><input type="checkbox">프로젝터</label></li>
		    <hr>
		    <li class="checkbox keep-open"><label><input type="checkbox">커피 머신</label></li>
		    <li class="checkbox keep-open"><label><input type="checkbox">구스다운 베드</label></li>
		    <li class="checkbox keep-open"><label><input type="checkbox">웰컴 드링크</label></li>
		    <li><a class="btn btn btn-outline-secondary btn-sm">조회하기</a></li>
		</ul>
	</span>
  
<!-- 1. 객실 선택 -->
	<div class="reserve_step1 ">
		<table class="scriptCalendar table w-auto p-3">
			<thead style="background-color: #f3f3da;">
				<tr>
					<td class="calendarBtn" id="btnPrevCalendar">&#60;&#60;</td>
					<td colspan="5">
						<span id="calYear">YYYY</span>년 
						<span id="calMonth">MM</span>월
					</td>
					<td class="calendarBtn" id="nextNextCalendar">&#62;&#62;</td>
				</tr>
				<tr style="background-color: #f3f3da;">
					<td>일</td>
					<td>월</td>
					<td>화</td>
					<td>수</td>
					<td>목</td>
					<td>금</td>
					<td>토</td>
				</tr>
			</thead>
			<tbody>
		   <!-- <tr>
					<td>
					날짜
					</td>
					<hr>
					<td>
						<div>
							<a>객실1</a>
							<a>객실2</a>
						</div>
					</td>
				</tr> -->
			</tbody>
		</table>
	</div>
	
	
<!-- 2. 객실 예약정보 입력-->
	<div class="reserve_step2 offset-md-2 col-md-8" id="reserveInput">
		<hr>

		<div class="header" id="tempHeader">
            <span>
                선택한 객실
                <em id="typeNm" >#typeNm</em>
            </span>
		</div>

        <div class="roundbox perio-wrapper" >
            <div class="header">
                <h3>기간선택</h3>
            </div>
            <div class="content" style="display: flex;">
                <div class="checkin"> 
                    <p>CHEKC IN</p>
                    <span id="checkinDt">#checkinDt</span>
                </div>
                <div>
                    <div style="color:white">.</div>
                    <div>~</div>
                </div>
                <div class="checkout on"> 
                    <p>CHEKC OUT</p>
                    <span id="checkinDt">#checkout</span>
                </div>   
                <div>
                    <select class="availableDays">
                        <option value>머무실 기간 선택</option>
                        <option value="" price="" consprice="">1박</option>
                        <option value="" price="" consprice="">2박</option>
                        <!-- <option value="" price="" consprice="">3박</option>
                        <option value="" price="" consprice="">4박</option>
                        <option value="" price="" consprice="">5박</option>
                        <option value="" price="" consprice="">6박</option>
                        <option value="" price="" consprice="">7박</option> -->
                    </select>
                </div> 
            </div>    
        </div>

        <div class="roundbox extra-person-wrapper">
            <div class="header">
                <h3>인원</h3>
            </div>
            <div class="content" id="countPerson">
                <ul>
                    <li>
                        <label id="adtNm">성인</label>
                        <select class="adultPers" id="adultPers">  
                            <option value>선택</option>
                            <option value="1" price="">1</option>
                            <option value="2" price="">2</option>
                            <!-- <option value="3" price="">3</option>
                            <option value="4" price="">4</option>
                            <option value="5" price="20000">5</option>
                            <option value="6" price="20000">6</option> -->
                        </select>
                    </li>
                    <li>
                        <lable id="kidNm">아동/유아</lable>
                        <select class="kidsPers" id="kidsPers">  
                            <option value>선택</option>
                            <option value="0">없음</option>
                            <option value="1" price="20000">1</option>
                            <!-- <option value="20000" price="">2</option>
                            <option value="3" price="20000">3</option>
                            <option value="4" price="20000">4</option>-->
                        </select>
                    </li>
                    <li>
                        <lable id="infNm">영아(24개월 미만)</lable>
                        <select class="infPers" id="infPers">  
                            <option value>선택</option>
                            <option value="0">없음</option>
                            <option value="1" price="">1</option>
                            <!-- <option value="" price="">2</option>
                            <option value="3" price="">3</option>
                            <option value="4" price="">4</option>-->
                        </select>
                    </li>
                </ul>
            </div>
        </div>

        <div class="roundbox options-wrapper">
            <div class="header">
                <h3>옵션선택</h3>
            </div>
            <div class="content">
                <ul id="optnList">
                    <li>
                        <!-- <li data-cnt="0"></li>
                        <li data-cnt="0"></li>
                        <li data-cnt="0"></li> -->                    
                        <input type="checkbox"  id="OPTN0"  data-cnt="0"  class="optCode op_name" value="1" data-tooltip >
                        <lable class="op_la">옵션명-n인기준(n0,000원)</lable>
                        <button type="button" class="btn btn btn-outline-dark btn-sm" data-bs-toggle="tooltip" data-bs-placement="right" title="예약시간 준수 및 사용시간 2시간 제한 있습니다. 이용 금액 : n0,000원">
                            상세설명
                        </button>
                    </li>
                    <li>
                        <input type="checkbox"  id="OPTN0"  data-cnt="0"  class="optCode op_name" value="1" data-tooltip >
                        <lable class="op_la">옵션명-n인기준(n0,000원)</lable>
                        <button type="button" class="btn btn btn-outline-dark btn-sm" data-bs-toggle="tooltip" data-bs-placement="right" title="예약시간 준수 및 사용시간 2시간 제한 있습니다. 이용 금액 : n0,000원">
                            상세설명
                        </button>
                    </li>                <li>
                        <input type="checkbox"  id="OPTN0"  data-cnt="0"  class="optCode op_name" value="1" data-tooltip >
                        <lable class="op_la">옵션명-n인기준(n0,000원)</lable>
                        <button type="button" class="btn btn btn btn-outline-dark btn-sm" data-bs-toggle="tooltip" data-bs-placement="right" title="예약시간 준수 및 사용시간 2시간 제한 있습니다. 이용  금액 : n0,000원">
                            상세설명
                        </button>
                    </li>               
                </ul>
            </div>
        </div>

        <div class="roundbox summary-wrapper">
            <div class="header">
                <h3>금액</h3>
            </div>
            <div class="content-amt content">
                <div>
                    <table>
                        <tbody>
                            <tr>
                                <th>객 실 금 액 : </th>
                                <td><span id="roomPrice">0</span>원</td>
                            </tr>
                            <tr>
                                <th>인원추가금액 : </th>
                                <td><span id="persePrice">0</span>원</td>
                            </tr>
                            <tr>
                                <th>옵 션 금 액  : </th>
                                <td><span id="optnPrice">0</span>원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(연박 할인가능)</td>
                            </tr>
                            <tr id="consApply" style="display:none">
                                <th>연 박 할 인 : </th>
                                <td><span id="consApplyPrice">0</span>원</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <hr>
                <div>
                    <p>
                        총 금액
                        <br>
                        <em id="totPrice"><b>0</b></em>원
                    </p> 
                </div>
            </div>
        </div>
	    <hr>
	    <div class="buttons"  >
	        <p>체크아웃 날짜와 인원(성인, 영유아)을 선택하시면 다음 단계로 넘어가실 수 있습니다.</p>
	        <button class="button-1 btn btn btn-outline-dark btn-lg" id="goNextBtn"
	        onclick="location.href='<%=request.getContextPath() %>/views/reserve/reserveList2.jsp' "
	        >다음단계</button>
	    </div>
    </div>
</section>

<script>

	$("#searchType").change(e=>{
	 	const type=$(e.target).val();
	 	$(e.target).parent().find("div").css("display","none");
	 	$("#search-"+type).css("display","inline-block");
	});

	$(document).ready(function () {
 		$("div[id='room']").click(function () {
  			$(".reserve_step2").show();
 		 });
	});

	$('.keep-open').click(function(e) {
        if (/input|label/i.test(e.target.tagName)){
          var parent = $(e.target).parent();
          if(parent.hasClass('checkbox')){
            var checkbox = parent.find('input[type=checkbox]');
            checkbox.prop("checked", !checkbox.prop("checked"));
            return false;
          }
        }
      });
		
</script>

		<!-- 푸터 영역 -->
<%@ include file="/views/common/footer.jsp"%>
<!-- footer바닥 고정법 찾아보기 -->