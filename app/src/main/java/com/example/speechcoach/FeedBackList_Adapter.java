package com.example.speechcoach;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class FeedBackList_Adapter extends ArrayAdapter {

    private Context context;
    private List<FeedBackList_Class> getAllFeedBacks;
    private Button RemoveButton;
    String feedBackID[];
    String feedBackDate[];
    double feedBackScore[];

    public FeedBackList_Adapter(Context c,String feedBackID[],String FeedBackDate[],double feedBackScore[]) {
        super(c,R.layout.feedback_list_item_activity ,R.id.txt_FeedBack_Date,FeedBackDate);
        this.context=c;
        this.feedBackID=feedBackID;
        this.feedBackDate=FeedBackDate;
        this.feedBackScore=feedBackScore;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater) context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.feedback_list_item_activity,parent,false);

        TextView date = view.findViewById(R.id.txt_FeedBack_Date);
        TextView score = view.findViewById(R.id.txt_feedBack_score);
        ProgressBar scoreBar = view.findViewById(R.id.feedback_listItem_progress);

        ImageButton viewFeedBack= view.findViewById(R.id.btn_viewFeedBack);
        viewFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view_feedBackIntent =new Intent(getContext(), FeedBack.class);
                view_feedBackIntent.putExtra("ViewFeedBack","sessionID");
                view_feedBackIntent.putExtra("FeedBackID","1");//pass sessionID
                context.startActivity(view_feedBackIntent);
            }
        });

        date.setText(feedBackDate[position]);
        score.setText(Double.toString(feedBackScore[position]));
        scoreBar.setProgress(((int) feedBackScore[position])*10);

        return view;
    }
}
