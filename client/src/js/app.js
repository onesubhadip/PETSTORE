'use strict';

/*
 * Entry point of petstore front end application
 */
angular.module('PETSTORE', [
	'ngRoute',
	'ngResource',
	'pageslide-directive'
])
.config(['$routeProvider', function ($routeProvider) {
	$routeProvider.when('/', {
		redirectTo: '/home'
	}).when('/home', {
		templateUrl: '/petstore/templates/petStoreHome.html',
		reloadOnSearch: false
	}).when('/petlist', {
		templateUrl: '/petstore/templates/petGrid.html',
		reloadOnSearch: false
	})
	.otherwise('/');
}]);

var app = angular.module('PETSTORE');