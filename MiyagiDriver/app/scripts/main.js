jQuery.support.cors = true;

var map;
var driverid;
var hubId = "56d35a97e6087451433f0ecb";
var hubList;
var hub;

var shouldLoop = true;

var serverIP = "http://192.168.11.133:3000";

var createdDriver = false;

var driver = {
	name: "mark",
	location: {
		"type": "Point",
		"coordinates": [
			-122.3944,
			37.7960
		]
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
	driver.location.coordinates = [position.coords.longitude,position.coords.latitude]; 
	
	//UpdateMap
	updateMap(driver.location);
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
	
	//Show marker
	var marker = new google.maps.Marker({
		animation: google.maps.Animation.DROP,
		labal : '(You)',
		position: myLatlng,
		map: map,
		title: ' <- YOU'
	});
}

function createDriver() {
	//See if we made a driver yet, if not, make one
	if(!createdDriver){
		driver.name = makeName();
		$('#drivername').text(driver.name);	
		createdDriver = true;
		// registerDriver(driver);
	}else{
		console.log(driver.name + " already created!");
	}
	
	//Debug
	console.log(driver);
}

function registerDriver(driver){
	console.log(driver);

	$.ajax({

		// The URL for the request
		url: serverIP + "/drivers/" + driver.name,
	
		// Whether this is a POST or GET request
		type: "GET",
	
		// The type of data we expect back
		dataType : "json",
	
		// Code to run if the request succeeds;
		// the response is passed to the function
		success: function( json ) {
			createdDriver = true;
			
			console.log('Drivename: ' + driver.name);
		},
	
		// Code to run if the request fails; the raw request and
		// status codes are passed to the function
		error: function( xhr, status, errorThrown ) {
			console.error("Sorry, there was a problem!");
		},
	
		// Code to run regardless of success or failure
		complete: function( xhr, status ) {
		}
	});
}

function queryHub(id){
	console.log("query hub : " + id);
		
	// Using the core $.ajax() method
	$.ajax({

		// The URL for the request
		url: serverIP + "/hubs/" + id,
	
		// Whether this is a POST or GET request
		type: "GET",
		
		headers: {
			'Content-Type':'application/json'
		},
	
		// The type of data we expect back
		dataType : "json",
	
		// Code to run if the request succeeds;
		// the response is passed to the function
		success: function( json ) {
			
			hub = json;
			
			updateQueue(hub);
			
			console.log(json);
		},
	
		// Code to run if the request fails; the raw request and
		// status codes are passed to the function
		error: function( xhr, status, errorThrown ) {
			console.error("Sorry, there was a problem!");
		},
	
		// Code to run regardless of success or failure
		complete: function( xhr, status ) {
		}
	});
}

function queryHubList(){
	console.log("queryHubList:");
	
	// Using the core $.ajax() method
	$.ajax({

		// The URL for the request
		url: serverIP + "/hubs",
	
		// Whether this is a POST or GET request
		type: "GET",
	
		// The type of data we expect back
		dataType : "json",
	
		// Code to run if the request succeeds;
		// the response is passed to the function
		success: function( json ) {
			updateHubs(json);
			console.log(json);
		},
	
		// Code to run if the request fails; the raw request and
		// status codes are passed to the function
		error: function( xhr, status, errorThrown ) {
			console.error("Sorry, there was a problem!");
		},
	
		// Code to run regardless of success or failure
		complete: function( xhr, status ) {
		}
	});
}

function queueDriver(){
	console.log("queueDriver:");
	
	// Using the core $.ajax() method
	$.ajax({

		// The URL for the request
		url: serverIP + "/hubs/" + hubList[0]._id + '/drivers/' + driver.name,
	
		// Whether this is a POST or GET request
		type: "GET",
	
		// The type of data we expect back
		dataType : "json",
	
		// Code to run if the request succeeds;
		// the response is passed to the function
		success: function( json ) {
			console.log(json);
		},
	
		// Code to run if the request fails; the raw request and
		// status codes are passed to the function
		error: function( xhr, status, errorThrown ) {
			console.error("Sorry, there was a problem!");
		},
	
		// Code to run regardless of success or failure
		complete: function( xhr, status ) {
		}
	});
}

function dequeueDriver(){
	console.log("dequeueDriver:");
	
	// Using the core $.ajax() method
	$.ajax({

		// The URL for the request
		url: serverIP + "/hubs/" + hub._id + '/top',
	
		// Whether this is a POST or GET request
		type: "GET",
	
		// The type of data we expect back
		dataType : "json",
		
		// Code to run if the request succeeds;
		// the response is passed to the function
		success: function( json ) {
			console.log(json);
		},
	
		// Code to run if the request fails; the raw request and
		// status codes are passed to the function
		error: function( xhr, status, errorThrown ) {
			console.error("Sorry, there was a problem!");
		},
	
		// Code to run regardless of success or failure
		complete: function( xhr, status ) {
		}
	});
}

function updateHubs(json){
	console.log(json);
	
	hubList = json;
	
	for(var i = 0; i < hubList.length; i++){
		addHubMarker(hubList[i]);
	}
}

function addHubMarker(hub){
		var myLatlng = new google.maps.LatLng(hub.hub.coordinates[1], hub.hub.coordinates[0]);
		
		console.log("New Hub Marker: " + hub.hub.coordinates[1] + ":" + hub.hub.coordinates[0]);
	
		//Show marker
		var marker = new google.maps.Marker({
			animation: google.maps.Animation.DROP,
			labal : 'Kreese',
			position: myLatlng,
			map: map,
			title: hub.name
		});
}

function createAlertRideAccept(){
		var alertMarkup = '\
		<div class="alert alert-success alert-dismissible fade in" role="alert">\
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>\
			<h4>Accept Ride?</h4>\
			<p>Top of queue reached.</p>\
			<p><button type="button" class="btn btn-success">Accept Ride</button> <button type="button" class="btn btn-danger">Leave Queue</button></p>\
		</div>\
		';
	
		var bootstrap_alert = function() {}
		
		bootstrap_alert.warning = function(message) {
            $('#alert_placeholder').html(alertMarkup);
        }
		bootstrap_alert.warning("Here is my Alert!");
		
		$('.btn.btn-danger').on('click', function () {
			rejectRide();
		})
		
		$('.btn.btn-success').on('click', function () {
			acceptRide();
		})
}

function rejectRide(){
	$('.close').alert('close');
	
	//dequeue driver
	
	console.log('Ride rejected!');
}

function acceptRide(){
	$('.close').alert('close');
	
	//dequeue rider and driver
	dequeueDriver();

	console.log('Ride accepted!');	
}

var iconIds = ["first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"];

function updateQueue(json){
	
	hub = json;
	
	for(var j = 0; j < iconIds.length; j++){
		$('#r'+iconIds[j]).text('');
		$('#d'+iconIds[j]).text('');	
	}
	
	for(var i = 0; i < hub.riders.length; i++){
		var hubRiderPostion = hub.riders[i];
		$('#r'+iconIds[i]).text(hubRiderPostion);
	}
			
	for(var i = 0; i < hub.drivers.length; i++){
		var hubDriverPositon = hub.drivers[i];
		if(hubDriverPositon == driver.name){
			$('#d'+iconIds[i]).text('(YOU)');
		}	
		else{			
			$('#d'+iconIds[i]).text(hubDriverPositon);	
		}
	}
	
	//Check hub driver position 1, if it's our ID, then let's put up an accept thing
	if(hub.drivers[0] == driver.name && hub.riders.length < 0){
		createAlertRideAccept();
	}
}

$('#location').on('click', function () {
	console.log('Location btn pressed');
	createDriver();
	getDriverLocation();
});

$('#register').on('click', function () {
	console.log('Location btn pressed');
	
	createDriver();
});

$('#enter').on('click', function () {
	console.log('Location btn pressed');
	
	queueDriver();
});

$('#leave').on('click', function () {
	console.log('Location btn pressed');
});

$('#hubs').on('click', function () {
	//Get a list of hubs
	queryHubList();
});

$('#hub').on('click', function () {
	shouldLoop = true;
	setTimeout(mainLoop, 2000);
});

$('#stop').on('click', function () {
	shouldLoop = false;
});

function mainLoop() {
	queryHub(hubList[0]._id);
	if(shouldLoop)
		setTimeout(mainLoop, 2000);
}