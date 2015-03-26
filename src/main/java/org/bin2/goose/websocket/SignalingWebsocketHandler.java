package org.bin2.goose.websocket;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bin2.goose.signaling.SignalingManagerImpl;
import org.bin2.goose.signaling.SignalingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by benoitroger on 23/03/15.
 */
@Component
public class SignalingWebsocketHandler  implements WebSocketHandler{
    private static Logger logger= LoggerFactory.getLogger(SignalingWebsocketHandler.class);

    private Map<String, WebSocketSession> sessionById = Maps.newConcurrentMap();

    private Gson gson = new GsonBuilder().create();

    @Resource
    private SignalingManagerImpl signalingManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("connection established {} " ,session.getId());
        sessionById.put(session.getId(),session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("handleMessage {} {}" ,session.getId(), message.getPayload() );
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            String text =msg.getPayload();
            SignalingMessage sig = gson.fromJson(text, SignalingMessage.class);
            switch(sig.getMessageType()) {
                case CREATE:
                    signalingManager.createRoom(sig.getRoomId(),session.getId());
                    break;
                case JOIN:
                    List<String> existingMsges =signalingManager.getMessageForRoom(sig.getRoomId(), session.getId());
                    existingMsges.stream().map((String s) -> new TextMessage(s)).forEach(new MessageSender(session.getId()));

                    break;
                case SDP:
                case CANDIDATE:
                    signalingManager.addSignalingMessage(sig.getRoomId(),session.getId(),sig, text);
                    signalingManager.getPartner(sig.getRoomId(),session.getId()).stream().forEach(new MessageBroadcast(message));
                    break;
            }
        }

    }

    private  class MessageSender implements Consumer<WebSocketMessage<?>> {
        private final String destination;

        public MessageSender(String destination) {
            this.destination = destination;
        }

        @Override
        public void accept(WebSocketMessage<?> webSocketMessage) {
            try {
                sessionById.get(destination).sendMessage(webSocketMessage);
            } catch (IOException e) {
                logger.warn("error sending message to {} ",destination,e);
            }
        }
    }

    private  class MessageBroadcast implements Consumer<String> {
        private final WebSocketMessage<?> message;

        public MessageBroadcast(WebSocketMessage<?> message) {
            this.message = message;
        }

        @Override
        public void accept(String destination) {
            try {
                sessionById.get(destination).sendMessage(message);
            } catch (IOException e) {
                logger.warn("error sending message to {} ",destination,e);
            }
        }
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("handleMessage {}" ,session.getId(), exception );
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("connection closed {} {}  " ,session.getId(),closeStatus);
        sessionById.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
