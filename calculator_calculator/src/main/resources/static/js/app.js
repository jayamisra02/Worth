define([
  'jquery',
  'underscore',
  'backbone',
  'views/assetlist',
  'views/liabilitylist',
  'collections/assetlist',
  'collections/liabilitylist',
  'models/asset',
  'models/liability'
  
], function($, _, Backbone, AssetListView, LiabilityListView,Assets,Liabilities,Asset,Liability){

  var initialize = function(){
   
    	var assets = new Assets();
    	var liabilities = new Liabilities();
    	
    	//getTotalAssets();
    	$('.add-asset').on('click',function(){
    		var asset = new Asset({
    			assetName: $('.asset-name').val(),
    			assetValue: $('.asset-value').val(),
    		});
    		
    		asset.on("invalid", function(model, error) {
  			  alert(error);
  			});
    		
    		var asName  = $('.asset-name').val();
    		if(assets.hasModel(asName)){
    			alert("Asset already exists. Please add a new asset or update the existing asset below");
    		}else if(asset.isValid()){
    			$('.asset-name').val('');
        		$('.asset-value').val('');
        		console.log(asset.toJSON());
        		
        		var url = "/assets";
    			asset.save({},{ url:url, type: 'POST',
        			success: function(model, response, options){
        			assets.add(asset);
        			console.log("The asset has been saved");
        		},
        		error: function(model, xhr, options){
        			console.log("Something went wrong while saving the asset");
        		},
        		});
    			
    		}
    		
    		
    	});
    	
    	
    	//getTotalLiabilities();
    	$('.add-liability').on('click',function(){
    		var liability = new Liability({
    			liabilityName: $('.liability-name').val(),
    			liabilityValue: $('.liability-value').val()
    		});
    		
    		liability.on("invalid", function(model, error) {
  			  alert(error);
  			});
    		
    		var lbName  = $('.liability-name').val();
    		if(liabilities.hasModel(lbName)){
    			alert("Liability already exists. Please add a new liability or update the liability below");
    		}else if(liability.isValid()){
    		$('.liability-name').val('');
    		$('.liability-value').val('');
    		console.log(liability.toJSON());
    		
    		var url = "/liabilities";
    		liability.save({},{ url:url, type: 'POST',
    			success: function(model, response, options){
        			console.log("The liability has been saved");
        			liabilities.add(liability);
        		},
        		error: function(model, xhr, options){
        			console.log("Something went wrong while saving the liability");
        		}
        		});
    		}
    	});
    	
    	//getNetWorth();
    	
      var assetListView = new AssetListView({model: assets});
      assetListView.render();
      
      var liabilityListView = new LiabilityListView({model: liabilities});
      liabilityListView.render();
    
  };
  
  return {
    initialize: initialize
  };
});