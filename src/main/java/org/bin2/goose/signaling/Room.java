package org.bin2.goose.signaling;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by benoi_000 on 3/25/2015.
 */
public class Room {
    private final String name;
    private final Map<String,UserRtcInfo> userRtcInfos = Maps.newConcurrentMap();

    public Room(String name) {
        this.name = name;
    }

    public boolean addSignalingMessage(String sessionId, SignalingMessage msg, String value) {
        UserRtcInfo info = userRtcInfos.get(sessionId);
        boolean isNew=info==null;
        if (info==null) {
            info = new UserRtcInfo(sessionId);
            userRtcInfos.put(sessionId,info);
        }
        switch (msg.getMessageType()) {
            case CANDIDATE: info.addCandidate(value);
                break;
            case SDP:info.setDSP(value);
                break;
        }
        return isNew;
    }

    public Map<String, UserRtcInfo> getUserRtcInfos() {
        return userRtcInfos;
    }

    public String getName() {
        return name;
    }
}
