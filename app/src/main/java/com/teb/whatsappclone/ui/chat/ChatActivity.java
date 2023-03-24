package com.teb.whatsappclone.ui.chat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.data.service.ChatService;
import com.teb.whatsappclone.data.service.MessageListener;
import com.teb.whatsappclone.data.service.ServiceLocator;

import java.util.List;

public class ChatActivity extends Activity {

    ChatService chatService = ServiceLocator.provideChatService();

    ImageButton backButton;
    TextView txtUsername;
    EditText editText;

    ImageButton micButton;
    ImageButton camButton;
    ImageButton sendButton;

    RecyclerView recyclerView;
    private final ChatAdapter adapter = new ChatAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        initViews();

    }

    private void initViews() {
        backButton = findViewById(R.id.backbutton);
        txtUsername = findViewById(R.id.txtUsername);

        camButton = findViewById(R.id.camButton);
        sendButton = findViewById(R.id.sendButton);
        micButton = findViewById(R.id.micButton);

        backButton.setOnClickListener(view -> {
            finish();
        });

        sendButton.setOnClickListener(view -> {
            String message = editText.getText().toString();
            editText.setText("");

            toggleButtonsVisibility(true);

            chatService.sendMessage("ahmet", message);

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        txtUsername.setText("Ahmet");
        editText = findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                toggleButtonsVisibility(charSequence.length() == 0);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //liste
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        chatService.setMessageListener(messageList -> {
            adapter.setDataList(messageList);
        });

    }

    public void toggleButtonsVisibility(boolean sendButtonGone){

        if(sendButtonGone){
            //camera + mic visible | sendbutton gone
            camButton.setVisibility(View.VISIBLE);
            micButton.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.GONE);
        }else{
            //camera + mic gone | sendbutton visible

            camButton.setVisibility(View.GONE);
            micButton.setVisibility(View.GONE);
            sendButton.setVisibility(View.VISIBLE);
        }
    }

}
