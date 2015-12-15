package com.oicmap.beautifoto.network.response.entity;

import com.google.gson.annotations.SerializedName;

public class Size {
    @SerializedName("label")
    public String label;

    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    @SerializedName("source")
    public String source;

    @SerializedName("url")
    public String url;

    @SerializedName("media")
    public String media;
}