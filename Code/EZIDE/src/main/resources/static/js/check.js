function check() {
    console.log("ASDFF");
    var code = $('#code').val();
    console.log(code);

    $.ajax(
        {
            type: "GET",
            url: "/check",
            data: {
                "code": code
            },
            success: function (result) {
                console.log(result);
                $('#secCol').html('<div class="alert alert-primary" role="alert" id="results">Results:<p id="results0"></p></div>')
                for (i = 0; i < result.length - 1; i++) {
                    console.log(i);
                    $('<div class="alert alert-secondary" role="alert"><pre class="tab">' + result[i] + '</pre></div>').insertAfter("#results0")
                }
            },
            error: function (jqXHR, exception) {
                alert("Failed");
            }
        }
    );
}