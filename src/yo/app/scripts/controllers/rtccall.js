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
      var roomId ="leGrasCestLaVie";
      var host = window.location.host;
      var port = window.location.port;
      // if grunt serve
      if (port ==9000) {
          host =  window.location.hostname +":8080";
      }
      console.log("ws://"+host+"/webRtcSignaling");
       var signalingChannel = new WebSocket("ws://"+host+"/webRtcSignaling");
       var pc;
       var configuration = { iceServers: [{ url: "stun:stun.l.google.com:19302" }]};

       // run start(true) to initiate a call
       function start(isCaller) {
           pc = new RTCPeerConnection(configuration);

           // send any ice candidates to the other peer
           pc.onicecandidate = function (evt) {
               signalingChannel.send(JSON.stringify({ "candidate": evt.candidate , messageType :"CANDIDATE", roomId : roomId}));
           };

           // once remote stream arrives, show it in the remote video element
           pc.onaddstream = function (evt) {
            // remoteView.src = URL.createObjectURL(evt.stream);
                $scope.$apply(function(){$scope['partnerView']=$sce.trustAsResourceUrl( URL.createObjectURL(evt.stream)) ;})

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
                   pc.createAnswer( gotDescription);

               function gotDescription(desc) {
                   pc.setLocalDescription(desc);
                   signalingChannel.send(JSON.stringify({ "sdp": desc , messageType :"SDP", roomId :roomId}));
               }
           }, function() {});
       }

       signalingChannel.onmessage = function (evt) {
           if (!pc)
               start(false);

           var signal = JSON.parse(evt.data);
           if (signal.sdp)
               pc.setRemoteDescription(new RTCSessionDescription(signal.sdp));
           else if (signal.candidate)
               pc.addIceCandidate(new RTCIceCandidate(signal.candidate));
       };

       this.createRoom = function(roomId) {
          function create() {
            signalingChannel.send(JSON.stringify({ messageType :"CREATE", roomId :roomId}));
            start(true);
          }
          if (signalingChannel.readyState==1) {
            create();
          }  else {
              signalingChannel.onopen= create;
          }

       }
       this.joinRoom= function(roomId) {
           function join() {
             signalingChannel.send(JSON.stringify({ messageType :"JOIN", roomId :roomId}));

           }
           if (signalingChannel.readyState==1) {
             join();
           }  else {
               signalingChannel.onopen= join;
           }

       }

       if(partnerId==123) {
          this.createRoom(roomId);
       } else {
          this.joinRoom(roomId)
       }
  });
