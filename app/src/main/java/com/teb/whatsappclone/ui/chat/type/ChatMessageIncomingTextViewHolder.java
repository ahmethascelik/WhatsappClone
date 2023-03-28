package com.teb.whatsappclone.ui.chat.type;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.ui.chat.ChatMessageViewHolder;

public class ChatMessageIncomingTextViewHolder extends ChatMessageViewHolder {

    public TextView txtMessageIncoming;
    public TextView txtMessageIncomingSender;


    public ChatMessageIncomingTextViewHolder(View v) {
        super(v);
        txtMessageIncoming = v.findViewById(R.id.txtMessageIncoming);
        txtMessageIncomingSender = v.findViewById(R.id.txtMessageIncomingSender);
    }

    @Override
    public void bindMessageToView(ChatMessage chatMessage) {

        txtMessageIncomingSender.setText(chatMessage.sender);
        txtMessageIncoming.setText(chatMessage.message);
    }
}
