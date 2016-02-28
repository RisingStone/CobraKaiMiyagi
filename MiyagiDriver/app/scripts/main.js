var map;
var driverid;

var createdDriver = false;

var driver = {
	name: "",
	visible: true,
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

function makeName() {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 5; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}

function getDriverLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(updatePostion, showError);
    } else { 
		console.log("Location not supported!!!")
    }
}

function updatePostion(position) {
	console.log(position.coords);
	
	//Do driver related reg
	driver.location.coordinates = [position.coords.latitude,position.coords.longitude]; 
	
	//UpdateMap
	updateMap(driver.location);
	
	//See if we made a driver yet, if not, make one
	if(!createdDriver){
		createDriver();
		registerDriver(driver);
	}else{
	}
}

function showError(error) {
	console.error("Location Error: " + error.code);
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
	driver.name = makeName();
	
	//Debug
	console.log(driver);
}

function registerDriver(driver){
	console.log(driver);
		
	// Using the core $.ajax() method
	$.ajax({

		// The URL for the request
		url: "http://192.168.11.132:3000/drivers",
	
		// The data to send 
		data : driver,
	
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

function createAlertRideAccept(){
		var alertMarkup = '\
		<div class="alert alert-danger alert-dismissible fade in" role="alert">\
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>\
			<h4>Accept Ride?</h4>\
			<p>Top of queue reached.</p>\
			<p><button type="button" class="btn btn-danger">Accept Ride</button> <button type="button" class="btn btn-default">Leave Queue</button></p>\
		</div>\
		';
	
		var bootstrap_alert = function() {}
		bootstrap_alert.warning = function(message) {
            $('#alert_placeholder').html(alertMarkup);
        }
		bootstrap_alert.warning("Here is my Alert!");
		
		$('#alert_placeholder').on('btn.btn-default', function () {
			console.log("Alert button pressed!");
		})
		
		$('#alert_placeholder').on('btn.btn-danger', function () {
			console.log("Alert button pressed!");
		})
}

getDriverLocation();
createAlertRideAccept();