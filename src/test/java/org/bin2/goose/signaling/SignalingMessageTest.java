package org.bin2.goose.signaling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by benoi_000 on 3/25/2015.
 */
public class SignalingMessageTest {

    @Test
    public void testGsonRead() {
        Gson gson = new GsonBuilder().create();
        String text ="{\"sdp\":{\"sdp\":\"v=0\\r\\no=- 2857903866539634347 2 IN IP4 127.0.0.1\\r\\ns=-\\r\\nt=0 0\\r\\na=group:BUNDLE audio video\\r\\na=msid-semantic: WMS ssEju2JTem0ZxeFvRnng0uMrC8VDIDQky0Y5\\r\\nm=audio 9 RTP/SAVPF 111 103 104 9 0 8 106 105 13 126\\r\\nc=IN IP4 0.0.0.0\\r\\na=rtcp:9 IN IP4 0.0.0.0\\r\\na=ice-ufrag:R3WsuSpTLqefOpak\\r\\na=ice-pwd:sXT4uj4qb1ZCBVHAzNWSnoZN\\r\\na=ice-options:google-ice\\r\\na=fingerprint:sha-256 A8:78:62:AC:0B:D2:8E:00:A4:59:04:AE:50:2F:A7:89:3D:97:01:DC:74:DF:60:63:90:A5:03:CB:38:17:E7:EB\\r\\na=setup:actpass\\r\\na=mid:audio\\r\\na=extmap:1 urn:ietf:params:rtp-hdrext:ssrc-audio-level\\r\\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\\r\\na=sendrecv\\r\\na=rtcp-mux\\r\\na=rtpmap:111 opus/48000/2\\r\\na=fmtp:111 minptime=10; useinbandfec=1\\r\\na=rtpmap:103 ISAC/16000\\r\\na=rtpmap:104 ISAC/32000\\r\\na=rtpmap:9 G722/8000\\r\\na=rtpmap:0 PCMU/8000\\r\\na=rtpmap:8 PCMA/8000\\r\\na=rtpmap:106 CN/32000\\r\\na=rtpmap:105 CN/16000\\r\\na=rtpmap:13 CN/8000\\r\\na=rtpmap:126 telephone-event/8000\\r\\na=maxptime:60\\r\\na=ssrc:1116493156 cname:eKsJR0WAcnKFt/0j\\r\\na=ssrc:1116493156 msid:ssEju2JTem0ZxeFvRnng0uMrC8VDIDQky0Y5 2c80c2f3-da59-41a1-8354-d501020c9ff0\\r\\na=ssrc:1116493156 mslabel:ssEju2JTem0ZxeFvRnng0uMrC8VDIDQky0Y5\\r\\na=ssrc:1116493156 label:2c80c2f3-da59-41a1-8354-d501020c9ff0\\r\\nm=video 9 RTP/SAVPF 100 116 117 96\\r\\nc=IN IP4 0.0.0.0\\r\\na=rtcp:9 IN IP4 0.0.0.0\\r\\na=ice-ufrag:R3WsuSpTLqefOpak\\r\\na=ice-pwd:sXT4uj4qb1ZCBVHAzNWSnoZN\\r\\na=ice-options:google-ice\\r\\na=fingerprint:sha-256 A8:78:62:AC:0B:D2:8E:00:A4:59:04:AE:50:2F:A7:89:3D:97:01:DC:74:DF:60:63:90:A5:03:CB:38:17:E7:EB\\r\\na=setup:actpass\\r\\na=mid:video\\r\\na=extmap:2 urn:ietf:params:rtp-hdrext:toffset\\r\\na=extmap:3 http://www.webrtc.org/experiments/rtp-hdrext/abs-send-time\\r\\na=sendrecv\\r\\na=rtcp-mux\\r\\na=rtpmap:100 VP8/90000\\r\\na=rtcp-fb:100 ccm fir\\r\\na=rtcp-fb:100 nack\\r\\na=rtcp-fb:100 nack pli\\r\\na=rtcp-fb:100 goog-remb\\r\\na=rtpmap:116 red/90000\\r\\na=rtpmap:117 ulpfec/90000\\r\\na=rtpmap:96 rtx/90000\\r\\na=fmtp:96 apt=100\\r\\na=ssrc-group:FID 2809938744 3134466564\\r\\na=ssrc:2809938744 cname:eKsJR0WAcnKFt/0j\\r\\na=ssrc:2809938744 msid:ssEju2JTem0ZxeFvRnng0uMrC8VDIDQky0Y5 8968c4c3-6647-4d81-aaf1-f4820ef0ba0b\\r\\na=ssrc:2809938744 mslabel:ssEju2JTem0ZxeFvRnng0uMrC8VDIDQky0Y5\\r\\na=ssrc:2809938744 label:8968c4c3-6647-4d81-aaf1-f4820ef0ba0b\\r\\na=ssrc:3134466564 cname:eKsJR0WAcnKFt/0j\\r\\na=ssrc:3134466564 msid:ssEju2JTem0ZxeFvRnng0uMrC8VDIDQky0Y5 8968c4c3-6647-4d81-aaf1-f4820ef0ba0b\\r\\na=ssrc:3134466564 mslabel:ssEju2JTem0ZxeFvRnng0uMrC8VDIDQky0Y5\\r\\na=ssrc:3134466564 label:8968c4c3-6647-4d81-aaf1-f4820ef0ba0b\\r\\n\",\"type\":\"offer\"},\"messageType\":\"SDP\",\"roomId\":\"leGrasCestLaVie\"}";

        SignalingMessage sm = gson.fromJson(text, SignalingMessage.class);
        Assert.assertEquals(sm.getMessageType(), SignalingMessage.Type.SDP);

    }
}
