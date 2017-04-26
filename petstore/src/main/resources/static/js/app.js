'use strict';

angular.module('PETSTORE', [
	'ngRoute',
	'ui.bootstrap',
	'ngResource',
	'pageslide-directive'
])
.config(['$routeProvider', function ($routeProvider) {
	$routeProvider.when('/', {
		redirectTo: '/home'
	}).when('/home', {
		templateUrl: '/petstore/htmls/petStoreHome.html',
		reloadOnSearch: false
	}).when('/petlist', {
		templateUrl: '/petstore/htmls/petGrid.html',
		reloadOnSearch: false
	})
	.otherwise('/');
}]);

var app = angular.module('PETSTORE');