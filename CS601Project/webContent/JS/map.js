function initMap() {
  //web resource reference - address convert to address: https://developers.google.com/maps/documentation/geocoding/intro
  //web resource reference - google map with marker and info window: https://developers.google.com/maps/documentation/javascript/examples/infowindow-simple

  //Get address
  var address = $('.address').text().trim();
  //Send request to google api to convert address to coordinates
  $.ajax({ 
    url: 'https://maps.googleapis.com/maps/api/geocode/json?address='+address, 
    success: function(data){
      //If successfully get response data from google api, then extract latitude and longitude
      //as long as formatted addresss
      var lat = data.results[0].geometry.location.lat;
      var lng = data.results[0].geometry.location.lng;
      var formattedAddress = data.results[0].formatted_address;
      var coordinates = {lat: lat, lng: lng};
      // Create a instance of google map
      var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14, //Set zoom level to 14
        center: coordinates //Set center of the map to coordinates
      });
      //Create template of goolgle info window
      var contentString = '<div><p><b>'+formattedAddress+'</b></p></div>';
      //Create a instance of goole info window and set the template
      var infowindow = new google.maps.InfoWindow({
        content: contentString
      });
      //Create a marker 
      var marker = new google.maps.Marker({
        position: coordinates,
        map: map,
      });
      //Binding the click event to marker to open info window
      marker.addListener('click', function() {
        infowindow.open(map, marker);
      });
    },
    error: function () {
      //When unsuccessfully get response from google map, then set a error message
      $('#map').html('<p style="color:red"><b>For some reason,the map can not be loaded!<b></p>')
    }
  });
}