package ro.tuc.ds2020.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.ChatMessage;

@Service
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public MessageService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    public void sendMessageToUser(ChatMessage chatMessage, Long userId) {
        try {
            messagingTemplate.convertAndSend("/topic/user."+String.valueOf(userId), objectMapper.writeValueAsString(chatMessage));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToAdmin(ChatMessage chatMessage, Long userId) {
        try {
            messagingTemplate.convertAndSend("/topic/admin", objectMapper.writeValueAsString(chatMessage));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}