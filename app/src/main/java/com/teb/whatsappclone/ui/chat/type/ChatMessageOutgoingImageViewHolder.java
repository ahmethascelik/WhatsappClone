package com.teb.whatsappclone.ui.chat.type;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.ui.chat.ChatMessageViewHolder;

import java.io.File;

public class ChatMessageOutgoingImageViewHolder extends ChatMessageViewHolder {
    public ImageView imageView;

    public ChatMessageOutgoingImageViewHolder(View v) {
        super(v);
        imageView = v.findViewById(R.id.imageView);

    }

    @Override
    public void bindMessageToView(ChatMessage chatMessage) {

        Picasso.get()
                .load(chatMessage.imageUrl)
                .into(imageView);

    }

    public static ChatMessageOutgoingImageViewHolder newInstace(@NonNull ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_message_outgoing_image, parent, false);

        return new ChatMessageOutgoingImageViewHolder(view);
    }
}