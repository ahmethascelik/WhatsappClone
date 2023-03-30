package com.teb.whatsappclone.ui.util;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.teb.whatsappclone.R;

public class PollUtil {

    public interface PopupListener {
        void onDone(ArrayList<String> items);
    }

    public static void showDialog(Context context, PopupListener popupListener) {
        // AlertDialog oluşturma
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View popupView = LayoutInflater.from(context).inflate(R.layout.poll_popup_view, null);
        builder.setView(popupView);

        // EditText ve butonları bulma
        final EditText editText = popupView.findViewById(R.id.editText);
        Button addButton = popupView.findViewById(R.id.addButton);
        Button doneButton = popupView.findViewById(R.id.doneButton);

        // Satırların listesini oluşturmak için ArrayList tanımlama
        final ArrayList<String> list = new ArrayList<>();

        final ListView listView = popupView.findViewById(R.id.listView);
        final ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        // AlertDialog'u olustur
        AlertDialog dialog = builder.create();

        // Ekle butonuna tıklama işlemi
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editText.getText().toString();
                list.add(item);
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        // Tamam butonuna tıklama işlemi
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Satırların listesini döndürme
                popupListener.onDone(list);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}

