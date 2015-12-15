package com.oicmap.beautifoto.network.response.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrPhotos {
    @SerializedName("page")
    public int page;

    @SerializedName("pages")
    public int pages;

    @SerializedName("perpage")
    public int perpage;

    @SerializedName("total")
    public int total;

    @SerializedName("photo")
    public List<FlickrPhoto> photo;
}