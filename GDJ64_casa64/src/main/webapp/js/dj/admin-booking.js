// 검색
function searchOption() {

}

// 예약정보가 없을시
function noSearchBooking() {
  return '<tr><td colspan="7">예약내역이 없습니다 :(</td></tr>';
}

// 테이블에 결과 출력
function resultBooking(data) {
  const BOOKING_DATA = data.split(",");

  let result = '<tr>'
  result += '<td>' + BOOKING_DATA[0] +'</td>'
  result += '<td>' + BOOKING_DATA[10] +'</td>'
  result += '<td>' + BOOKING_DATA[2] +'</td>'
  result += '<td>' + BOOKING_DATA[1] +'</td>'
  result += '<td>' + BOOKING_DATA[3] +'</td>'
  result += '<td>' + BOOKING_DATA[4] +'</td>'
  result += '<td>'
  result +=
    '<button type="button" class="btn btn-dark btn-sm" onclick="infoBooking(this)" data-bs-toggle="modal" data-bs-target="#bookingModal">상세조회</button>';
  result += '</td>'
  result += '</tr>';

  return result;
}

// 상세조회
function infoBooking(data) {
  const BOOKING_NO = $(data).parent().parent().find('td').first().text();
  ajaxShowModal(checkBookingURL, BOOKING_NO);
}

// 상세조회 AJAX
function ajaxShowModal(address, no) {
  $.ajax({
    url: address,
    type: 'get',
    data: {bookingNo:no},
    dataType: 'text',
    success: (data) => {
      if (isEmpty(data)) {
        alert('상세조회를 할 수 없습니다...');
        return;
      }

      const BOOKING_DATA = data.split(',');

      $("#reservationState").html('<em>' + BOOKING_DATA[10] + '</em>');
      $('#reservationNo').text('No.' + BOOKING_DATA[0]);
      $('#reservation').text(BOOKING_DATA[1]);
      $('#reservationEmail').text(BOOKING_DATA[1]);
      $('#reservationPhone').text(BOOKING_DATA[1]);
      $('#reservationDate').html(BOOKING_DATA[3] + '&#126;' + BOOKING_DATA[4]);
      $('#reservationPerson').text('성인: ' + BOOKING_DATA[5] + ' 미성인: ' + BOOKING_DATA[6] + ' 유아: ' + BOOKING_DATA[7]);
      $('#reservationComment').text(BOOKING_DATA[9]);
      $('#reservationPayment').text(BOOKING_DATA[8] + '원')

      if (BOOKING_DATA[10] === '결제완료' || '취소요청') {
        $('#bookingCancelBtn').removeClass('d-none');
      } else {
        $('#bookingCancelBtn').addClass('d-none');
      }

    }
  })
}


// 예약 AJAX
function ajaxBooking(address, state) {
  $.ajax({
    url: address,
    type: 'get',
    data: {state:state},
    dataType: 'text',
    success: (data) => {

      if (isEmpty(data)) {
        $('#resultTable').html(noSearchBooking());
        return;
      }

      const BOOKING_DATA = data.split('\n');

      $('#resultTable').empty();

      for (let i = 0; i < BOOKING_DATA.length; i++) {
        $('#resultTable').append(resultBooking(BOOKING_DATA[i]));
      }
    }
  })
}

// 공백, null, undefined, 빈값 처리 함수
function isEmpty(value) {
  return typeof value === "undefined" || (typeof value === "object" && !Object.keys(value).length) ||
    value === null || value === "" || value === null || value.length === 0 ? true : false;
}