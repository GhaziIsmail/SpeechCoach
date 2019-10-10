package com.example.speechcoach;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    // variable name should be same as in the json response from php
    @SerializedName("success")
    static
    boolean success;
    @SerializedName("message")
    static
    String message;
    static String getMessage() {
        return message;
    }
    static boolean getSuccess() {
        return success;
    }
}
