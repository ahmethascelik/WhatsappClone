package com.teb.whatsappclone.ui.chat.type;

import android.view.View;
import android.widget.TextView;

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
}