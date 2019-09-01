package com.example.speechcoach;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.example.speechcoach.R.id.txt_pose_view;

public class FeedBack_PoseList_Adapter extends ArrayAdapter {

    private Context context;
    private float BadPosePercentage;
    private float GoodPosePercentage;
    private String BadPoseList[];
    private String GoodPoseList[];

    public FeedBack_PoseList_Adapter(Context c,float BadPosePercentage,float GoodPosePercentage, String BadPoseList[], String GoodPoseList[]) {
        super(c,R.layout.feedback_pose_list_activity,R.id.txt_pose_view,BadPoseList);
        this.context=c;
        this.BadPosePercentage=BadPosePercentage;
        this.GoodPosePercentage=GoodPosePercentage;
        this.BadPoseList=BadPoseList;
        this.GoodPoseList=GoodPoseList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater=(LayoutInflater) context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.feedback_pose_list_activity,parent,false);

        Button bad_PosePercentage= view.findViewById(R.id.PercentageButton_BadPose);
        Button good_PosePercentage=view.findViewById(R.id.PercentageButton_GoodPose);

        ListView BadList= view.findViewById(R.id.List_BadPose);
        ListView GoodList= view.findViewById(R.id.List_GoodPose);

        bad_PosePercentage.setText(BadPosePercentage+"%");
        good_PosePercentage.setText(GoodPosePercentage+"%");

        /*ArrayAdapter<String> badListAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,BadPoseList);
        BadList.setAdapter(badListAdapter);

        ArrayAdapter<String> goodListAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,GoodPoseList);
        GoodList.setAdapter(goodListAdapter);*/

        return view;
    }
}
