<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/nara_publish/css/about.css" />
<!--KAKAO map API-->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ceba3d61a1b8c20c16bbe54d8baca88b"></script>
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/about_categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div id="wrap" class="container-fluid">
	<section>
		<div class="container">
			<%@ include file="/views/about/about_tab.jsp"%>

			<div class="map">
				<div class="kakao-map-area mb-5">
					<div id="map" style="width: 100%; height: 550px; margin: auto;"></div>
				</div>
				<div class="map-description-area w-100">
					<dl class="horizen-sort">
						<dt class="w150px">주소</dt>
						<dd>강원도 강릉시 저동 61-2</dd>
					</dl>
					<dl class="horizen-sort">
						<dt class="w150px">연락처</dt>
						<dd>010-4578-1245</dd>
					</dl>
					<dl class="horizen-sort">
						<dt class="w150px">자가용 이용</dt>
						<dd>북강릉TG 진출 시 북강릉IC에서 ‘강릉, 사천’ 방면으로 우측방향 → 동해대로를 따라 약 4.5km
							이동 → 고가도로 옆길을 따라 이동 후 ‘경포도립공원’ 방면으로 좌회전 → 안현로를 따라 약 2.17km 이동 후
							우측방향 → 안현로244번길을 따라 약 1.5km 이동 후 좌회전 → 저동골길을 따라 약 30m 이동 후 좌회전 →
							저동골길을 따라 약 55m이동 → CASA64 도착</dd>
					</dl>
					<dl class="horizen-sort">
						<dt class="w150px">대중교통 이용</dt>
						<dd>강릉TG 진출 시 ‘주문진, 경포, 강릉과학산업단지’ 방면으로 우측방향 → 사임당로를 따라 약
							2.2km 이동 → ‘솔올지구, 강릉원주대학교’ 방면으로 우회전 → 교동광장로를 따라 약 800m 이동 → 동해대로를
							따라 약 1km 이동 → ‘오죽헌, 시립박물관, 강릉오죽한옥마을’ 방면으로 우측방향 → 죽헌길을 따라 약 300m
							이동 → ‘오죽헌, 시립박물관, 강릉오죽한옥마을’ 방면으로 우측방향 → 죽헌길을 따라 약 770m 이동 후 우회전 →
							경포사거리에서 ‘경포, 참소리박물관’ 방면으로 좌회전 → 경포로를 따라 약 2.27km 이동 후 좌회전 →
							경포로371번길을 따라 약 300m이동 후 좌회전 → 경포로371번길을 따라 약 300m 이동 → CASA64 도착</dd>
					</dl>
				</div>
			</div>
		</div>
	</section>
</div>

<script>
	const latitude = 37.7987202892717;
	const longitude = 128.894420556472;
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(latitude, longitude), //지도의 중심좌표.
		level : 3
	//지도의 레벨(확대, 축소 정도)
	};

	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	var markerPosition = new kakao.maps.LatLng(latitude, longitude);

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		position : markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);

	// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	var iwContent = '<div style="padding:12px;"><b>CASA 64<b><br><a href="https://map.kakao.com/link/map/CASA64,37.7987202892717,128.894420556472" style="color:gray" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/CASA64,37.7987202892717,128.894420556472" style="color:gray" target="_blank">길찾기</a></div>', iwPosition = new kakao.maps.LatLng(
			latitude, longitude); //인포윈도우 표시 위치입니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
		position : iwPosition,
		content : iwContent
	});

	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker);

	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	var mapTypeControl = new kakao.maps.MapTypeControl();

	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
</script>
<!-- 내용 종료 -->
<%@ include file="/views/common/footer.jsp"%>

