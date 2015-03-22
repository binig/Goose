'use strict';

/**
 * @ngdoc service
 * @name webappApp.UserService
 * @description
 * # UserService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('UserService', function () {
    // AngularJS will instantiate a singleton by calling "new" on this function
    var self= this;
    self.getUsers = function() {
     return [
     {id:123,name:'alpha 1'},
     {id:124,name:'alpha 2'},
     {id:125,name:'beta 1'},
     {id:126,name:'delta 1'}
     ]
    }
  });
