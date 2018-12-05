 <%-- 
    Document   : abc
    Created on : 09-Nov-2017, 12:28:39
    Author     : bvrus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
 	<jsp:include page="menu.jsp" />
  	<h2>Welcome : ${pageContext.request.userPrincipal.name}</h2>
  	
    <h3>Quick Accident Mapper Demo</h3>
    <!--The div element for the map -->
    <div id="map"></div>
    <script>
// Initialize and add the map
function initMap() {
  // Creating the map and marking where it sets itself up.
  var london = {lat: 51.5072, lng: 0.1272};
  var londonArray = [{lat: 51.5072, lng: 0.1272}, {lat: 51.5082, lng: 0.1272}, {lat: 51.5072, lng: 0.1282}];
  // The map, centered on London
  var map = new google.maps.Map(
      document.getElementById('map'), {zoom: 4, center: london});
  // Plus a quick marker. 
  for(count = 0; count < 3; count++){
               //Does nothing for now.
			   var marker = new google.maps.Marker({position: londonArray[0], map: map});
            }
  //IMPORTANT NOTE - This is a rubbish way to do it. I know it's a rubbish way. Currently working on a workaround.
}
    </script>
    <!--Load the API from the specified URL
    * The async attribute allows the browser to render the page while the API loads
    * The callback parameter executes the initMap() function
	* This key is specifically for this project so I feel safe including it here. Please don't do anything stupid with it.
    -->
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAOMvHm2McdA9yu5G5bi1j6rTeIbd5vmnk&callback=initMap">
    </script>
  </body>
</html>
 
 
