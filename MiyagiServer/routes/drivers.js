var express = require('express');
var router = express.Router();
var mongoose = require('mongoose');
var Driver = mongoose.model('Driver');

/* GET all drivers listing. */
router.get('/', function(req, res, next) {
	Driver.find({}, function(err, drivers) {
	    if (!err) { 
	        res.json(200, drivers);
	    } else {
	    	throw err;
	    }
	});
});

module.exports = router;
