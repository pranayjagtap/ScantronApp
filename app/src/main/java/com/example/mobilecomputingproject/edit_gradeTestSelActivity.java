package com.example.mobilecomputingproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class edit_gradeTestSelActivity extends AppCompatActivity  {

    String RESULT="";
    private String selectedPath;
    private Uri fileUri;
    public static final String TAG = "MyTag";
    StringRequest stringRequest; // Assume this exists.
    RequestQueue requestQueue;  // Assume this exists.
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private ImageView imageView;
    private TextView studentId;
    private TextView answersTV;
    private String testName;
    //private EditText testName;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "ScantronSheets";

    static final int REQUEST_IMG_CAPTURE = 100;
    String currentPhotoPath;
    String filename="";
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        filename=imageFileName;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        System.out.println(currentPhotoPath);
        return image;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grade_test_sel);
        this.imageView = (ImageView) this.findViewById(R.id.testImage);
        this.answersTV=(TextView)this.findViewById(R.id.answers);
        this.studentId = (TextView) this.findViewById(R.id.studentId);
        //this.testName = (EditText) findViewById(R.id.testname);
//        Button testB = findViewById(R.id.testButton);
        Intent oldIntent = getIntent();
        final String mode = oldIntent.getStringExtra("mode");
        testName = oldIntent.getStringExtra("nameOfTest");
        System.out.println("Rubric name at edit_gradeTestSelActivity: " + testName);

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
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.mobilecomputingproject",
                        photoFile);
                System.out.println(photoURI.toString());
                fileUri=photoURI;
                imgIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(imgIntent, REQUEST_IMG_CAPTURE);

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMG_CAPTURE && resultCode == RESULT_OK) {

            imageView.setImageURI(fileUri);
            System.out.println("SELECT_VIDEO Path : "+fileUri );
            connectServer();


        }
    }
    @Override
    protected void onStop () {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
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









    /////////////////////////////////////////////////////////////////////////---------------------------------

    void connectServer(){


        String postUrl= "http://jilia.pythonanywhere.com?path=/home/jilia/mysite/images/test_02.png";

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        // Read BitMap by file path

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath,options);//"/storage/emulated/0/Android/data/com.example.mobilecomputingproject/files/Pictures/"+filename+".jpg", options);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        RequestBody postBodyImage = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "grademe.jpg", RequestBody.create(MediaType.parse("image/*jpg"), byteArray))
                .build();

        postRequest(postUrl, postBodyImage);
    }


    void postRequest(String postUrl, RequestBody postBody) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Cancel the post on failure.
                call.cancel();

                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Failed");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                // In order to access the TextView inside the UI thread, the code is executed inside runOnUiThread()
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                           //System.out.println(response.body().string());
                           RESULT=response.body().string();

                           processGrades(RESULT);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void processGrades(String answers)  {
        //Remove this mock data
        //answers="{\"filename\": \"Capture.JPG\",\"score\": [0,1,2,3,4]}";
        String result="";
        String[] temp=null;
        System.out.println("Processing this:"+answers);

        try {
            JSONObject obj = new JSONObject(answers);
            result=obj.getString("score");
            if(result.equalsIgnoreCase("null")){
                this.answersTV.setText("Either image or rubric is incorrect");
                return;
            }
            temp=result.substring(1,result.length()-1).split(",");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        char map[]={'A','B','C','D','E'};

        String stringtodisp="Answers:";
        for(int i=0;i<temp.length;i++){
            stringtodisp=stringtodisp+map[Integer.parseInt(temp[i])];
        }

        System.out.println(testName);

        this.answersTV.setText(stringtodisp);

        SharedPreferences prefsget = getSharedPreferences("datastore", MODE_PRIVATE);
        Map<String, ?> allEntries = prefsget.getAll();
//        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
//        }
        String rubrics = prefsget.getString(testName+"-answers","");//"No name defined" is the default value.
        System.out.println(rubrics);
        //Compare and thenstore

        String[] rubricsArr=rubrics.split(",");
        System.out.println(rubricsArr[0]);
        int score=0;
        String perques="";
         for(int i=0;i<rubricsArr.length&&i<temp.length;i++){
             String[] rubtemp=rubricsArr[i].split(":");
             System.out.println(map[Integer.parseInt(temp[i])]+""+"=="+rubtemp[1]);
             if((map[Integer.parseInt(temp[i])]+"").equalsIgnoreCase(rubtemp[1])){
                 System.out.println(score);
                 score++;
                 perques=perques+"1";
             }
             else{
                 perques=perques+"0";
             }
         }

         System.out.println("S"+score);
         System.out.println("T"+temp.length);
         double gradedScore=(score*1.0/rubricsArr.length)*100;
         System.out.println("Grade:"+gradedScore);
        this.answersTV.setText(stringtodisp+"  Score:"+gradedScore+"%");
        //Store this result
        SharedPreferences prefsget2 = getSharedPreferences("datastore", MODE_PRIVATE);
        String oldstats = prefsget2.getString(testName+"-"+"stats", "");//"No name defined" is the default value.
        SharedPreferences prefs = getSharedPreferences("datastore", MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("datastore", MODE_PRIVATE).edit();
        editor.putString(testName+"-"+"stats",oldstats+perques);
        editor.apply();

        String studentIdString = studentId.getText().toString();
        System.out.println(studentIdString);

        //Per student stat store -Task 24
        SharedPreferences prefsget3 = getSharedPreferences("datastore", MODE_PRIVATE);
        String stud = prefsget3.getString(studentId.getText().toString(), "");//"No name defined" is the default value.
        SharedPreferences prefs2 = getSharedPreferences("datastore", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = getSharedPreferences("datastore", MODE_PRIVATE).edit();
        editor.putString(studentId.getText().toString(),stud+","+testName+":"+gradedScore);
        editor.apply();

        //Per student stat store -Task 24
        SharedPreferences prefsget4 = getSharedPreferences("datastore", MODE_PRIVATE);
        String classstat = prefsget4.getString(testName+"-scores", "");//"No name defined" is the default value.
        SharedPreferences prefs3 = getSharedPreferences("datastore", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = getSharedPreferences("datastore", MODE_PRIVATE).edit();
        editor.putString(testName + "-scores",classstat+","+studentIdString+":"+gradedScore);
        editor.apply();


        String test = prefsget3.getString(studentId.getText().toString(), "");
        System.out.println(test);
        String test2 = prefsget4.getString(testName, "");
        System.out.println(test2);
    }


    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
            ////////////////////////////////////////////////////////////////////---------------------------------------

}
