'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('SettingsCtrl', function ($scope,$sce) {
  var localStream;
 function gotStream(stream) {
    localStream = stream;
    var url = URL.createObjectURL(stream);

     $scope.$apply(function(){$scope['selfView']=$sce.trustAsResourceUrl(url) ;})

     $scope.$on('$destroy', function iVeBeenDismissed() {
        localStream.stop();
     })
 }

 navigator.getUserMedia({audio:true, video:true}, gotStream, function() {});
  });
