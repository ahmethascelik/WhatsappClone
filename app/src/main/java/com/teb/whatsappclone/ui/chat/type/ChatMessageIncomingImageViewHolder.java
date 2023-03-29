package com.teb.whatsappclone.ui.chat.type;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.ui.chat.ChatMessageViewHolder;

import java.io.File;

public class ChatMessageIncomingImageViewHolder extends ChatMessageViewHolder {
    public ImageView imageView;

    public ChatMessageIncomingImageViewHolder(View v) {
        super(v);
        imageView = v.findViewById(R.id.imageViewIncoming);

    }

    @Override
    public void bindMessageToView(ChatMessage chatMessage) {

        Picasso.get()
                .load(new File(chatMessage.imageUrl))
                .into(imageView);

    }

    public static ChatMessageIncomingImageViewHolder newInstace(@NonNull ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_message_incoming_image, parent, false);

        return new ChatMessageIncomingImageViewHolder(view);
    }
}