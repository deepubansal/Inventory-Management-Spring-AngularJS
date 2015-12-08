'use strict';

/**
 * @ngdoc function
 * @name sbAdminApp.controller:AddproductCtrl
 * @description
 * # AddproductCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('AddproductCtrl', ['$scope', '$state', 'ProductService', function ($scope, $state, ProductService) {
    $scope.product = {};
    $scope.product.identifiers = [];
    $scope.newIdentifier = "";
    $scope.addIdentifier = function () {
    	if ($scope.newIdentifier.trim() != "" && $scope.product.identifiers.indexOf($scope.newIdentifier) < 0 ) {
			$scope.product.identifiers.push($scope.newIdentifier);
      		$scope.newIdentifier = "";
    	}
    };
    $scope.removeIdentifier = function(index) {
    	$scope.product.identifiers.splice(index, 1);
    }
    $scope.addProduct = function() {
    	ProductService.addProduct($scope.product);
    	$state.go('^', {}, {reload : true});
    };
  }]);
