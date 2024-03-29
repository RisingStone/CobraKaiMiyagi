var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var async = require('async');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var driver = require('./driver')();
var Drivers = mongoose.model('Driver');
var hub = require('./hub')();
var Hubs = mongoose.model('Hub');

var fs = require('fs');
var path = require('path');
var routes = require('./routes/index');
var drivers = require('./routes/drivers');
var hubs = require('./routes/hubs');

// Bootstrap mongoose and load dummy data
mongoose.connect('mongodb://localhost/miyagi_db', function(err) {
  if (err) throw err;

  /*
  // load driver data from file and transform it to Object
  var driverdata = JSON.parse(fs.readFileSync(path.join(__dirname, 'drivers.json'), 'utf8'));

  // clean db and load new data
  Drivers.remove(function() {
    async.each(driverdata, function(item, callback) {
      // create a new location
      Drivers.create(item, callback);
    }, function(err) {
      if (err) throw err;
    });
  });
  */

  /*
  // load driver data from file and transform it to Object
  var hubdata = JSON.parse(fs.readFileSync(path.join(__dirname, 'hubs.json'), 'utf8'));

  // clean db and load new data
  Hubs.remove(function() {
    async.each(hubdata, function(item, callback) {
      // create a new location
      Hubs.create(item, callback);
    }, function(err) {
      if (err) throw err;
    });
  });
*/
});

var allowCrossDomain = function(req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'POST,GET,OPTIONS,HEAD,DELETE');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    next();
}
var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(allowCrossDomain);
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', routes);
app.use('/drivers', drivers);
app.use('/hubs', hubs);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});


module.exports = app;
