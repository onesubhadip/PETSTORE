'use strict';

app.directive('petStoreHeader', function() {
	return {
		restrict:'E',
		templateUrl: '/petstore/templates/header.html',
		replace:true,
		scope:{}
	}
});