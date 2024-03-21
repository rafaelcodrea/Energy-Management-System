package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ro.tuc.ds2020.entities.ChatMessage;
import ro.tuc.ds2020.services.MessageService;

@Controller
public class WebSocketController {


    private final MessageService messageService;

    @Autowired
    public WebSocketController(MessageService messageService){
        this.messageService = messageService;
    }


    @MessageMapping("/chat.admin")
    public ChatMessage register(@Payload ChatMessage chatMessage) {
        System.out.println(chatMessage);
        messageService.sendMessageToUser(chatMessage, chatMessage.getSenderId());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        messageService.sendMessageToAdmin(chatMessage, 1L);

        System.out.println(chatMessage);

        return chatMessage;
    }
}
