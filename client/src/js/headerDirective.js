'use strict';

//Directive containing header for pet store UI.
app.directive('petStoreHeader', function() {
	return {
		restrict:'E',
		templateUrl: '/petstore/templates/header.html',
		replace:true,
		scope:{}
	}
});