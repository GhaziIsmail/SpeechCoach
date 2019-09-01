package com.example.speechcoach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

public class FeedBack extends AppCompatActivity {

    private float goodPosePercentage;
    private float badPosePercentage;
    private String GoodPoseList[]={"Standing hand hips","Standing normal"};
    private String BadPoseList[] = {"Hands folded","Hands behind body","controting"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity);

        if(this.getIntent().getExtras() != null)
        {
            Intent intent=this.getIntent();
            String viewIntent=intent.getStringExtra("ViewFeedBack");

            if(viewIntent.equals("sessionID")){
                String sessionID=intent.getStringExtra("FeedBackID");
                Toast.makeText(this,"in fragment 2 ",Toast.LENGTH_LONG).show();
               /* ListView poseAnalysis_List=findViewById(R.id.feedback_pose);
                FeedBack_PoseList_Adapter poseAdapter=new FeedBack_PoseList_Adapter(this,badPosePercentage,goodPosePercentage,BadPoseList,GoodPoseList);
                poseAnalysis_List.setAdapter(poseAdapter);*/
            }

            //Toast.makeText(this,"in fragment 2 "+id,Toast.LENGTH_LONG).show();

        }

    }
}
