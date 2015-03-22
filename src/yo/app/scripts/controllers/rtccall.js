'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RtccallCtrl
 * @description
 * # RtccallCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('RtccallCtrl', function ($scope,$routeParams,$sce) {
      var partnerId = $routeParams.partnerId;
       var localStream;
       function gotStream(stream) {
          localStream = stream;
           window.AudioContext = window.AudioContext || window.webkitAudioContext;
           var audioContext = new AudioContext();

           // Create an AudioNode from the stream
           var mediaStreamSource = audioContext.createMediaStreamSource(stream);

           // Connect it to destination to hear yourself
           // or any other node for processing!
           mediaStreamSource.connect(audioContext.destination);
          var url = URL.createObjectURL(stream);

           $scope.$apply(function(){$scope['selfView']=$sce.trustAsResourceUrl(url) ;})

           $scope.$on('$destroy', function iVeBeenDismissed() {
              localStream.stop();
           })
       }

       navigator.getUserMedia({audio:true, video:true}, gotStream, function() {});
  });
