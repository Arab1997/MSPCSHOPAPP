package com.ukuya.mspc.api.model;

import com.google.gson.annotations.SerializedName;

public class Data<T> {
    @SerializedName("data")
    private T data;
    @SerializedName("errors")
    private Error error;
    @SerializedName("isSuccess")
    private Boolean isSuccess;
    @SerializedName("status")
    private String status;
    @SerializedName("id")
    private String id;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
