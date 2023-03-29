package com.teb.whatsappclone.ui.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teb.whatsappclone.R;

import java.util.ArrayList;
import java.util.List;

public class AttachmentItemsAdapter extends RecyclerView.Adapter<AttachmentItemsAdapter.AttachmentViewHolder> {

    List<AttachmentItem> attachmentItemList = new ArrayList<>();

    public void setAttachmentItemList(List<AttachmentItem> attachmentItemList) {
        this.attachmentItemList.clear();
        this.attachmentItemList.addAll(attachmentItemList);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AttachmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_attachment_view, parent, false);

        return new AttachmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttachmentViewHolder holder, int position) {
        AttachmentItem item = attachmentItemList.get(position);

        holder.textView.setText(item.text);
        holder.iconView.setImageResource(item.iconRes);

    }

    @Override
    public int getItemCount() {
        return attachmentItemList.size();
    }

    public static class  AttachmentViewHolder extends RecyclerView.ViewHolder{

        ImageView iconView;
        TextView textView;

        public AttachmentViewHolder(@NonNull View itemView) {
            super(itemView);
            iconView = itemView.findViewById(R.id.imgIcon);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
