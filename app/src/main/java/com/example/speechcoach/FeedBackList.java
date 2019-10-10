package com.example.speechcoach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class FeedBackList extends AppCompatActivity {

    ArrayList feedbackDate = new ArrayList<>(Arrays.asList("2019-01-01 11:11","2019-02-02 22:22","2019-03-03 04:44"));
    //ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.feedbackicon_3,R.drawable.feedbackicon_3,R.drawable.feedbackicon_3));
    ArrayList feedbackScore = new ArrayList<>(Arrays.asList(60,10,75));
    //String feedBackID[] = {"f1","f2","f3"};
    //String feedBackDate[] = {"2019-01-01 11:11","2019-02-02 22:22","2019-03-03 04:44"};
    //double feedBackScore[]= {6,10,2};
    private ListView feedBack_Item_List;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_list_activity);


        /*feedBack_Item_List=findViewById(R.id.list_Feedbacks);
        FeedBackList_Adapter adapter = new FeedBackList_Adapter(this,feedBackID,feedBackDate,feedBackScore);
        feedBack_Item_List.setAdapter(adapter);*/

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        FeedBack_CardViewAdapter customAdapter = new FeedBack_CardViewAdapter(FeedBackList.this, feedbackDate,feedbackScore);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }



}
