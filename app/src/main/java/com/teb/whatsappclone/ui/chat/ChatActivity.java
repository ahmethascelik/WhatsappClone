package com.teb.whatsappclone.ui.chat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;
import com.teb.whatsappclone.data.model.ChatMessage;
import com.teb.whatsappclone.data.service.ChatService;
import com.teb.whatsappclone.data.service.MessageListener;
import com.teb.whatsappclone.data.service.ServiceLocator;
import com.teb.whatsappclone.ui.util.CameraUtil;
import com.teb.whatsappclone.ui.welcome.WelcomeActivity;
import com.teb.whatsappclone.ui.widgets.AttachmentItem;
import com.teb.whatsappclone.ui.widgets.AttachmentItemsAdapter;
import com.teb.whatsappclone.ui.widgets.AttachmentView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

            chatService.sendMessage(nickname, message);

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
        attachmentView.setItems(list);

        attachmentView.setItemClickListener(position -> {
            //0 ise camera
            if(position == 0){

                cameraUtil.takePhoto(ChatActivity.this);

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
                setPic(filePath);
            }
        });


    }



    private void setPic(String currentPhotoPath) {
        // Get the dimensions of the View
        int targetW = 200;
        int targetH = 200;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW/targetW, photoH/targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        RelativeLayout layoutBody = findViewById(R.id.layoutBody);

        layoutBody.setBackground(new BitmapDrawable(bitmap));
    }






}
