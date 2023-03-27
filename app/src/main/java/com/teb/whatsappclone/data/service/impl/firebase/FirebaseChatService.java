package com.teb.whatsappclone.data.service.impl.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.teb.whatsappclone.data.service.ChatService;
import com.teb.whatsappclone.data.service.MessageListener;

public class FirebaseChatService implements ChatService {
    @Override
    public void sendMessage(String sender, String message) {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    @Override
    public void setMessageListener(MessageListener listener) {

    }
}
