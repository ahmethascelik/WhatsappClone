package com.teb.whatsappclone.ui.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.service.ChatService;
import com.teb.whatsappclone.data.service.ServiceLocator;
import com.teb.whatsappclone.ui.util.CameraUtil;
import com.teb.whatsappclone.ui.util.PollUtil;
import com.teb.whatsappclone.ui.widgets.AttachmentItem;
import com.teb.whatsappclone.ui.widgets.AttachmentView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends Activity {

    CameraUtil cameraUtil = new CameraUtil();

    public static final String EXTRA_NICKNAME = "EXTRA_NICKNAME";

    public String nickname;

    ChatService chatService = ServiceLocator.provideChatService();

    ImageButton backButton;
    TextView txtUsername;
    EditText editText;

    ImageButton micButton;
    ImageButton camButton;
    ImageButton sendButton;

    ImageButton addButton;

    RecyclerView recyclerView;
    AttachmentView attachmentView;

    private final ChatAdapter adapter = new ChatAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String pNickname = getIntent().getStringExtra(EXTRA_NICKNAME);
        if(pNickname != null){
            this.nickname = pNickname;
        }


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

            chatService.sendTextMessage(nickname, message);

        });

        txtUsername.setText(getString(R.string.welcome_x, nickname));
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

        adapter.setNickname(nickname);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        chatService.setMessageListener(messageList -> {
            adapter.setDataList(messageList);
        });

        //attachment view

        addButton = findViewById(R.id.addButton);
        attachmentView = findViewById(R.id.attachmentPanel);

        addButton.setOnClickListener(view -> attachmentView.show());

        List<AttachmentItem> list = new ArrayList<>();
        list.add(new AttachmentItem(R.drawable.ic_camera, getString(R.string.camera_title)));
        list.add(new AttachmentItem(R.drawable.ic_record_audio, "Anket"));
        attachmentView.setItems(list);


        attachmentView.setItemClickListener(position -> {
            //0 ise camera
            if(position == 0){

                cameraUtil.takePhoto(ChatActivity.this);

            } else if (position == 1) {

                PollUtil.showDialog(ChatActivity.this, new PollUtil.PopupListener() {
                    @Override
                    public void onDone(ArrayList<String> items) {
                        // Liste işlemlerini burada yapabilirsiniz
                        Toast.makeText(ChatActivity.this, items.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        cameraUtil.onActivityResult(requestCode, resultCode, data, new CameraUtil.OnPhotoShotListener() {
            @Override
            public void onPhotoShotSuccess(String filePath) {


                chatService.sendImageMessage(nickname, filePath);

//                File file = new File(filePath);
//
//                ImageView imageView = findViewById(R.id.image);
//                Picasso.get()
//                        .load(file)
//                        .into(imageView);
            }
        });


    }







}
