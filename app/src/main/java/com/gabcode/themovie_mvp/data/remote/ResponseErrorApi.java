package com.gabcode.themovie_mvp.data.remote;

import com.google.gson.annotations.SerializedName;

public class ResponseErrorApi {

    @SerializedName("status_message") private String message;
    @SerializedName("status_code") private Integer statusCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
