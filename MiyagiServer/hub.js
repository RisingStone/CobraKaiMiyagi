// import the necessary modules
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

// model creation
var HubModel = function() {
  var HubSchema = new Schema({
    name: String,
    visible: Boolean,
    riders: { type: Array },
    drivers: { type: Array },
    area: {
    	type: { type: String },
    	coordinates: { type: Array }
    }
  });
  HubSchema.index({ area: '2dsphere' });
  // register the mongoose model
  mongoose.model('Hub', HubSchema);
};

// create an export function to encapsulate the model creation
module.exports = HubModel;