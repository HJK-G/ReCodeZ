function check() {
    var code = $('#code').val();
    $('#errors').html('<p id="errorIdentifier"></p>')
    $('#output').html('<p id="outputIdentifier"></p>')

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
                    document.getElementById("output").value = '<pre class="tab">' + result[i] + '<pre>';
                }
            },
            error: function (jqXHR, exception) {
                alert("Failed");
            }
        }
    );
}