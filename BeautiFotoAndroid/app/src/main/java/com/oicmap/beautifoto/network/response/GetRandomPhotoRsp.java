package com.oicmap.beautifoto.network.response;

import com.google.gson.annotations.SerializedName;
import com.oicmap.beautifoto.network.response.entity.FlickrPhotos;

/**
 * Created by khacpham on 11/14/15.
 */
public class GetRandomPhotoRsp extends BaseRsp {

    @SerializedName("photos")
    public FlickrPhotos photos;
}
