var map;
var driverid;

var driver = {
	id: "",
	name: "",
	v: 0,
	location: {
		type: "Point",
		coordinates: [37.807798, -122.431253]
	}
}

function initMap() {
		var myLatlng = new google.maps.LatLng(driver.location.coordinates[0], driver.location.coordinates[1]);
		
		var mapOptions = {
			zoom: 18,
			center: myLatlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		
        map = new google.maps.Map(document.getElementById('map'), mapOptions);
      }
	
function makeName(){
	return "Mark Stanford";
}

function makeid() {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 5; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(updatePostion, showError);
    } else { 
		$("#output").append("Geolocation is not supported by this browser.");
    }
}

function updatePostion(position) {
	//Do driver related reg
	driver.location.coordinates = [position.coords.latitude,position.coords.longitude]; 
	
	//UpdateMap
	updateMap(driver.location);
	
	$("#output").append("Latitude: " + position.coords.latitude + 
    "<br>Longitude: " + position.coords.longitude);	
}

function showError(error) {
	$("#output").append(error.code);
    switch(error.code) {
        case error.PERMISSION_DENIED:
            
            break;
        case error.POSITION_UNAVAILABLE:

            break;
        case error.TIMEOUT:

            break;
        case error.UNKNOWN_ERROR:

            break;
    }
}

function updateMap(location){
	var myLatlng = new google.maps.LatLng(location.coordinates[0], location.coordinates[1]);
	map.panTo(myLatlng);
}

function createDriver(){
	driver.id = makeid();
	driver.name = makeName();
}

function registerDriver(){
	// Using the core $.ajax() method
	$.ajax({

		// The URL for the request
		url: "http://192.168.11.132:3000/drivers",
	
		// The data to send (will be converted to a query string)
		body: {
			driver
		},
	
		// Whether this is a POST or GET request
		type: "POST",
	
		// The type of data we expect back
		dataType : "json",
	
		// Code to run if the request succeeds;
		// the response is passed to the function
		success: function( json ) {
			$("#output").append(json);
		},
	
		// Code to run if the request fails; the raw request and
		// status codes are passed to the function
		error: function( xhr, status, errorThrown ) {
			$("#output").append("Sorry, there was a problem!" );
		},
	
		// Code to run regardless of success or failure
		complete: function( xhr, status ) {
		}
	});
}

function queryHub(){
	// Using the core $.ajax() method
	$.ajax({

		// The URL for the request
		url: "http://192.168.11.132:3000/drivers",
	
		// Whether this is a POST or GET request
		type: "GET",
	
		// The type of data we expect back
		dataType : "json",
	
		// Code to run if the request succeeds;
		// the response is passed to the function
		success: function( json ) {
			$("#output").append(json);
		},
	
		// Code to run if the request fails; the raw request and
		// status codes are passed to the function
		error: function( xhr, status, errorThrown ) {
			$("#output").append("Sorry, there was a problem!" );
		},
	
		// Code to run regardless of success or failure
		complete: function( xhr, status ) {
		}
	});
}

getLocation();
createDriver();
registerDriver();

$("#output").append("<br>Driver: <br> Name:" + driver.name + "<br>ID: " + driver.id + "<br>Location: <br>" + driver.location.coordinates + "<br> Type: " + driver.location.type);

