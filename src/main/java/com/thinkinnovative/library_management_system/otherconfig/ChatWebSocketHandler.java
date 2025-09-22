//package com.thinkinnovative.library_management_system.otherconfig;
//
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class ChatWebSocketHandler extends TextWebSocketHandler {
//
//    private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessions.add(session);  // Add session to list
//        System.out.println("New WebSocket connection established: " + session.getId());
//    }
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        System.out.println("Received message: " + message.getPayload());
//
//        for (WebSocketSession s : sessions) {
//            if (s.isOpen()) {
//                s.sendMessage(new TextMessage("Received: " + message.getPayload()));
//            }
//        }
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
//        sessions.remove(session);
//        System.out.println("WebSocket connection closed: " + session.getId());
//    }
//}
