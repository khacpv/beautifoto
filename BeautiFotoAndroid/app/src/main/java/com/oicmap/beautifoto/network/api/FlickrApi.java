package com.oicmap.beautifoto.network.api;

import com.oicmap.beautifoto.network.response.GetPhotoSizeByIdRsp;
import com.oicmap.beautifoto.network.response.GetRandomPhotoRsp;
import com.oicmap.beautifoto.network.response.GetRecentPhotoRsp;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by khacpham on 11/14/15.
 * configuration for api
 */
public interface FlickrApi {

    int PERPAGE = 20;

    String KEY_API = "4ef2fe2affcdd6e13218f5ddd0e2500d";

    String SV_PROD = "https://api.flickr.com/services";

    @GET("/rest?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    Observable<GetRecentPhotoRsp> getRecentPhotos();

    @GET("/rest?method=flickr.interestingness.getList&format=json&nojsoncallback=1")
    Observable<GetRandomPhotoRsp> getRandomPhotos();

    @GET("/rest?method=flickr.photos.getSizes&format=json&nojsoncallback=1")
    Observable<GetPhotoSizeByIdRsp> getPhotoSizeById(@Query("photo_id") String photoId);

    @GET("/rest?method=flickr.people.getPhotos&format=json&nojsoncallback=1")
    Observable<String> getPhotosByUserId(@Query("user_id") String userId);

    @GET("/rest?method=flickr.people.findByUsername&format=json&nojsoncallback=1")
    Observable<String> findUserIdByUserName(@Query("username") String username);

    @GET("/rest?method=flickr.photos.search&format=json&nojsoncallback=1")
    Observable<String> searchPhoto(@Query("text") String text);

}
