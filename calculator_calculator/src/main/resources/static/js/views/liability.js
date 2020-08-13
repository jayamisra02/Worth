define([
  'jquery',
  'underscore',
  'backbone',
  'models/liability'
  
  // Using the Require.js text! plugin, we are loaded raw text
  // which will be used as our views primary template
 // 'text!templates/project/index.html'
], function($, _, Backbone,Liability){
  var liabilityItemView = Backbone.View.extend({
		model : new Liability(),
		tagName : 'tr',
		initialize : function() {
			this.template = _.template($('.liabilities-list-template').html());
		},
		
		events:{
			'keypress .lvalue': 'update',
			'click .edit-liability': 'edit',
			'click .update-liability': 'update',
			'click .cancel-liability': 'cancel',
			'click .delete-liability': 'deleteLiability'
		},
		edit: function(){
			$('.edit-liability').hide();
			$('.delete-liability').hide();
			this.$('.update-liability').show();
			this.$('.cancel-liability').show();
			
			var liabName = this.$('.lname').html();
			var liabValue = this.$('.lvalue').html();
			
		//	this.$('.name').html('<input type="text" class="form-control name-update" value="'+ assetName + '">');
			this.$('.lvalue').html('<input type="text" class="form-control value-update" value="'+ liabValue + '">');
		},
		update: function(e){
			if ( e.which === 13 ){
			//this.model.set('name', $('.name-update').val());
				var lValue = this.$('.lvalue').val();
				this.model.set('liabilityValue', lValue);
		//	this.model.set('liabilityValue', $('.value-update').val());
			
			if(!this.model.isValid()){
				alert(this.model.validationError);
			}else{
			this.url = this.model.url + "/" + this.model.get('liabilityName');
			this.model.save({},{
    			success: function(model, response, options){
        			console.log("The liability has been updated");
        		},
        		error: function(model, xhr, options){
        			console.log("Something went wrong while updating the liability");
        		}
        		});
			}
			}
		},
		cancel: function(){
			this.render();
		},
		deleteLiability: function(){
			var answer = confirm("Are you sure you want to delete this Liability?");
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
		}
	});

  // Our module now returns our view
  return liabilityItemView;
});
