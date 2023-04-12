package com.teb.whatsappclone.ui.util;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class DocumentUtil {
    private static int RESULT_LOAD_DOCUMENT = 1;
    String currentDocumentPath;

    public void documentChooser(Activity context) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("application/pdf");
        i.addCategory(Intent.CATEGORY_OPENABLE);
        context.startActivityForResult(Intent.createChooser(i, "Select Document"), RESULT_LOAD_DOCUMENT);
    }

    public void onActivityResult(Activity context, int requestCode, int resultCode, Intent data, OnDocumentGalleryListener listener) {
        if (requestCode == RESULT_LOAD_DOCUMENT && resultCode == RESULT_OK && null != data) {
            Uri selectedDocument = data.getData();
            String[] filePathColumn = { MediaStore.Files.FileColumns.DATA };
            Cursor cursor = context.getContentResolver().query(selectedDocument, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            currentDocumentPath = cursor.getString(columnIndex);
            cursor.close();
            listener.onDocumentGallerySuccess(currentDocumentPath);
        }
    }

    public interface OnDocumentGalleryListener {
        void onDocumentGallerySuccess(String filePath);
    }
}

