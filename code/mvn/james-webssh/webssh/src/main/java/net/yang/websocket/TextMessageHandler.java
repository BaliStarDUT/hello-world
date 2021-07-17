package net.yang.websocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yang.NodeShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TextMessageHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> users;

    @Autowired
    NodeShell nodeShell;

    static {
        users = new HashMap<String, WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        /*
         * 链接成功后会触发此方法，可在此处对离线消息什么的进行处理
         */
        users.put(session.getId(), session);
        String username = (String) session.getAttributes().get(Constants.DEFAULT_WEBSOCKET_USERNAME);
        System.out.println(username + " connect success ...");
        session.sendMessage(new TextMessage(username + " 链接成功!!"));
        nodeShell.setWebSocketSession(session);
        nodeShell.init_ssh();
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        /*
         * 前端 websocket.send() 会触发此方法 
         */
        System.out.println("message -> " + message.getPayload());
//        nodeShell.byteArrayInputStream.reset();
//        nodeShell.byteArrayInputStream.read(message.asBytes());
//        byte[] by =  nodeShell.byteArrayOutputStream.toByteArray();
//        TextMessage ret = new TextMessage(by);
//        session.sendMessage(ret);
        Map<String,String> map = new ObjectMapper().readValue(message.getPayload(),
                new TypeReference<Map<String,String>>() {
                });
        nodeShell.cmd_to_node(map.get("command"));

//        super.handleTextMessage(session, ret);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        System.err.println(exception.getMessage());
        System.out.println("websocket connection closed......");
        users.remove(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("websocket connection closed......");
        users.remove(session.getId());
        nodeShell.close_node_shell();
    }

    public void sendMessageToUser(String username, TextMessage message) {
        Iterator<Map.Entry<String, WebSocketSession>> it = userIterator();
        while (it.hasNext()) {
            WebSocketSession session = it.next().getValue();
            if (username.equals(session.getAttributes().get(Constants.DEFAULT_WEBSOCKET_USERNAME))) {
                try {
                    if (session.isOpen())
                        session.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessageToUsers(TextMessage message) {
        Iterator<Map.Entry<String, WebSocketSession>> it = userIterator();
        while (it.hasNext()) {
            WebSocketSession session = it.next().getValue();
            try {
                if (session.isOpen())
                    session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private Iterator<Map.Entry<String, WebSocketSession>> userIterator() {
        Set<Map.Entry<String, WebSocketSession>> entrys = users.entrySet();
        return entrys.iterator();
    }
}
