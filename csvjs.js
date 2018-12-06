$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "london-ksi-only-since2010.csv",
        dataType: "text",
        success: function(data) {processData(data);}
     });
});

function processData(allText) {
    var allTextLines = allText.split(/\r\n|\n/);
    var headers = allTextLines[0].split(',');
    var lines = [];

    for (var i=1; i<allTextLines.length; i++) {
        var data = allTextLines[i].split(',');
        if (data.length == headers.length) {

            var output = [];
            for (var j=0; j<headers.length; j++) {
                output.push(headers[j]+":"+data[j]);
            }
            lines.push(output);
        }
    }
    // alert(lines);
}		