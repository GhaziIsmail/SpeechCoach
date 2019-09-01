package com.example.speechcoach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;


public class FeedBackList extends AppCompatActivity {

    String feedBackID[] = {"f1","f2","f3"};
    String feedBackDate[] = {"2019-01-01 11:11","2019-02-02 22:22","2019-03-03 04:44"};
    double feedBackScore[]= {6,10,2};
    private ListView feedBack_Item_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_list_activity);


        feedBack_Item_List=findViewById(R.id.list_Feedbacks);
        FeedBackList_Adapter adapter = new FeedBackList_Adapter(this,feedBackID,feedBackDate,feedBackScore);
        feedBack_Item_List.setAdapter(adapter);

    }



}
