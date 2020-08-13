define([
  'backbone'
], function (
  Backbone
) {

    var Asset = Backbone.Model.extend({
    	
  	  urlRoot: "/assets",
  	  
  	  idAttribute: "assetName",
  	  
  	defaults : {
  		assetName : null,
  		assetValue : null
  	},
  	
    validate: function(attributes) { 
    	if ( !attributes.assetName ) {  
            return 'please enter asset name ';  
         }
    	if ( !attributes.assetValue) {  
            return 'please enter asset value';  
         }
    	if ( isNaN(attributes.assetValue)) {  
            return 'please enter number for asset value';  
         }
        if ( attributes.assetValue < 0 ||  attributes.assetValue > 100000000) {  
           return 'please enter the valid number for asset Value ';  
        }  
     }
  });

    return Asset;
  });

