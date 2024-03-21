package ro.tuc.ds2020.entities;

import org.apache.tomcat.util.json.JSONParser;

public class ChatMessage {

    Long senderId;
    String action;
    String message;

    public ChatMessage(Long senderId, String action, String message) {
        this.senderId = senderId;
        this.action = action;
        this.message = message;
    }

    public ChatMessage() {
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        return "{" +
                "senderId: \"" + senderId + "\", " +
                "action: \""+  action + "\", " +
                "message: \"" + message+ "\"" +
                "}";
    }
}
