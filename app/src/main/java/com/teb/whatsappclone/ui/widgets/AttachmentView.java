package com.teb.whatsappclone.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.teb.whatsappclone.R;

public class AttachmentView extends RelativeLayout {
    public AttachmentView(Context context) {
        super(context);

        init();
    }


    public AttachmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public AttachmentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public AttachmentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_chat_message_outgoing_text, this, false);

        addView(view);
    }

}
