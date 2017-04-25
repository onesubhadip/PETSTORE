'use strict';

app.directive('category', function() {
	return {
		restrict:'E',
		template: '<li class="clearfix"><div class="sidebar-label common-customCheckbox"><input type="checkbox" value="{{name}}"><p class="count pull-right">({{count}})</p><a>{{name}}</a><div class="common-checkboxIndicator"></div></div></li>',
		replace:true,
		scope:{
			name: '@',
			count: '@'
		}
	}
});