var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
var Hub = mongoose.model('Hub');

//
// GET all hubs, or only hubs within given distance of a lat/long pair
//
router.get('/', function(req, res, next) {
	var limit = req.query.limit || 100;

	if(!req.query || !req.query.longitude || !req.query.latitude) {
		Hub.find({}).limit(limit).exec(function(err, hubs) {
		    if (!err) { 
		        res.json(200, hubs);
		    } else {
		    	throw err;
		    }
		});
	} else {
	    var radius = req.query.distance || 100;

	    // Max distance to radians: radius of earth is approx 6371km.
	    //max /= 6371;

	    // long, lat coordinates (notice order!)
	    var pair = [];
	    pair[0] = req.query.longitude;
	    pair[1] = req.query.latitude;

	    // get nearby hubs
	    var query = Hub.find({})
	    	.where('area')
	    	.near({
	    		center: { type: 'Point', coordinates: pair },
	    		maxDistance: radius * 1609.34,
	    		spherical: true
	    	})
	    	.limit(limit)
	    	.exec(function(err, hubs) {
	      		if (err) return res.json(500, err);
	      		res.json(200, hubs);
	    	});
	}
});

//
// GET all hubs, or only hubs within given distance of a lat/long pair
//
router.get('/:hub_id', function(req, res, next) {
	var hub_id = req.params.hub_id;
	if(hub_id) {
		Hub.findOne({ "_id": hub_id }, function (err, hub) {
			if (err) {
				console.error('Where is my hub? ', err);
			  	return res.json(404, err);
			}
			if(hub) {
				res.json(200, hub);
			} else {
				res.send(404, "No hub found");
			}
		});
	} else {
		res.send(400, "Missing hub id");
	}
});

//
// Create new hub
// 
router.post('/', function(req, res, next) {
	console.log('Creating hub...');
	var newHub = new Hub(req.body);
	newHub.save(function(err) {
		if(err) {
			console.log(err);
			res.send(500, err);
		} else {
			res.json(req.body);
		}
	});
});

//
// Update visibility of existing hub
//
router.put('/:hub_id', function(req, res, next) {
	var hub_id = req.params.hub_id;
	console.log('Updating hub ' + hub_id);

	Hub.findOne({ _id: hub_id }, function (err, hub){
	  if (err || !hub) {
	  	console.error('Where is my hub? ', err);
	  	return res.json(404, err);
	  }

	  // is the boolean property defined	  
	  if(typeof req.body.visible !== "undefined") {
	  	if(!req.body.visible && hub.visible && hub.riders.length > 0) {
	  		// Keep lights on while riders are in queue
	  		return res.send(403, "Riders in queue, can't shutdown");
	  	}
	  	hub.visible = req.body.visible;
	  	hub.save(function(err) {
			if(err)
				res.send(500, err);
			else
				res.json(req.body);
	  	});
	  } else {
	  	return res.send(200, "No changes");
	  }
	});
});

//
// Add rider to existing hub's FIFO queue
//
router.post('/:hub_id/riders/:rider', function(req, res, next) {
	var hub_id = req.params.hub_id;
	var rider = req.params.rider;
	console.log('Adding rider ' + rider + ' to hub_id ' + hub_id);
	
	Hub.findOne({ "_id": hub_id }, function (err, hub){
	  if (err || !hub) {
	  	console.error('Where is my hub? ', err);
	  	return res.json(404, "No hub");
	  }

	  if(rider) {
	  	var i = 0;
	  	// Ensure only unique riders in queue
	  	for(; i < hub.riders.length; i++) {
	  		if(hub.riders[i] === rider) {
	  			console.log('Re-queuing rider ' + rider);
	  			hub.riders.splice(i, 1); // In-place removal
	  			break;
	  		}
	  	}
	  	// Enqueue given rider
	  	hub.riders.push(rider);

	  	hub.save(function(err) {
			if(err)
				res.send(500, err);
			else
				res.send(200);
	  	});
	  } else {
	  	return res.send(400, "No rider");
	  }
	});
});

//
// Add driver to existing hub's queue
//
router.post('/:hub_id/drivers/:driver', function(req, res, next) {
	var hub_id = req.params.hub_id;
	var driver = req.params.driver;
	console.log('Adding driver ' + driver + ' to hub_id ' + hub_id);
	
	Hub.findOne({ "_id": hub_id }, function (err, hub){
	  if (err || !hub) {
	  	console.error('Where is my hub? ', err);
	  	return res.json(404, "No hub");
	  }

	  if(driver) {
	  	var i = 0;
	  	// Ensure only unique drivers in queue
	  	for(; i < hub.drivers.length; i++) {
	  		if(hub.drivers[i] === driver) {
	  			console.log('Re-queuing driver ' + driver);
	  			hub.drivers.splice(i, 1); // In-place removal
	  			break;
	  		}
	  	}
	  	// Enqueue given driver
	  	hub.drivers.push(driver);
	  	hub.save(function(err) {
			if(err)
				res.send(500, err);
			else
				res.send(200);
	  	});
	  } else {
	  	return res.send(400, "No driver");
	  }
	});
});

//
// FATAL: Just for Mark's web app: Add driver to existing hub's queue
//
router.get('/:hub_id/drivers/:driver', function(req, res, next) {
	var hub_id = req.params.hub_id;
	var driver = req.params.driver;
	console.log('Adding driver ' + driver + ' to hub_id ' + hub_id);
	
	Hub.findOne({ "_id": hub_id }, function (err, hub){
	  if (err || !hub) {
	  	console.error('Where is my hub? ', err);
	  	return res.json(404, "No hub");
	  }

	  if(driver) {
	  	var i = 0;
	  	// Ensure only unique drivers in queue
	  	for(; i < hub.drivers.length; i++) {
	  		if(hub.drivers[i] === driver) {
	  			console.log('Re-queuing driver ' + driver);
	  			hub.drivers.splice(i, 1); // In-place removal
	  			break;
	  		}
	  	}
	  	// Enqueue given driver
	  	hub.drivers.push(driver);
	  	hub.save(function(err) {
			if(err)
				res.send(500, err);
			else
				res.send(200);
	  	});
	  } else {
	  	return res.send(400, "No driver");
	  }
	});
});

//
// Dequeue rider & driver pair from FIFO queue on existing hub
//
router.delete('/:hub_id/top', function(req, res, next) {
	var hub_id = req.params.hub_id;
	console.log('Removing top rider from hub_id ' + hub_id);
	
	Hub.findOne({ "_id": hub_id }, function (err, hub){
	  if (err || !hub) {
	  	console.error('Where is my hub? ', err);
	  	return res.json(404, "No hub");
	  }

	  if(hub.riders.length == 0) {
	  	return res.send(404, "No rider in queue");
	  }

	  if(hub.drivers.length == 0) {
	  	return res.send(404, "No driver in queue");
	  }

	  // Dequeue pair
	  var rider = hub.riders.shift();
	  var driver = hub.drivers.shift();
  	  hub.save(function(err) {
		if(err)
			res.send(500, err);
		else
			res.send(200, "['" + rider + "','" + driver + "']");
  	  });
	});
});

//
// FATAL: For Mark's web app only: Dequeue rider & driver pair from FIFO queue on existing hub
//
router.get('/:hub_id/top', function(req, res, next) {
	var hub_id = req.params.hub_id;
	console.log('Removing top rider from hub_id ' + hub_id);
	
	Hub.findOne({ "_id": hub_id }, function (err, hub){
	  if (err || !hub) {
	  	console.error('Where is my hub? ', err);
	  	return res.json(404, "No hub");
	  }

	  if(hub.riders.length == 0) {
	  	return res.send(404, "No rider in queue");
	  }

	  if(hub.drivers.length == 0) {
	  	return res.send(404, "No driver in queue");
	  }

	  // Dequeue pair
	  var rider = hub.riders.shift();
	  var driver = hub.drivers.shift();
  	  hub.save(function(err) {
		if(err)
			res.send(500, err);
		else
			res.send(200, "['" + rider + "','" + driver + "']");
  	  });
	});
});

//
// Remove rider from existing hub
//
router.delete('/:hub_id/riders/:rider', function(req, res, next) {
	var hub_id = req.params.hub_id;
	var rider = req.params.rider;
	console.log('Removing rider ' + rider + ' from hub_id ' + hub_id);
	
	Hub.findOne({ "_id": hub_id }, function (err, hub){
	  if (err) {
	  	console.error('Where is my hub? ', err);
	  	return res.json(404, err);
	  }

	  if(rider) {
	  	var i = 0;
	  	// Ensure only unique riders in queue
	  	for(; i < hub.riders.length; i++) {
	  		if(hub.riders[i] === rider) {
	  			console.log('Re-queuing rider ' + rider);
	  			hub.riders.splice(i, 1); // In-place removal
	  			hub.save(function(err) {
					if(err)
						res.send(500, err);
					else
						res.send(200);
			  	});
	  			break;
	  		}
	  	}
	  } else {
	  	return res.send(400, "No rider");
	  }
	});
});

module.exports = router;
