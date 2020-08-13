define([
  'jquery',
  'underscore',
  'backbone',
  'views/asset'
  
], function($, _, Backbone,AssetView){
  var assetListView = Backbone.View.extend({
		/*model : assets,*/
		el : $('.assets-list'),
		initialize : function() {
			this.render();
			this.listenTo(this.model, 'change', this.render);
			this.listenTo(this.model, 'add', this.render);
			this.listenTo(this.model, 'remove', this.render);
			
		},
		render : function() {
			var self = this;
			this.$el.html('');
			this.model.fetch();
			
			var totalAssets = [];
			this.model.each(function( item ) {
	            this.renderAsset( item );
	            var total = parseFloat(item.get('assetValue'));
				totalAssets.push(total);
	        }, this );
		
		var sumAssets = _.reduce(totalAssets, function(memo, num){ return memo + num; }, 0);
		//console.log(sumAssets);
		$('#totalAssetsValue').text(this.numberWithCommas(sumAssets.toFixed(2)));
		var sumLiab = $('#totalLiabilitiesValue').text().replace(/,/g, '');
		var netWorth = (sumAssets-(parseFloat(sumLiab))).toFixed(2);
		$('#networth').text(this.numberWithCommas(netWorth));
			
			return this;
		},
		
		renderAsset: function( item ) {
	        var assetView = new AssetView({
	            model: item
	        });
	        this.$el.append( assetView.render().el );
	    },
	    
		numberWithCommas: function(x) {
		    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
		
	
	});
  
  return assetListView;
});


