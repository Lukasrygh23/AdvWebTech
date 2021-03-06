<!DOCTYPE html>
<html>
  <head>
    <style>
       /* Set the size of the div element that contains the map */
      #map {
        height: 400px;  /* The height is 400 pixels */
        width: 100%;  /* The width is the width of the web page */
       }
    </style>
  </head>
  <body>
  	<jsp:include page="menu.jsp"/>
    <h3>Quick Accident Mapper Demo</h3>
    <!--The div element for the map -->
    <div id="map"></div>
	
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="csvjs.js"></script>
	<!--These two scripts will allow for the complete extraction of all data into local data. Unfortunately this part ends here due to the difficulty of conversion from BNG into lat and long.
	 *However the plan is to then move them into the next script, which would convert them into lat and long for use in the var londonArray.
	 -->
	
    <script>
	

	
// Initialize and add the map
function initMap() {
  // Creating the map and marking where it sets itself up.
  var london = {lat: 51.5072, lng: 0.1272};
  var londonArray = [{lat: 51.485723, lng: -0.173176}, {lat: 51.499102, lng: -0.160395}, {lat: 51.494815, lng: -0.185346}];
  //These represent the first three pieces of data, by the way.
  // The map, centered on London
  var map = new google.maps.Map(
      document.getElementById('map'), {zoom: 4, center: london});
  // Plus a quick marker. 
  for(count = 0; count < 3; count++){
               //Does a bucket of points.
			   var marker = new google.maps.Marker({position: londonArray[count], map: map});
            }
  //IMPORTANT NOTE - This is a rubbish way to do it. I know it's a rubbish way. Currently working on a workaround.
}
    </script>
    <!--Load the API from the specified URL
    * The async attribute allows the browser to render the page while the API loads
    * The callback parameter executes the initMap() function
	* This key is specifically for this project so I feel safe including it here. Please don't do anything stupid with it.
	* This was based off and adapted from the https://developers.google.com/maps/documentation/javascript/adding-a-google-map tutorial. 

    -->
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAOMvHm2McdA9yu5G5bi1j6rTeIbd5vmnk&callback=initMap">
    </script>
  </body>
</html>