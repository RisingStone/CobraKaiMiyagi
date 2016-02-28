var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
var Driver = mongoose.model('Driver');

/* GET all drivers listing. */
router.get('/', function(req, res, next) {
	var limit = req.query.limit || 100;

	if(!req.query || !req.query.longitude || !req.query.latitude) {
		Driver.find({}).limit(limit).exec(function(err, drivers) {
		    if (!err) { 
		        res.json(200, drivers);
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

	    // get nearby drivers
	    var query = Driver.find({})
	    	.where('location')
	    	.near({
	    		center: { type: 'Point', coordinates: pair },
	    		maxDistance: radius * 1609.34,
	    		spherical: true
	    	})
	    	.limit(limit)
	    	.exec(function(err, drivers) {
	      		if (err) return res.json(500, err);
	      		res.json(200, drivers);
	    	});
	}
});

router.post('/', function(req, res, next) {
	var newDriver = new Driver(req.body);
	console.log(req.body);
	newDriver.save(function(err) {
		if(err) res.send(500, err);
		else res.json(req.body);
	});
});

module.exports = router;
