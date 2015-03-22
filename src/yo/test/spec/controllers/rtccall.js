'use strict';

describe('Controller: RtccallCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var RtccallCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    RtccallCtrl = $controller('RtccallCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
