package com.example.quickreddit.Comments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckComment {

    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public CheckComment(String success) {
        this.success = success;
    }
}
