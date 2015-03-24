package org.bin2.goose.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by benoitroger on 23/03/15.
 */
@Component
public class SignalingWebsocketHandler  implements WebSocketHandler{
    private static Logger logger= LoggerFactory.getLogger(SignalingWebsocketHandler.class);
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("connection established {} " ,session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        logger.info("handleMessage {} {}" ,session, message.getPayload() );
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
