package com.example.mobilecomputingproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class edit_gradeTestSelActivity extends AppCompatActivity {

    private String selectedPath;
    private Uri fileUri;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "ScantronSheets";

    static final int REQUEST_IMG_CAPTURE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grade_test_sel);

//        Button testB = findViewById(R.id.testButton);
        Intent oldIntent = getIntent();
        final String mode = oldIntent.getStringExtra("mode");

//        testB.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                String message = "mode = " + mode;
//                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//            }
//        });
    }

    public void captureImg(View view) {

        Intent imgIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (imgIntent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(imgIntent, REQUEST_IMG_CAPTURE);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMG_CAPTURE && resultCode == RESULT_OK) {

                  //Uri of camera image


            System.out.println("Inside3");

            System.out.println("Inside4");
            System.out.println("SELECT_VIDEO Path : " );


        }
    }


    public String getPath(Uri uri) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        }

        Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.MediaColumns.DATA}, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

//        cursor = getContentResolver().query(
//                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
//                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
//        cursor.moveToFirst();
//        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
//        cursor.close();

        return document_id;
    }

}
