const room = $("#typeNm").text();
const checkInDate = $("#checkinDt").text();
const checkOutDate = $("#checkoutDt").text();
let manyAdult;
let manyChild;
let manyInfant;
let options = [];
const roomPrice = $("#roomPrice").text();
const personPrice = $("#persePrice").text();
const optionPrice = $("#optnPrice").text();
const totalPrice = $("#totPrice").text();

// select를 통해 입력한 인원 수 데이터 가져오기
$("#adultPers").change((e) => {
  return isEmpty($(e.target).val()) ? false : (manyAdult = $(e.target).val());
})

$("#manyChild").change((e) => {
  return isEmpty($(e.target).val()) ? false : (manyChild = $(e.target).val());
})

$("#manyInfant").change((e) => {
  return isEmpty($(e.target).val()) ? false : (manyInfant = $(e.target).val());
})

// checkbox를 통해 선택한 유료옵션 데이터 가져오기
$(".optCode:checked").each((e) => {
  return options.push($(e.target).val());
})

// 공백, null, undefined, 빈값 처리 함수
function isEmpty(value) {
  return typeof value === "undefined" || (typeof value === "object" && !Object.keys(value).length) ||
    value === null || value === "" || value === null || value.length === 0 ? true : false;
}