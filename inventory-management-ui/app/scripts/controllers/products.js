'use strict';

/**
 * @ngdoc function
 * @name sbAdminApp.controller:ProductsCtrl
 * @description
 * # ProductsCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('ProductsCtrl', ['$scope', 'ProductService', function ($scope, ProductService) {
  	
  	$scope.products = [];

  	ProductService.getAllProducts().then(function(response) {
  		if (response.status == 200) {
  			$scope.products = response.data;
  		}

  	}, function(response) {});

  }]);
