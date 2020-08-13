require.config({
  paths: {
	  'jquery': '../lib/bower_components/jquery/dist/jquery',
    'underscore': '../lib/bower_components/underscore/underscore',
    'backbone': '../lib/bower_components/backbone/backbone'
  }

});

require([
  'app',
], function(App){
  // The "app" dependency is passed in as "App"
  App.initialize();
});