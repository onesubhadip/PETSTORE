'use strict';

/*
 * All services for pet resource related operations
 */
app.factory('PetService', ['$resource', '$filter', '$http', function($resource, $filter, $http) {

	/*
	 * CRUD operations on pet resource.
	 */
    var petResource = function() {
        return $resource('/petstore/pet/:petId', {petId : '@petId'}, {
            getByStatus: {
                method: "GET",
                isArray: true,
                url: "/petstore/pet/status/:status"
            },
            getByCategories: {
                method: "GET",
                isArray: true,
                url: "/petstore/pet/categories/"
            }
        });
    }
    
    //Specific method to POST a new pet
 	var savePet = function(pet){
		return $http.post('/petstore/pet/', pet);
 	} 
    
 	//Get distinct categories of pets from a given list of Pets.
    var getDistinctCategoty = function(pets) {
        var list = {};
        pets.forEach(function(pet) {
            if (list[pet.category.name] === undefined) {
                list[pet.category.name] = 1;
            } else {
                list[pet.category.name] += 1;
            }
        });
        var uniqueItems = [];
        Object.keys(list).forEach(function(key) {
            uniqueItems.push({
                name: key,
                count: list[key]
            });
        });
        return uniqueItems;
    }
    
    //Get distinct inventory status of pets from a given list of Pets.
    var getDistinctStatus = function(pets) {
        var list = {};
        pets.forEach(function(pet) {
            if (list[pet.status] === undefined) {
                list[pet.status] = 1;
            } else {
                list[pet.status] += 1;
            }
        });
        var uniqueItems = [];
        var total = 0;
        Object.keys(list).forEach(function(key) {
            uniqueItems.push({
                name: key,
                count: list[key]
            });
            total += list[key];
        });
        uniqueItems.push({
            name: "All",
            count: total
        });
        return uniqueItems;
    }

    return {
        petResource: petResource,
        getDistinctCategoty: getDistinctCategoty,
        getDistinctStatus: getDistinctStatus,
        savePet: savePet
    }

}]);
