package com.example.speechcoach;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static int VIDEO_REQUEST=101;
    private Uri videoUri = null;

    private Button RecordVideo;
    private Button FeedBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecordVideo=findViewById(R.id.btn_record);
        FeedBackBtn=findViewById(R.id.btn_previousFeedback);

        RecordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureVideo(v);
            }
        });

        FeedBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedBackIntent =new Intent(getApplicationContext(), FeedBackList.class);
                startActivity(feedBackIntent);
            }
        });

    }

    public void captureVideo(View view){
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        File videoFile=getFilePath();

        //String authorities = getApplicationContext().getPackageName() + ".fileprovider";
        //Uri imageUri = FileProvider.getUriForFile(this, authorities, photoFile);
        //callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        //Uri videoUri=Uri.fromFile(videoFile);
        Uri videoUri = FileProvider.getUriForFile(getApplicationContext(), "com.example.speechcoach.fileprovider", videoFile);
        videoIntent.putExtra(MediaStore.EXTRA_OUTPUT,videoUri);
        //videoIntent.putExtra();
        //startActivityForResult(videoIntent,VIDEO_REQUEST);

       if(videoIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(videoIntent,VIDEO_REQUEST);
        }
    }

    public File getFilePath(){

        /*File folder=new File("storage/videoApp");

        if(!folder.exists()){
            folder.mkdir();
        }

        File newVideoFile = new File(folder,"app_video.mp4");*/
        File newVideoFile=new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES),"app_video.mp4");

        if (!newVideoFile.mkdirs()) {
            //Log.e(LOG_TAG, "Directory not created");
            //
            newVideoFile.mkdir();
        }
        return newVideoFile;

    }



    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==VIDEO_REQUEST && resultCode==RESULT_OK){
            videoUri = data.getData();
            //Toast.makeText(this,"this works : "+videoUri, Toast.LENGTH_LONG).show();
            String selectedVideoPath=getPath(videoUri);
            //Toast.makeText(this,selectedVideoPath, Toast.LENGTH_LONG).show();
            uploadToServer(selectedVideoPath);

        }
    }

    private void uploadToServer(String filePath) {
        Retrofit retrofit = AppConfig.getRetrofitClient(this);
        FileUploadService uploadAPIs = retrofit.create(FileUploadService.class);
        //Create a file object using file path
        File file = new File(filePath);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("video/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "video-type");
        //
        Call call = uploadAPIs.upload( description,part);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.v("Upload", "success");
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
    }
