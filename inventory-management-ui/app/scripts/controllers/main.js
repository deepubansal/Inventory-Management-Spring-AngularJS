'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('MainCtrl', ['$scope', '$http','StockService', 
  	function($scope,$http, StockService) {
  		$scope.inventoryItems = [];
		StockService.getLatestStocks().then(function(response) {
  		if (response.status == 200) {
  			$scope.inventoryItems = response.data;
  		}

  	}, function(response) {});


  }]);
