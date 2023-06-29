$(() => {
  let isCreate = false;
  $('#insertSeasonBtn').click(function () {
    if (!isCreate) {
      $('#seasonTableBody').append(createSeasonRow());
      isCreate = true;
    }
  });

  $(document).on('click', '#insertRowBtn', function () {
    const $tbody = $(this).parent().parent().parent();
    const $seasonName = $tbody.find('.season-name').val();
    const $weekdayPrice = $tbody.find('.season-week-day').val();
    const $weekendPrice = $tbody.find('.season-weekend').val();
    const $seasonStart = $tbody.find('.season-start').val();
    const $seasonEnd = $tbody.find('.season-end').val();

    if (isEmpty($seasonName) || isEmpty($weekdayPrice) || isEmpty($weekendPrice) ||
      isEmpty($seasonStart) || isEmpty($seasonEnd)) {
      alert('추가하실 시즌에 입력하지 않은 값이 있습니다!');
      return;
    }

    location.assign(
      ROOT +
        "?seasonName=" +
        $seasonName +
        "&weekdayPrice=" +
        $weekdayPrice +
        "&weekEndPrice=" +
        $weekendPrice +
        "&seasonStart=" +
        $seasonStart +
        "&seasonEnd=" +
        $seasonEnd
    );

    $tbody.append(insertSeasonRow($seasonName, $weekdayPrice, $weekendPrice, $seasonStart, $seasonEnd));
    $(this).parent().parent().remove();
    isCreate = false;
  })

  $(document).on("click", "#removeRowBtn", function () {
    $(this).closest("tr").remove();
  });
});

function createSeasonRow() {
  return (
    '<tr>' +
    '<td><input type="text" class="form-control season-name" name="season"/></td>' +
    '<td><input type="text" class="form-control season-week-day" name="weekdayRate"/></td>' +
    '<td><input type="number" class="form-control season-weekend" name="weekendRate"/></td>' +
    '<td><input type="date" class="form-control season-start" name="startDate" /></td>' +
    '<td><input type="date" class="form-control season-end" name="endDate"/></td>' +
    '<td><button type="button" id="insertRowBtn" class="btn btn-dark" onclick=\"addSeason()\">추가</button></td > ' +
    '</tr>'
  );
}

function insertSeasonRow(name, weekPrice, weekEndPrice, start, end) {
  return (
    '<tr>' +
    '<td>'+ name +'</td>' +
    '<td>'+ weekPrice +'</td>' +
    '<td>'+ weekEndPrice +'</td>' +
    '<td>'+ start +'</td>' +
    '<td>'+ end +'</td>' +
    '<td><button type="button" id="removeRowBtn" class="btn btn-dark">삭제</button></td > ' +
    '</tr>'
  );
}


// function addSeason() {
//   const $tbody = $('#insertRowBtn').parent().parent().parent();
//   location.assign(
//     ROOT +
//     "?seasonName=" +
//     $tbody.find(".season-name").val() +
//     ",weekdayPrice=" +
//     $tbody.find(".season-week-day").val() +
//     ",weekEndPrice=" +
//     $tbody.find(".season-weekend").val() +
//     ",seasonStart=" +
//     $tbody.find(".season-start").val() +
//     ",seasonEnd=" +
//     $tbody.find(".season-end").val()
//   );
// }


// 공백, null, undefined, 빈값 처리 함수
function isEmpty(value) {
  return typeof value === "undefined" || (typeof value === "object" && !Object.keys(value).length) ||
    value === null || value === "" || value === null || value.length === 0 ? true : false;
}
