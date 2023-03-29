package com.teb.whatsappclone.data.service.impl.firebase;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.data.model.ChatMessageType;
import com.teb.whatsappclone.data.service.ChatService;
import com.teb.whatsappclone.data.service.MessageListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FirebaseChatService implements ChatService {

    List<ChatMessage> chatMessageList = new ArrayList<>();
    private MessageListener messageListener;


    @Override
    public void sendTextMessage(String sender, String message) {

        writeNewTextMessage(sender, message);
    }

    @Override
    public void sendImageMessage(String sender, String filePath) {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();


        Uri file = Uri.fromFile(new File(filePath));
        StorageReference remoteFileRef = storageRef.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = remoteFileRef.putFile(file);

//// Register observers to listen for when the download is done or if it fails
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                // ...
//            }
//        });


        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return remoteFileRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Log.d("ahmet","" + downloadUri);

                    writeNewImageMessage(sender, downloadUri.toString());



                } else {
                    // Handle failures
                    // ...
                }
            }
        });


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

    public void writeNewTextMessage(String sender, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.message = message;
        chatMessage.sender = sender;
        chatMessage.messageType = ChatMessageType.TYPE_TEXT;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refMessages = database.getReference("messages");

        DatabaseReference refNewItem = refMessages.push();

        refNewItem.setValue(chatMessage);

    }


    public void writeNewImageMessage(String sender, String imageURL) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.imageUrl = imageURL;
        chatMessage.sender = sender;
        chatMessage.messageType = ChatMessageType.TYPE_IMAGE;

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
