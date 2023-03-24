package com.teb.whatsappclone.data.service;

import com.teb.whatsappclone.data.model.ChatMessage;

import java.util.List;

public interface MessageListener {
    void onMessageListChanged(List<ChatMessage> messageList);
}
