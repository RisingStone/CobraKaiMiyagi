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
	    var max = req.query.distance || 100;

	    // Max distance to radians: radius of earth is approx 6371km.
	    max /= 6371;

	    // long, lat coordinates (notice order!)
	    var coords = [];
	    coords[0] = req.query.longitude;
	    coords[1] = req.query.latitude;

	    // get nearby drivers
	    Driver.find({
	      location: {
	        $near: coords,
	        $maxDistance: maxDistance
	      }
	    }).limit(limit).exec(function(err, drivers) {
	      if (err) return res.json(500, err);
	      res.json(200, drivers);
	    });
	}
});

module.exports = router;
