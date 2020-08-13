define([
  'jquery',
  'underscore',
  'backbone',
  'views/liability'
 
], function($, _, Backbone,LiabilityView){
  var liabilityListView = Backbone.View.extend({
		/*model : liabilities,*/
		el : $('.liabilities-list'),
		initialize : function() {
			this.model.fetch();
			this.render();
			this.listenTo(this.model, 'change', this.render);
			this.listenTo(this.model, 'add', this.render);
			this.listenTo(this.model, 'remove', this.render);
			
		},
		render : function() {
			var self = this;
			this.$el.html('');
			this.model.fetch();
			
			var totalLiabilities = [];
			_.each(this.model.toArray(), function(liability) {
				self.$el.append((new LiabilityView({
					model : liability
				})).render().$el);
				
				 var total = parseFloat(liability.get('liabilityValue'));
				 totalLiabilities.push(total);
			});
			
			var sumLiab = _.reduce(totalLiabilities, function(memo, num){ return memo + num; }, 0);
			//console.log(sumLiab);
			$('#totalLiabilitiesValue').text(this.numberWithCommas(sumLiab.toFixed(2)));
			var sumAssets = $('#totalAssetsValue').text().replace(/,/g, '');
			var netWorth = ((parseFloat(sumAssets))-sumLiab).toFixed(2);
			$('#networth').text(this.numberWithCommas(netWorth));
			
			
			return this;
		},
		numberWithCommas: function(x) {
		    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}
	});
  // Our module now returns our view
  return liabilityListView;
});

