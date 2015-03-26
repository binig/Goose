package org.bin2.goose.signaling;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by benoi_000 on 3/25/2015.
 */
@Component
public class SignalingManagerImpl {
    private Map<String,Room> rooms = Maps.newConcurrentMap();

    public boolean addSignalingMessage(String roomName, String sessionId, SignalingMessage msg, String value) {
        Room room = rooms.get(roomName);
        Preconditions.checkNotNull(room);
        return room.addSignalingMessage(sessionId,msg, value);
    }

    public List<String> getMessageForRoom(String roomName, String sessionId) {
        Room room =rooms.get(roomName);
        final List<String> messages = Lists.newArrayList();
        room.getUserRtcInfos().values().stream().filter((UserRtcInfo u) ->  !u.getId().equals(sessionId)).forEach(
                (UserRtcInfo u) -> messages.addAll(u.getMessages())
        );
        return messages;
    }

    public List<String> getPartner(String roomId, String id) {
        Room room = rooms.get(roomId);
        List<String> partners = Lists.newArrayList();
        if (room!=null) {
           room.getUserRtcInfos().values().stream().map((UserRtcInfo u)-> u.getId())
                   .filter((String s) -> !id.equals(s)).forEach((String s)->partners.add(s));
        }
        return partners;
    }

    public void createRoom(String roomId, String id) {
        Preconditions.checkArgument(!rooms.containsKey(roomId));
        Room room = new Room(roomId);
        rooms.put(roomId,room);
    }
}
