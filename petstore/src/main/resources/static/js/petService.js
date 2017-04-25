'use strict';

app.factory('PetService', ['$resource', function($resource){
	return $resource('/petstore/pet/status/:pet',
	{ pet: '@pet'},
	{ getByStatus : {
		method: "GET",
		isArray: true,
		url: "/petstore/pet/status/:status"
	}
	});
}]);