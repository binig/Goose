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
 function gotStream(stream) {
     window.AudioContext = window.AudioContext || window.webkitAudioContext;
     var audioContext = new AudioContext();

     // Create an AudioNode from the stream
     var mediaStreamSource = audioContext.createMediaStreamSource(stream);

     // Connect it to destination to hear yourself
     // or any other node for processing!
     mediaStreamSource.connect(audioContext.destination);
    var url = URL.createObjectURL(stream);
 $scope.trustSrc = function(src) {
    return $sce.trustAsResourceUrl(src);
  }
     $scope.$apply(function(){$scope['selfView']=url ;})
 }

 navigator.getUserMedia({audio:true, video:true}, gotStream, function() {});
  });
