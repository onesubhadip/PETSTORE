'use strict';

app.directive('petInfo', function() {
    return {
        restrict: 'AE',
        templateUrl: '/petstore/templates/petInfo.html',
        replace: true,
        scope: {
            pet: "="
        },
        controller: ['$scope', 'PetService', '$rootScope', function($scope, PetService, $rootScope) {

            $scope.removePet = function() {
            	console.log('pet', $scope.pet);
                PetService.petResource().remove($scope.pet, function(data) {
                    console.log('removed data', data);
                    $rootScope.$broadcast('isNewFetchRequired', {
  						required: true
					});
					console.log('$rootScope', $rootScope);
                }, function(err) {
                    console.log('err', err);
                });

            }
        }]
    }

});
