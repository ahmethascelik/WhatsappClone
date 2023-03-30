package com.teb.whatsappclone.ui.util;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;


public class GalleryUtil {
    public static final int SELECT_PICTURE = 200;
    private static int RESULT_LOAD_IMAGE = 1;
    String currentPhotoPath;

    public void imageChooser(Activity context) {

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        context.startActivityForResult(i, RESULT_LOAD_IMAGE);

        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        context.startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
*/
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
            //ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


//        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
//            Uri uri = data.getData();
//            File file = new File(uri.getPath());//create path from uri
//            currentPhotoPath = file.getAbsolutePath();
//            //final String[] split = file.getPath().split(":");//split the path.
//            //currentPhotoPath = split[1];//assign it to a string(your choice).
//            listener.onPhotoGallerySuccess(currentPhotoPath);
//            Log.d("evren ", currentPhotoPath);
//
//        }
    }

    public interface OnPhotoGalleryListener {
        public void onPhotoGallerySuccess(String filePath);
    }
}
