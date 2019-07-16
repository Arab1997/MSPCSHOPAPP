package com.ukuya.mspc.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Links {
    @SerializedName("self")
    private HashMap<String, String> self;
    @SerializedName("next")
    private HashMap<String, String> next;
    @SerializedName("last")
    private HashMap<String, String> last;
}
