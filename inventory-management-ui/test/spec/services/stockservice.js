'use strict';

describe('Service: StockService', function () {

  // load the service's module
  beforeEach(module('sbAdminApp'));

  // instantiate service
  var StockService;
  beforeEach(inject(function (_StockService_) {
    StockService = _StockService_;
  }));

  it('should do something', function () {
    expect(!!StockService).toBe(true);
  });

});
