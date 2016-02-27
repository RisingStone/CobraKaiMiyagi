// import the necessary modules
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// model creation

var DriverModel = function() {
  var DriverSchema = new Schema({
    name: String,
    location: {
    	type: {
    		type: String
    	},
    	coordinates: [Number]
    }
  });
  DriverSchema.index({ location: '2dsphere' });
  // register the mongoose model
  mongoose.model('Driver', DriverSchema);
};

// create an export function to encapsulate the model creation
module.exports = DriverModel;