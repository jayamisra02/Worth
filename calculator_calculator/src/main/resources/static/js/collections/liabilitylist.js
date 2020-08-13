define(['backbone','models/liability'], 
	function (Backbone,Liability) {

    var LiabilityList =Backbone.Collection.extend({
    	model: Liability,
  	    url : "/liabilities",
  	  	hasModel: function(input) {
	    return !!this.get(input);
	  }
  });

    return LiabilityList;

  });