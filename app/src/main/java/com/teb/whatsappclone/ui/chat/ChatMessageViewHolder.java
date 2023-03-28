package com.teb.whatsappclone.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;

public abstract class ChatMessageViewHolder extends RecyclerView.ViewHolder {

    public ChatMessageViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindMessageToView(ChatMessage chatMessage) ;
}
