$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "london-ksi-only-since2010.csv",
        dataType: "text",
        success: function(data) {processData(data);}
     });
	 initMap();
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

function initMap() {
  // Creating the map and marking where it sets itself up.
  var london = {lat: 51.5072, lng: 0.1272};
  var londonArray = [{lat: 51.485723, lng: -0.173176}, {lat: 51.499102, lng: -0.160395}, {lat: 51.494815, lng: -0.185346}, {lat:51.494815, lng:-0.185346}, {lat:51.520947, lng:-0.212271}, {lat: 51.491043, lng:-0.197308}, {lat:51.496484 , lng:-0.206169}, {lat:51.482636 , lng:-0.171283}, {lat:51.509766 , lng:-0.192678}, {lat:51.491227 , lng:-0.180159}, {lat:51.485812 , lng:-0.150127}, {lat:51.489269, lng:-0.164104}, {lat:51.480775 , lng:-0.184607}, {lat:51.495710 , lng:-0.173497}, {lat:51.481027 , lng:-0.183445}, ];
  //These represent the first three pieces of data, by the way.
  // The map, centered on London
  var map = new google.maps.Map(
      document.getElementById('map'), {zoom: 12, center: london});
  // Plus a quick marker. 
  for(count = 0; count < londonArray.length; count++){
               //Does a bucket of points.
			   var marker = new google.maps.Marker({position: londonArray[count], map: map});
            }
  //IMPORTANT NOTE - This is a rubbish way to do it. I know it's a rubbish way. Currently working on a workaround.
}

    //--Load the API from the specified URL
    //The async attribute allows the browser to render the page while the API loads
    //The callback parameter executes the initMap() function
	//This key is specifically for this project so I feel safe including it here. Please don't do anything stupid with it.
	// This was based off and adapted from the https://developers.google.com/maps/documentation/javascript/adding-a-google-map tutorial. 

    