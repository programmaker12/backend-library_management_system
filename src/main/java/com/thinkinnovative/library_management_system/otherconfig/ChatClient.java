//package com.thinkinnovative.library_management_system.otherconfig;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
//import org.springframework.web.reactive.socket.WebSocketMessage;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.time.Duration;
//
//@Component
//public class ChatClient {
//
//    private final ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();
//    private final URI serverUri = URI.create("ws://localhost:8081/chat");
//
//    @PostConstruct  // Runs automatically when the application starts
//    public void startClient() {
//        sendMessage("Hello from ChatClient on port 8081!");
//    }
//
//    public void sendMessage(String message) {
//        client.execute(serverUri, session ->
//                        session.send(Mono.just(session.textMessage(message)))
//                                .thenMany(session.receive()
//                                        .map(WebSocketMessage::getPayloadAsText)
//                                        .doOnNext(response -> System.out.println("Received from server: " + response)))
//                                .then()
//                ).timeout(Duration.ofSeconds(5)) // Timeout to prevent infinite blocking
//                .doOnError(error -> System.err.println("WebSocket error: " + error.getMessage()))
//                .block(); // Blocking here ensures execution completes before closing
//    }
//}
