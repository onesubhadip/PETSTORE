'use strict';

app.controller('PetController', ['$scope', 'PetService', function($scope, PetService) {
	var petCtrl = this;
	petCtrl.pets=[];
    petCtrl.uniqueCategories=[];
    
    petCtrl.getPetListByStatus = function(inventoryStatus){
    	    petCtrl.pets = PetService.petResource().getByStatus({ status: inventoryStatus }, function(data){
                petCtrl.uniqueCategories = PetService.getDistinctCategoty(data)
    	    },
    	    function(err){
    	    	console.log('err',err);
    	    });

    } 

    //Initially loading all pets   
    petCtrl.getPetListByStatus('All');
}]);
