package com.oicmap.beautifoto.network.response.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sizes {
    @SerializedName("canblog")
    public int canblog;

    @SerializedName("canprint")
    public int canprint;

    @SerializedName("candownload")
    public int candownload;

    @SerializedName("size")
    public List<Size> size;
}