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


       var signalingChannel = new WebSocket("ws://localhost:8080/webRtcSignaling");
       var pc;
       var configuration = { iceServers: [{ url: "stun:stun.l.google.com:19302" }]};

       // run start(true) to initiate a call
       function start(isCaller) {
           pc = new RTCPeerConnection(configuration);

           // send any ice candidates to the other peer
           pc.onicecandidate = function (evt) {
               signalingChannel.send(JSON.stringify({ "candidate": evt.candidate , messageType :"CANDIDATE", roomId :"leGrasCestLaVie"}));
           };

           // once remote stream arrives, show it in the remote video element
           pc.onaddstream = function (evt) {
               remoteView.src = URL.createObjectURL(evt.stream);
           };

           // get the local stream, show it in the local video element and send it
           navigator.getUserMedia({ "audio": true, "video": true }, function (stream) {
                $scope.$apply(function(){$scope['selfView']=$sce.trustAsResourceUrl( URL.createObjectURL(stream)) ;})

                $scope.$on('$destroy', function iVeBeenDismissed() {
                     stream.stop();
                 })

               pc.addStream(stream);

               if (isCaller)
                   pc.createOffer(gotDescription);
               else
                   pc.createAnswer(pc.remoteDescription, gotDescription);

               function gotDescription(desc) {
                   pc.setLocalDescription(desc);
                   signalingChannel.send(JSON.stringify({ "sdp": desc , messageType :"SDP", roomId :"leGrasCestLaVie"}));
               }
           }, function() {});
       }

       signalingChannel.onmessage = function (evt) {
           if (!pc)
               start(false);

           var signal = JSON.parse(evt.data);
           if (signal.sdp)
               pc.setRemoteDescription(new RTCSessionDescription(signal.sdp));
           else
               pc.addIceCandidate(new RTCIceCandidate(signal.candidate));
       };
       start(partnerId==123);
  });
