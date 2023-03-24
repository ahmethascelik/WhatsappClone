package com.teb.whatsappclone.data.service;

public interface ChatService {

    void sendMessage(String sender, String message );

    void setMessageListener(MessageListener listener);
}
