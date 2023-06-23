$(() => {
  $("#insertSeasonBtn").click(() => {
    $("#seasonTableBody").append(createSeasonRow());
  });

  $(document).on("click", ".remove-row-btn", function () {
    $(this).closest("tr").remove();
  });
});

function createSeasonRow() {
  return (
    '<tr>' +
    '<td><input type="text" class="form-control" /></td>' +
    '<td><input type="text" class="form-control" /></td>' +
    '<td><input type="number" class="form-control" /></td>' +
    '<td><input type="date" class="form-control" /></td>' +
    '<td><input type="date" class="form-control" /></td>' +
    '<td><button type="button" class="btn btn-dark insert-row">추가</button></td > ' +
    '</tr>'
  );
}

function insertSeasonRow() {
  $()
}
