package com.teb.whatsappclone.data.service.impl.fake;

import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.data.service.ChatService;
import com.teb.whatsappclone.data.service.MessageListener;

import java.util.ArrayList;
import java.util.List;

public class FakeChatService implements ChatService {

    List<ChatMessage> fakeMessageList = new ArrayList<>();
    private MessageListener messageListener;


    @Override
    public void sendMessage(String sender, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.message = message;
        chatMessage.sender = sender;
//        chatMessage.dateTime = new D

        fakeMessageList.add(chatMessage);

        if(messageListener != null){
            messageListener.onMessageListChanged(fakeMessageList);
        }
    }

    @Override
    public void setMessageListener(MessageListener listener) {
        this.messageListener = listener;
    }
}
