package com.example.speechcoach;

import java.util.Date;

public class FeedBackList_Class {

    private String FeedBackID;
    private String FeedBack_Date;
    private double Score;

    public FeedBackList_Class(String feedBackID, String feedBack_Date, double score) {
        FeedBackID = feedBackID;
        FeedBack_Date = feedBack_Date;
        Score = score;
    }

    public String getFeedBackID() {
        return FeedBackID;
    }

    public String getFeedBack_Date() {
        return FeedBack_Date;
    }

    public double getScore() {
        return Score;
    }
}
