'use strict';

app.controller('PetController', ['$scope', 'PetService', 'Filters', function($scope, PetService, Filters) {
    var petCtrl = this;
    petCtrl.pets = [];
    petCtrl.uniqueCategories = [];
    petCtrl.uniqueStatus = [];

    petCtrl.petObjToAdd = {
        "category": {
            "categoryId": null
        },
        "description": null,
        "name": null,
        "photoUrls": [],
        "status": "Available"
    };
    petCtrl.tempImageForPost = "";
    petCtrl.clearForm = function() {
        petCtrl.petObjToAdd = {
            "category": {
                "categoryId": null
            },
            "description": null,
            "name": null,
            "photoUrls": [],
            "status": "Available"
        };
        petCtrl.tempImageForPost = "";
    }

    petCtrl.savePet = function() {
        petCtrl.petObjToAdd.photoUrls.push(petCtrl.tempImageForPost);
        PetService.savePet(petCtrl.petObjToAdd).then(function(response) {
            console.log('data', response.data);
            if (petCtrl.isAddFormOpen) { petCtrl.isAddFormOpen = false; }
        }, function(err) {
            console.log('Error: ', err.data.errorMessage);
        });
    }

    petCtrl.getPetListByStatus = function(inventoryStatus) {
        petCtrl.pets = PetService.petResource().getByStatus({ status: inventoryStatus }, function(data) {
                petCtrl.uniqueCategories = PetService.getDistinctCategoty(data);
                if (inventoryStatus.toUpperCase() === "ALL".toUpperCase()) {
                    petCtrl.uniqueStatus = PetService.getDistinctStatus(data);
                }
            },
            function(err) {
                console.log('err', err);
            });
    }

    petCtrl.getPetListByCategories = function(categories) {
        petCtrl.pets = PetService.petResource().getByCategories({ categories: categories }, function(data) {
                //petCtrl.uniqueCategories = PetService.getDistinctCategoty(data)
            },
            function(err) {
                console.log('err', err);
            });

    }

    $scope.$watch(function() {
        return Filters.getStatus();
    }, function(statusToFilter) {
        petCtrl.getPetListByStatus(statusToFilter);
        /*petCtrl.pets = PetService.filterByCategory(petCtrl.pets, categoriesToFilter);*/
    }, true);
    $scope.$watch(function() {
        return Filters.getCategory();
    }, function(categoriesToFilter) {
        petCtrl.getPetListByCategories(categoriesToFilter);
        /*petCtrl.pets = PetService.filterByCategory(petCtrl.pets, categoriesToFilter);*/
    }, true);

    $scope.$on('isNewFetchRequired', function(event, data) {
        if(data !== undefined && data.required){
            petCtrl.getPetListByStatus("All");
        }
    });

    //Initially loading all pets   
    petCtrl.getPetListByStatus('All');




    petCtrl.isAddFormOpen = false;
    petCtrl.size = '100px';
    petCtrl.toggleAddPetForm = function() {
        petCtrl.isAddFormOpen = !petCtrl.isAddFormOpen;
    }


}]);
