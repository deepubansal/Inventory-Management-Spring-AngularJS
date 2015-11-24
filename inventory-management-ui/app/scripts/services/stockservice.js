'use strict';

/**
 * @ngdoc service
 * @name sbAdminApp.StockService
 * @description
 * # StockService
 * Service in the sbAdminApp.
 */
angular.module('sbAdminApp')
  .service('StockService', ['$http', 'configService',function ($http, configService) {
    return {
  		getLatestStocks: function() {
  			return $http.get(configService.serviceBase + '/stocks/latest');
  		}
  	};
  }]);
