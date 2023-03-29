package com.teb.whatsappclone.data.service;

import com.teb.whatsappclone.data.service.impl.fake.FakeChatService;
import com.teb.whatsappclone.data.service.impl.firebase.FirebaseChatService;

public class ServiceLocator {

    public static ChatService provideChatService(){
        return new FirebaseChatService();
    }
}
