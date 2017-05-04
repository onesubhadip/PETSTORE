'use strict';
/*
 * Directive for displaying individual categories in the left side bar in the UI
 */
app.directive('category', function() {
	return {
		restrict:'E',
		template: '<li class="clearfix" ng-click="updateCategoryFilter()"><div class="sidebar-label common-customCheckbox"><input type="checkbox" value="{{name}}"><p class="count pull-right">({{count}})</p><a>{{name}}</a><div class="common-checkboxIndicator {{isSelected ? \'checked\':\'\'}}"></div></div></li>',
		replace:true,
		scope:{
			name: '@',
			count: '@'
		},
		controller : ['$scope', 'Filters', function($scope, Filters){
			$scope.isSelected = false;
			$scope.updateCategoryFilter = function(){
				$scope.isSelected=!$scope.isSelected;
				Filters.updateCategory($scope.name);
			}
		}]
	}
});

/*
 * Directive for displaying individual status in the left side bar
 */
app.directive('status', function() {
	return {
		restrict:'E',
		template: '<li class="clearfix" ng-click="updateStatusFilter()"><div class="sidebar-label common-customCheckbox"><input type="radio" value="{{name}}"><p class="count pull-right">({{count}})</p><a>{{name}}</a><div class="common-checkboxIndicator {{isSelected ? \'checked\':\'\'}}"></div></div></li>',
		replace:true,
		scope:{
			name: '@',
			count: '@'
		},
		controller : ['$scope', 'Filters', function($scope, Filters){
			$scope.isSelected = false;
			$scope.updateStatusFilter = function(){
				$scope.isSelected=!$scope.isSelected;
				Filters.updateStatus($scope.name);
			}
		}]
	}
});