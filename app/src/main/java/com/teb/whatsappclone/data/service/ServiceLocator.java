package com.teb.whatsappclone.data.service;

import com.teb.whatsappclone.data.service.impl.fake.FakeChatService;

public class ServiceLocator {

    public static ChatService provideChatService(){
        return new FakeChatService();
    }
}
