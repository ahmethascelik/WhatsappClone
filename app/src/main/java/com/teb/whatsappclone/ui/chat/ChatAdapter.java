package com.teb.whatsappclone.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatMessageViewHolder> {

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

    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_message, parent, false);

        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int position) {

        ChatMessage chatMessage = dataList.get(position);

        boolean isOutgoing = chatMessage.sender.equals(nickname);

        if(isOutgoing){
            holder.layoutIncoming.setVisibility(View.GONE);
            holder.layoutOutgoing.setVisibility(View.VISIBLE);

            holder.txtMessage.setText(chatMessage.message);

        }else{
            //incoming

            holder.layoutIncoming.setVisibility(View.VISIBLE);
            holder.layoutOutgoing.setVisibility(View.GONE);

            holder.txtMessageIncoming.setText(chatMessage.message);

        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    static class ChatMessageViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage;
        TextView txtMessageIncoming;

        View layoutIncoming;
        View layoutOutgoing;


        public ChatMessageViewHolder(View v) {
            super(v);
            txtMessage = v.findViewById(R.id.txtMessage);
            layoutOutgoing = v.findViewById(R.id.layoutOutgoing);
            layoutIncoming = v.findViewById(R.id.layoutIncoming);
            txtMessageIncoming = v.findViewById(R.id.txtMessageIncoming);
        }
    }
}
