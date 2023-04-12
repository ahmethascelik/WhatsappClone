package com.teb.whatsappclone.data.service.impl.fake;

import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.data.model.ChatMessageType;
import com.teb.whatsappclone.data.service.ChatService;
import com.teb.whatsappclone.data.service.MessageListener;

import java.util.ArrayList;
import java.util.List;

public class FakeChatService implements ChatService {

    List<ChatMessage> fakeMessageList = new ArrayList<>();
    private MessageListener messageListener;


    @Override
    public void sendTextMessage(String sender, String textMessage) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.message = textMessage;
        chatMessage.messageType = ChatMessageType.TYPE_TEXT;
        chatMessage.sender = sender;
//        chatMessage.dateTime = new D

        fakeMessageList.add(chatMessage);


        ChatMessage gelenMesaj = new ChatMessage();
        gelenMesaj.message = "Gelen mesaj ! " + textMessage;
        gelenMesaj.sender = "yigit";
//        chatMessage.dateTime = new D

        fakeMessageList.add(gelenMesaj);


        if(messageListener != null){
            messageListener.onMessageListChanged(fakeMessageList);
        }
    }

    @Override
    public void sendImageMessage(String sender, String filePath) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.imageUrl = filePath;
        chatMessage.messageType = ChatMessageType.TYPE_IMAGE;
        chatMessage.sender = sender;
//        chatMessage.dateTime = new D

        fakeMessageList.add(chatMessage);


        ChatMessage gelenMesaj = new ChatMessage();
        gelenMesaj.imageUrl = filePath;
        gelenMesaj.messageType = ChatMessageType.TYPE_IMAGE;
        gelenMesaj.sender = "yigit";
//        chatMessage.dateTime = new D

        fakeMessageList.add(gelenMesaj);

        if(messageListener != null) {
            messageListener.onMessageListChanged(fakeMessageList);
        }
    }

    @Override
    public void sendPdfMessage(String sender, String filePath) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.pdfUrl = filePath;
        chatMessage.messageType = ChatMessageType.TYPE_PDF;
        chatMessage.sender = sender;
        fakeMessageList.add(chatMessage);

        ChatMessage gelenMesaj = new ChatMessage();
        gelenMesaj.pdfUrl = filePath;
        gelenMesaj.messageType = ChatMessageType.TYPE_PDF;
        gelenMesaj.sender = "yigit";
        fakeMessageList.add(gelenMesaj);

        if (messageListener != null) {
            messageListener.onMessageListChanged(fakeMessageList);
        }
    }


    @Override
    public void setMessageListener(MessageListener listener) {
        this.messageListener = listener;
    }
}
