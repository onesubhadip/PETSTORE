'use strict';

app.controller('PetController', ['$scope', 'PetService', 'Filters', function($scope, PetService, Filters) {
	var petCtrl = this;
	petCtrl.pets=[];
    petCtrl.uniqueCategories=[];
    petCtrl.uniqueStatus=[];
    
    petCtrl.getPetListByStatus = function(inventoryStatus){
    	    petCtrl.pets = PetService.petResource().getByStatus({ status: inventoryStatus }, function(data){
                petCtrl.uniqueCategories = PetService.getDistinctCategoty(data);
                if(inventoryStatus.toUpperCase()==="ALL".toUpperCase()){
                    petCtrl.uniqueStatus = PetService.getDistinctStatus(data);
                }
    	    },
    	    function(err){
    	    	console.log('err',err);
    	    });

    } 

    petCtrl.getPetListByCategories = function(categories){
            petCtrl.pets = PetService.petResource().getByCategories({ categories: categories }, function(data){
                //petCtrl.uniqueCategories = PetService.getDistinctCategoty(data)
            },
            function(err){
                console.log('err',err);
            });

    } 

    $scope.$watch(function() { return Filters.getStatus(); }, function(statusToFilter){
        petCtrl.getPetListByStatus(statusToFilter);
        /*petCtrl.pets = PetService.filterByCategory(petCtrl.pets, categoriesToFilter);*/
    }, true);
    $scope.$watch(function() { return Filters.getCategory(); }, function(categoriesToFilter){
        petCtrl.getPetListByCategories(categoriesToFilter);
        /*petCtrl.pets = PetService.filterByCategory(petCtrl.pets, categoriesToFilter);*/
    }, true);


    //Initially loading all pets   
    petCtrl.getPetListByStatus('All');    
}]);
