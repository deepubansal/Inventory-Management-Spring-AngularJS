'use strict';

/**
 * @ngdoc service
 * @name sbAdminApp.products
 * @description
 * # productService
 * Service in the sbAdminApp.
 */
angular.module('sbAdminApp')
  .service('ProductService', ['$http', 'configService',function ($http, configService) {
  	return {
  		getAllProducts: function() {
  			return $http.get(configService.serviceBase + '/product');
  		},
  		addProduct: function(product) {
  			return $http.post(configService.serviceBase + '/product/create', product);
  		}
  	};
  }]);
