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
                $('#results').html("Results:<pre class=\"tab\">"+result+"</pre>");
            },
            error: function (jqXHR, exception) {
                alert("Failed");
            }
        }
    );
}