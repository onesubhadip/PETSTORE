'use strict';

angular.module('PETSTORE', [
	'ngRoute',
	'ui.bootstrap'
])
.config(function ($routeProvider) {
	$routeProvider.when('/', {
		templateUrl: '/petstore/htmls/main.html',
		reloadOnSearch: false
	}).otherwise('/');
});

var app = angular.module('PETSTORE');