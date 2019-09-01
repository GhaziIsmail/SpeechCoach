package com.example.speechcoach;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        if(videoIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(videoIntent,VIDEO_REQUEST);
        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==VIDEO_REQUEST && resultCode==RESULT_OK){
            videoUri = data.getData();
        }
    }
}
