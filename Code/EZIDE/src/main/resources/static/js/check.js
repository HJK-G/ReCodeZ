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
                if (result[0] === "0") {
                	$('<pre class="tab text-success">' + result[1] + '<pre>').insertBefore("#outputIdentifier");
                }
                else {
	            	for (i = 0; i < result.length && result[i] != null; i++) {
	                    $('<pre class="tab text-danger">' + result[i] + '<pre>').insertBefore("#errorIdentifier");
	                }
				}
            },
            error: function (jqXHR, exception) {
                alert("Failed");
            }
        }
    );
}