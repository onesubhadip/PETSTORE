'use strict';

app.controller('PetController', ['$scope', 'PetService', 'Filters', function($scope, PetService, Filters) {
    var petCtrl = this;
    petCtrl.pets = [];
    petCtrl.uniqueCategories = [];
    petCtrl.uniqueStatus = [];

    //the pet object in question
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
    //Method to reset the default pet object
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

    //Method to save a new pet
    petCtrl.savePet = function() {
        petCtrl.petObjToAdd.photoUrls.push(petCtrl.tempImageForPost);
        PetService.savePet(petCtrl.petObjToAdd).then(function(response) {
            console.log('data', response.data);
            if (petCtrl.isAddFormOpen) { petCtrl.isAddFormOpen = false; }
            petCtrl.clearForm();
        }, function(err) {
            console.log('Error: ', err.data.errorMessage);
        });
    }

    //Method to get all pets for a given status
    petCtrl.getPetListByStatus = function(inventoryStatus) {
        petCtrl.pets = PetService.petResource().getByStatus({ status: inventoryStatus }, function(data) {
                petCtrl.uniqueCategories = PetService.getDistinctCategoty(data);
                if (inventoryStatus.toUpperCase() === "ALL".toUpperCase()) {
                    petCtrl.uniqueStatus = PetService.getDistinctStatus(data);
                    console.log(data);
                }
            },
            function(err) {
                console.log('err', err);
            });
    }

    //Method to get all pets for a given list of categories
    petCtrl.getPetListByCategories = function(categories) {
        petCtrl.pets = PetService.petResource().getByCategories({ categories: categories }, function(data) {
                //petCtrl.uniqueCategories = PetService.getDistinctCategoty(data)
            },
            function(err) {
                console.log('err', err);
            });

    }

    //Watchers on the filters applied on the left side bar of the UI.
    $scope.$watch(function() {
        return Filters.getStatus();
    }, function(statusToFilter) {
        petCtrl.getPetListByStatus(statusToFilter);
    }, true);
    $scope.$watch(function() {
        return Filters.getCategory();
    }, function(categoriesToFilter) {
        petCtrl.getPetListByCategories(categoriesToFilter);
    }, true);

    //When a pet is removed, then to update the grid this is called.
    $scope.$on('isNewFetchRequired', function(event, data) {
        if(data !== undefined && data.required){
            petCtrl.getPetListByStatus("All");
        }
    });

    //Utility code for controlling the sliding form for adding new pet.
    petCtrl.isAddFormOpen = false;
    petCtrl.size = '100px';
    petCtrl.toggleAddPetForm = function() {
        petCtrl.isAddFormOpen = !petCtrl.isAddFormOpen;
    }


}]);
