package com.oicmap.beautifoto.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by khacpham on 11/14/15.
 */
public class GetPhotoSizeByIdRsp extends BaseRsp {

    @SerializedName("sizes")
    public Sizes sizes;

    public static class Sizes{
        @SerializedName("canblog")
        public int canblog;

        @SerializedName("canprint")
        public int canprint;

        @SerializedName("candownload")
        public int candownload;

        @SerializedName("size")
        public List<Size> size;
    }

    public static class Size{
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
}
