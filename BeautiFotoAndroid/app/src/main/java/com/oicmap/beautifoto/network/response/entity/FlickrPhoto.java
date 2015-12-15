package com.oicmap.beautifoto.network.response.entity;

import com.google.gson.annotations.SerializedName;

public class FlickrPhoto {
    @SerializedName("id")
    public String serverId;

    @SerializedName("owner")
    public String owner;

    @SerializedName("secret")
    public String secret;

    @SerializedName("server")
    public String server;

    @SerializedName("farm")
    public int farm;

    @SerializedName("title")
    public String title;

    @SerializedName("ispublic")
    public int isPublic;

    @SerializedName("isfriend")
    public int isFriend;

    @SerializedName("isfamily")
    public int isFamily;
}