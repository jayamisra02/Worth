define([ 'backbone', 'models/asset' ], function(Backbone, Asset) {

	var AssetList = Backbone.Collection.extend({
		model : Asset,
		url : "/assets",
		hasModel : function(input) {
			return !!this.get(input);
		}
	});

	return AssetList;

});