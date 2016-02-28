var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
var Hub = mongoose.model('Hub');

/* GET all hubs, or only hubs within given distance of a lat/long pair */
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

router.post('/', function(req, res, next) {
	console.log('Creating hub...');
	var newHub = new Hub(req.body);
	newHub.save(function(err) {
		if(err) res.send(500, err);
		else res.json(req.body);
	});
});

module.exports = router;
