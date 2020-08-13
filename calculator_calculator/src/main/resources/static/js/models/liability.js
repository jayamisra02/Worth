define([
  'backbone'
], function (
  Backbone
) {

    var Liability = Backbone.Model.extend({
    	
    	urlRoot: "/liabilities",
    	
    	idAttribute: "liabilityName",
    	
    	defaults : {
    		liabilityName : null,
    		liabilityValue : null
    	},
    	
    	 validate: function(attributes) { 
    	    	if ( !attributes.liabilityName ) {  
    	            return 'please enter liability name ';  
    	         }
    	    	if ( !attributes.liabilityValue) {  
    	            return 'please enter liability value';  
    	         }
    	    	if ( isNaN(attributes.liabilityValue)) {  
    	            return 'please enter number for liability value';  
    	         }
    	        if ( attributes.liabilityValue < 0 ||  attributes.liabilityValue > 100000000) {  
    	           return 'please enter the valid number for liability Value ';  
    	        }  
    	     }
    });
    return Liability;
  });
