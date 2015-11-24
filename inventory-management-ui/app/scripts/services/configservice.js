'use strict';

/**
 * @ngdoc service
 * @name sbAdminApp.configService
 * @description
 * # configService
 * Service in the sbAdminApp.
 */
angular.module('sbAdminApp')
  .service('configService', function () {
  	return {
  		serviceBase: '../api'
  	};
  });
