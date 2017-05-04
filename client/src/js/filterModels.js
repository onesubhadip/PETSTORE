'use strict';
/*
 * Utility service to keep track of the applied filter selections on the left side bar.
 */
app.service('Filters', ['$filter', function($filter) {
    var models = this;
    models.category = [];
    models.defaultStatus ="ALL";
    models.status = models.defaultStatus;
    models.getCategory = function(){
    	return models.category;
    }
    
    models.updateCategory = function(categoryName) {
    	var matches = $filter('filter')(models.category, categoryName);
		if(matches.length < 1){
			models.category.push(categoryName);
		}else{
			models.category = $filter('filter')(models.category, '!'+categoryName, true);
		}
    }
    
    models.getStatus = function(){
    	return models.status;
    }
    
    models.updateStatus = function(statusName) {
		if(models.status.toUpperCase() === statusName.toUpperCase()){
			models.status = models.defaultStatus;
		}else{
			models.status = statusName;
		}
    }
}]);