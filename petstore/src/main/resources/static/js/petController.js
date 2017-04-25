'use strict';

app.controller('PetController', ['$scope', 'PetService', function($scope, PetService) {
	var petCtrl = this;
	petCtrl.pets=[];
    
    petCtrl.getPetListByStatus = function(inventoryStatus){
    	    petCtrl.pets = PetService.PetResource.getByStatus({ status: inventoryStatus }, function(data){
				console.log('data', JSON.stringify(data));
    	    },
    	    function(err){
    	    	console.log('err',err);
    	    });

    } 

    //Initially loading all pets   
    petCtrl.getPetListByStatus('All');
}]);
