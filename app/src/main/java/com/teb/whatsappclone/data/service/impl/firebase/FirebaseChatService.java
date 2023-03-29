package com.teb.whatsappclone.data.service.impl.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.data.service.ChatService;
import com.teb.whatsappclone.data.service.MessageListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseChatService implements ChatService {

    List<ChatMessage> chatMessageList = new ArrayList<>();
    private MessageListener messageListener;


    @Override
    public void sendTextMessage(String sender, String message) {

        writeNewMessage(sender, message);
    }

    @Override
    public void sendImageMessage(String sender, String filePath) {

    }

    public FirebaseChatService() {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refMessages = database.getReference("messages");


        refMessages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatMessageList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ChatMessage chatMessage = postSnapshot.getValue(ChatMessage.class);
                    chatMessageList.add(chatMessage);
                }

                Log.d("ahmet", "done!");
                if(messageListener != null){
                    messageListener.onMessageListChanged(chatMessageList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //todo
                Log.e("ahmet", "todo read error");
            }
        });

    }

    public void writeNewMessage(String sender, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.message = message;
        chatMessage.sender = sender;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refMessages = database.getReference("messages");

        DatabaseReference refNewItem = refMessages.push();

        refNewItem.setValue(chatMessage);

    }




    @Override
    public void setMessageListener(MessageListener listener) {
        messageListener = listener;
    }
}
