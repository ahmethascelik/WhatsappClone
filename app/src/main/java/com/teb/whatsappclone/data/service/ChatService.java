package com.teb.whatsappclone.data.service;

public interface ChatService {

    void sendTextMessage(String sender, String message );
    void sendImageMessage(String sender, String filePath );
    void sendPdfMessage(String sender, String filePath );

    void setMessageListener(MessageListener listener);
}
