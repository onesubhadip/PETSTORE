'use strict';

app.directive('petInfo', function() {
	return {
		restrict:'AE',
		templateUrl: '/petstore/htmls/petInfo.html',
		replace:true,
		scope:{
			pet: "="
		}
	}

});