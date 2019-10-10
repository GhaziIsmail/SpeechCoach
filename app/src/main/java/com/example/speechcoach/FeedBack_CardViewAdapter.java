package com.example.speechcoach;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedBack_CardViewAdapter extends RecyclerView.Adapter <FeedBack_CardViewAdapter.MyViewHolder>{

    ArrayList feedbackDate;
    ArrayList feedbackScore;
    Context context;
    private MyViewHolder holder;


    public FeedBack_CardViewAdapter(Context context, ArrayList feedbackDate, ArrayList feedbackScore) {
        this.context = context;
        this.feedbackDate = feedbackDate;
        this.feedbackScore = feedbackScore;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }





    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.Feedback_videoDate.setText((feedbackDate.get(position)).toString());
        holder.Feedback_score.setText((feedbackScore.get(position)).toString());
        holder.scoreBar.setProgress((int)feedbackScore.get(position));
    }



    @Override
    public int getItemCount() {
        return feedbackDate.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView Feedback_videoDate;
        TextView Feedback_score;
        ProgressBar scoreBar;

        public MyViewHolder(View itemView) {
            super(itemView);

        // get the reference of item view's
            Feedback_videoDate = (TextView) itemView.findViewById(R.id.txt_FeedBack_Date);
            Feedback_score = (TextView) itemView.findViewById(R.id.txt_feedBack_score);
            scoreBar=(ProgressBar) itemView.findViewById(R.id.feedback_listItem_progress);
    }
    }
}
