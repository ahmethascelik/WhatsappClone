package com.teb.whatsappclone.ui.util;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class GalleryUtil {
    private static int RESULT_LOAD_IMAGE = 1;
    String currentPhotoPath;

    public void imageChooser(Activity context) {

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        context.startActivityForResult(i, RESULT_LOAD_IMAGE);

    }

    public void onActivityResult(Activity context, int requestCode, int resultCode, Intent data,  OnPhotoGalleryListener listener) {

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = context.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            currentPhotoPath = cursor.getString(columnIndex);
            cursor.close();
            listener.onPhotoGallerySuccess(currentPhotoPath);

        }
    }

    public interface OnPhotoGalleryListener {
        public void onPhotoGallerySuccess(String filePath);
    }
}
