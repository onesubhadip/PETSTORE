'use strict';

app.directive('petStoreHeader', function() {
	return {
		restrict:'E',
		templateUrl: '/petstore/htmls/header.html',
		replace:true,
		scope:{}
	}
});