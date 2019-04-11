package com.example.freshup.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleProductCategoryModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
