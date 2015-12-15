package com.oicmap.beautifoto.network.response;

import com.google.gson.annotations.SerializedName;
import com.oicmap.beautifoto.network.response.entity.Sizes;

/**
 * Created by khacpham on 11/14/15.
 */
public class GetPhotoSizeByIdRsp extends BaseRsp {

    @SerializedName("sizes")
    public Sizes sizes;
}