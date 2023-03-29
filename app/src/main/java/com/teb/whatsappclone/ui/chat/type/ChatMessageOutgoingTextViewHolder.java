package com.teb.whatsappclone.ui.chat.type;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.ui.chat.ChatMessageViewHolder;

public class ChatMessageOutgoingTextViewHolder extends ChatMessageViewHolder {
    public TextView txtMessage;

    public ChatMessageOutgoingTextViewHolder(View v) {
        super(v);
        txtMessage = v.findViewById(R.id.txtMessage);

    }

    @Override
    public void bindMessageToView(ChatMessage chatMessage) {

        txtMessage.setText(chatMessage.message);

    }

    public static ChatMessageOutgoingTextViewHolder newInstace(@NonNull ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_message_outgoing_text, parent, false);

        return new ChatMessageOutgoingTextViewHolder(view);
    }
}