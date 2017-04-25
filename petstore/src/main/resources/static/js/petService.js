'use strict';

app.factory('PetService', ['$resource', function($resource) {

    var petResource = function() {
        return $resource('/petstore/pet/status/:pet', { pet: '@pet' }, {
            getByStatus: {
                method: "GET",
                isArray: true,
                url: "/petstore/pet/status/:status"
            }
        });
    }
    var getDistinctCategoty = function(pets){
            var list = {};
            pets.forEach(function (pet) {
                if (list[pet.category.name] === undefined) {
                     list[pet.category.name] = 1;
                } else {
                   list[pet.category.name] += 1;
                }
            }); 
            var uniqueItems = [];    
            Object.keys(list).forEach(function(key){      
              uniqueItems.push({  
                 name :key,
                 count: list[key]
              });    
            });
            return uniqueItems;
    }

    return {
    	petResource : petResource,
    	getDistinctCategoty : getDistinctCategoty
    }

}]);
