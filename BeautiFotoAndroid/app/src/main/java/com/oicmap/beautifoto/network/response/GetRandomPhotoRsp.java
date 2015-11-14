package com.oicmap.beautifoto.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by khacpham on 11/14/15.
 */
public class GetRandomPhotoRsp extends BaseRsp {

    @SerializedName("photos")
    public FlickrPhotos photos;

    public static class FlickrPhotos{
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

    public static class FlickrPhoto {
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
}
