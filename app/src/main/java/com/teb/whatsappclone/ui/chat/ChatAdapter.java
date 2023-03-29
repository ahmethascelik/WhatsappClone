package com.teb.whatsappclone.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.data.model.ChatMessageType;
import com.teb.whatsappclone.ui.chat.type.ChatMessageIncomingImageViewHolder;
import com.teb.whatsappclone.ui.chat.type.ChatMessageIncomingTextViewHolder;
import com.teb.whatsappclone.ui.chat.type.ChatMessageOutgoingImageViewHolder;
import com.teb.whatsappclone.ui.chat.type.ChatMessageOutgoingTextViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatMessageViewHolder> {

    public static final int VIEW_TYPE_INCOMING_TEXT = 1;
    public static final int VIEW_TYPE_OUTGOING_TEXT = 2;

    public static final int VIEW_TYPE_INCOMING_IMAGE = 3;
    public static final int VIEW_TYPE_OUTGOING_IMAGE = 4;

    List<ChatMessage> dataList = new ArrayList<>();
    private String nickname;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDataList(List<ChatMessage> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = dataList.get(position);

        boolean isOutgoing = chatMessage.sender.equals(nickname);

        if(isOutgoing){
            return chatMessage.messageType == ChatMessageType.TYPE_IMAGE ?  VIEW_TYPE_OUTGOING_IMAGE  : VIEW_TYPE_OUTGOING_TEXT;
        }else{
            return chatMessage.messageType == ChatMessageType.TYPE_IMAGE ?  VIEW_TYPE_INCOMING_IMAGE  : VIEW_TYPE_INCOMING_TEXT;
        }


    }

    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_OUTGOING_TEXT) {
           return ChatMessageOutgoingTextViewHolder.newInstace(parent);
        }else if (viewType == VIEW_TYPE_INCOMING_TEXT) {
            return ChatMessageIncomingTextViewHolder.newInstace(parent);
        } else if (viewType == VIEW_TYPE_OUTGOING_IMAGE) {
           return ChatMessageOutgoingImageViewHolder.newInstace(parent);
        }else if (viewType == VIEW_TYPE_INCOMING_IMAGE) {
            return ChatMessageIncomingImageViewHolder.newInstace(parent);
        }

        throw new RuntimeException("development error");
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int position) {

        ChatMessage chatMessage = dataList.get(position);
        holder.bindMessageToView(chatMessage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }





}
