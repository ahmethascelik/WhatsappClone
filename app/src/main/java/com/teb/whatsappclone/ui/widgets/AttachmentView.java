package com.teb.whatsappclone.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;

import java.util.List;

public class AttachmentView extends RelativeLayout {

    RecyclerView recyclerView;
    Button btnCancel;

    private final AttachmentItemsAdapter adapter = new AttachmentItemsAdapter();

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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_attachment, this, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);

        btnCancel = view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setVisibility(GONE);
            }
        });

        addView(view);

        setVisibility(GONE);
    }


    public void show(){
        setVisibility(VISIBLE);
    }

    public void hide(){
        setVisibility(GONE);

    }

    public void setItems(List<AttachmentItem> attachmentItemList){
        adapter.setAttachmentItemList(attachmentItemList);
    }

}
