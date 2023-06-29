IMP.init('imp81583863');

function requestPay(member, room, booking) {
  const MEMBER = member.split(',');
  const ROOM = room.split(',');
  const BOOKING = booking.split(',');

  IMP.request_pay({
    pg: 'kakaopay',
    pay_method: 'card',
    merchant_uid: 'casa64' + new Date().getTime(), //예약번호
    name: ROOM[1],
    amount: BOOKING[18],
    buyer_email: MEMBER[3],
    buyer_name: MEMBER[2],
    buyer_tel: MEMBER[6],
  }, function (rsp) {
    if (rsp.success) {
      alert('예약에 성공했습니다.');
      $('#paymentForm').submit();
      return;
    } else {
      alert('예약에 실패했습니다. 다시 시도해주세요.');
    }
  })
}

