// == 상태조회 == //

$(function () {
  $('.tabBtns').click((e) => {
    $('.dateSearchBtns').addClass('btn-outline-dark').removeClass('btn-dark');
    $('.tabBtns').removeClass('btn-dark');
    $(e.target).addClass('btn-dark');
  })
  
  $('.dateSearchBtns').click((e) => {
    $('.dateSearchBtns').addClass('btn-outline-dark').removeClass('btn-dark');
    $('.tabBtns').removeClass('btn-dark');
    $(e.target).addClass('btn-dark').removeClass('btn-outline-dark');
  })

  // == 검색 == //
  $('#selectInput').keyup((e) => {
    if (e.keyCode === 13) {
      $('#searchSelectBtn').click();
    }
  })
})

//  == 예약내역 검색 == //

// <select> 고정값
let selectState = '전체';
let selectType = 'user-name';

$('#selectState').change((e) => {
  selectState = $(e.target).val();
})

$('#selectType').change((e) => {
  selectType = $(e.target).val();
})

// <input> value AJAX 전송
function searchBooking(address) {
  const SEARCH_VALUE = $('#selectInput').val();
  
  $('.dateSearchBtns').removeClass('btn-dark');

  if (isEmpty(SEARCH_VALUE)) {
    alert('검색하실 내용을 입력해주세요.');
  }

  $('.tabBtns').removeClass('btn-dark');

  switch (selectState) {
    case '전체': $('.tabBtns:first').addClass('btn-dark'); break;
    case '취소요청': $('.tabBtns:eq(1)').addClass('btn-dark'); break;
    case '결제완료': $('.tabBtns:eq(2)').addClass('btn-dark'); break;
    case '이용완료': $('.tabBtns:last').addClass('btn-dark'); break;
  }

  ajaxBooking(address, selectState, selectType, SEARCH_VALUE);
}

// == 상세조회 == //

// URL, 예약번호 AJAX 전송
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
    success: (data) => {
      if (isEmpty(data)) {
        alert('상세조회를 할 수 없습니다...');
        return;
      }

      insertModal(data);

      if (data.bookingState === '결제완료' || data.bookingState === '취소요청') {
        $('#isBookingCancelBtn').removeClass('d-none');
          $('#cancelBookingBtn').click((e) => {
            cancelBooking(cancelBookingURL, data.bookingNo);
          });
      } else {
        $('#isBookingCancelBtn').addClass('d-none'); 
      }

    }
  })
}

// 상세조회 Modal에 데이터 넣기
function insertModal(data) {
  $('#reservationState').html('<em>' + data.bookingState + '</em>');
  $('#reservationNo').text('No.' + data.bookingNo);
  $('#reservationPaymentDate').text(data.paymentDate);
  $('#reservation').text(data.member.memberName);
  $('#reservationEmail').text(data.member.email);
  $('#reservationPhone').text(data.member.phone);
  $('#reservationDate').html(data.checkIn + '&#126;' + data.checkOut);
  $('#reservationPerson').text('성인: ' + data.guestAdult + ' 미성인: ' + data.guestChild + ' 유아: ' + data.guestInfant);
  $('#reservationComment').text(data.bookingComment);
  $('#reservationPayment').text(data.bookingPrice + '원');
}

// == 예약취소 == //

// 예약취소 AJAX
function cancelBooking(address, no) {
  $.ajax({
    url: address,
    type: 'get',
    data: { bookingNo: no },
    success: (data) => {
      if (data > 0) {
        alert('예약을 취소했습니다.');
      } else {
        alert('취소 실패했습니다.');
      }
    }
  })
}

// == 공용 예약 AJAX == //

// 예약 AJAX
function ajaxBooking(address, state, type ,value, cPage=1, numPerPage=10) {
  $.ajax({
    url: address,
    type: 'get',
    data: {state:state,type:type,value:value,cPage:cPage,numPerPage:numPerPage},
    dataType: 'json',
    beforeSend: () => {
      $('html').css('cursor', 'wait');
      $('#resultTable').html('<tr><td colspan="7"><div class="text-center"> <div class="spinner-border" role="status"> <span class="visually-hidden">Loading...</span> </div> </div></td></tr>');
    },
    complete: () => {
      $('html').css('cursor', 'auto');
    },
    success: (data) => {
      if (isEmpty(data)) {
        $('#resultTable').html(noSearchBooking());
        return;
      }

      $('#resultTable').empty();
      console.log(data.bookingList);
      $('#pagination').empty();

      
      data.bookingList.forEach((booking) => {
        $('#resultTable').append(resultBooking(booking));
      });

      $('#pagination').html(data.pageBar);
    }
  })
}

// 예약정보가 없을 경우
function noSearchBooking() {
  return '<tr><td colspan="7">예약내역이 없습니다 :(</td></tr>';
}

// 예약정보 표시
function resultBooking(data) {
  let result = '<tr>'
  result += '<td>' + data.bookingNo +'</td>'
  result += '<td>' + data.bookingState +'</td>'
  result += '<td>' + data.room.roomName +'</td>'
  result += '<td>' + data.member.memberName +'</td>'
  result += '<td>' + data.checkIn +'</td>'
  result += '<td>' + data.checkOut +'</td>'
  result += '<td>'
  result +=
    '<button type="button" class="btn btn-dark btn-sm" onclick="infoBooking(this)" data-bs-toggle="modal" data-bs-target="#bookingModal">상세조회</button>';
  result += '</td>'
  result += '</tr>';
  return result;
}

// 공백, null, undefined, 빈값 처리 함수
function isEmpty(value) {
  return typeof value === 'undefined' || (typeof value === 'object' && !Object.keys(value).length) ||
    value === null || value === "" || value === null || value.length === 0 ? true : false;
}