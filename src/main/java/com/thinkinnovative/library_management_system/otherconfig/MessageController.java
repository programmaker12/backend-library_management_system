package com.thinkinnovative.library_management_system.otherconfig;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public class MessageController {


    @MessageMapping("/chat")
    @SendTo("/topic/public")
    public MessageEntity sendMessage(@Payload MessageEntity message )
    {
        return message;
    }


    public MessageEntity addUser(@Payload MessageEntity message, SimpMessageHeaderAccessor headerAccessor){

        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }
}