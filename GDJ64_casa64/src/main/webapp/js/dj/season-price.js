$(() => {
  let isCreate = false;
  $('#insertSeasonBtn').click(function () {
    if (!isCreate) {
      $('#seasonTableBody').append(createSeasonRow());
      isCreate = true;
    }
  });

  $(document).on('click', '#insertRowBtn', function () {
    console.log($(this).parent().parent());
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
    '<td><input type="text" class="form-control season-name" /></td>' +
    '<td><input type="text" class="form-control season-week-day" /></td>' +
    '<td><input type="number" class="form-control season-weekend" /></td>' +
    '<td><input type="date" class="form-control season-start" /></td>' +
    '<td><input type="date" class="form-control season-end" /></td>' +
    '<td><button type="button" id="insertRowBtn" class="btn btn-dark">추가</button></td > ' +
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

// 공백, null, undefined, 빈값 처리 함수
function isEmpty(value) {
  return typeof value === "undefined" || (typeof value === "object" && !Object.keys(value).length) ||
    value === null || value === "" || value === null || value.length === 0 ? true : false;
}
