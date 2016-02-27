// import the necessary modules
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// model creation
var HubModel = function() {
  var HubSchema = new Schema({
    name: String,
    location: {
    	type: {
    		type: String
    	},
    	coordinates: [Number]
    }
  });
  // register the mongoose model
  mongoose.model('Hub', HubSchema);
};

// create an export function to encapsulate the model creation
module.exports = HubModel;