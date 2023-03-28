package com.teb.whatsappclone.ui.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.teb.whatsappclone.R;
import com.teb.whatsappclone.ui.chat.ChatActivity;

public class WelcomeActivity extends Activity {

    Button buttonGo;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        buttonGo = findViewById(R.id.buttonGo);
        editText = findViewById(R.id.edtNickname);

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = editText.getText().toString();

                if(!nickname.isEmpty()){
                    Intent intent = new Intent(WelcomeActivity.this, ChatActivity.class);
                    intent.putExtra(ChatActivity.EXTRA_NICKNAME, nickname);
                    startActivity(intent);

                    finish();
                }

            }
        });

    }
}
