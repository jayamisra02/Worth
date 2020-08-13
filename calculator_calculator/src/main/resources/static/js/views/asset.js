define([
  'jquery',
  'underscore',
  'backbone',
  'models/asset'
 
], function($, _, Backbone,Asset){
  var assetItemView = Backbone.View.extend({
		model : new Asset(),
		tagName : 'tr',
		initialize : function() {
			this.template = _.template($('.assets-list-template').html());
			 
		},
		
		events:{
			'keypress .avalue': 'update',
			'click .edit-asset': 'edit',
			'click .update-asset': 'update',
			'click .cancel': 'cancel',
			'click .delete-asset': 'deleteAsset'
		},
		
		update: function(e){
			if ( e.which === 13 ){
				var aValue = this.$('.avalue').val();
				this.model.set('assetValue', aValue );
				//this.model.set('assetValue', $('.value-update').val());
				
				
				if(!this.model.isValid()){
					alert(this.model.validationError);
				}else{
				this.url = this.model.url + "/" + this.model.get('assetName');
				this.model.save({wait: true},{
	    			success: function(model, response, options){
	        			console.log("The asset has been updated");
	        		},
	        		error: function(model, xhr, options){
	        			console.log("Something went wrong while updating the asset. Response code:",xhr.status);
	        			if(xhr.status == 500){
	        			alert("Something went wrong while updating the asset or someone else updated the asset. Please try again");
	        			}
	        		}
	        		});
				}
			}
			
		},
		
		cancel: function(){
			this.render();
		},
		
		deleteAsset: function(){
			var answer = confirm("Are you sure you want to delete this Asset?");
			 if (answer) {
			this.model.destroy({wait: true},{
	            success: function() {
	                console.log("delete was a success");
	            },
			error: function(){
    			console.log("Something went wrong while deleting the record");
    		}
	        });
			
			 }
		},
		
		render : function() {
			this.$el.html(this.template(this.model.toJSON()));
			return this;
		},
		
		edit: function(){
			
			$('.edit-asset').hide();
			$('.delete-asset').hide();
			this.$('.update-asset').show();
			this.$('.cancel').show();
			
			var assetName = this.$('.name').html();
			var assetValue = this.$('.value').html();
			
		//	this.$('.name').html('<input type="text" class="form-control name-update" value="'+ assetName + '">');
			this.$('.value').html('<input type="text" class="form-control value-update" value="'+ assetValue + '">');
		}
	});
  // Our module now returns our view
  return assetItemView;
});



