function check() {
    var code = $('#code').val();
    $('#errors').html('<p id="lastresult"></p>')

    $.ajax(
        {
            type: "GET",
            url: "/check",
            data: {
                "code": code
            },
            success: function (result) {
                console.log(result);
                for (i = 0; i < result.length && result[i] != null; i++) {
                    $('<div class="card bg-light"><div class="card-header" id="heading' + (i + 1) + '"><h5><button class="btn btn-link text-danger collapsed" type="button" data-toggle="collapse" data-target="#collapse' + (i + 1) + '" aria-expanded="false" aria-controls="collapse' + (i + 1) + '">Error ' + (i + 1) + '</button></h5></div><div id="collapse' + (i + 1) + '" class="collapse" aria-labelledby="heading' + (i + 1) + '" data-parent="#errors"><div class="card-body"><pre class="tab text-danger">' + result[i] + '<pre></div></div></div>').insertBefore("#lastresult");
                }
            },
            error: function (jqXHR, exception) {
                alert("Failed");
            }
        }
    );
}