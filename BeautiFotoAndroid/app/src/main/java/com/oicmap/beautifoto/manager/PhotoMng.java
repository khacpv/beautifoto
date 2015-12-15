package com.oicmap.beautifoto.manager;

import com.oicmap.beautifoto.model.Photo;
import com.oicmap.beautifoto.network.NetworkMng;
import com.oicmap.beautifoto.network.response.GetPhotoSizeByIdRsp;
import com.oicmap.beautifoto.network.response.GetRandomPhotoRsp;
import com.oicmap.beautifoto.network.response.GetRecentPhotoRsp;
import com.oicmap.beautifoto.network.response.entity.FlickrPhoto;
import com.oicmap.beautifoto.network.response.entity.Size;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by khacpham on 11/14/15.
 */
public class PhotoMng extends BaseMng {

    private static PhotoMng instance;

    public static PhotoMng getInstance() {
        if (instance == null) {
            instance = new PhotoMng();
        }
        return instance;
    }

    /**
     * @return id photos
     */
    public Observable<List<String>> getRecentPhoto() {
        return NetworkMng.getInstance().getFlickrApi().getRecentPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GetRecentPhotoRsp, List<FlickrPhoto>>() {
                    @Override
                    public List<FlickrPhoto> call(GetRecentPhotoRsp getRandomPhotoRsp) {
                        return getRandomPhotoRsp.photos.photo;
                    }
                })
                .map(new Func1<List<FlickrPhoto>, List<String>>() {
                    @Override
                    public List<String> call(List<FlickrPhoto> flickrPhotos) {
                        List<String> ids = new ArrayList<>();
                        for(FlickrPhoto photo: flickrPhotos){
                            ids.add(photo.serverId);
                        }
                        return ids;
                    }
                });
    }

    /**
     * @return id photos
     */
    public Observable<List<String>> getRandomPhoto() {
        return NetworkMng.getInstance().getFlickrApi().getRandomPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GetRandomPhotoRsp, List<FlickrPhoto>>() {
                    @Override
                    public List<FlickrPhoto> call(GetRandomPhotoRsp getRandomPhotoRsp) {
                        return getRandomPhotoRsp.photos.photo;
                    }
                })
                .map(new Func1<List<FlickrPhoto>, List<String>>() {
                    @Override
                    public List<String> call(List<FlickrPhoto> flickrPhotos) {
                        List<String> ids = new ArrayList<>();
                        for (FlickrPhoto photo : flickrPhotos) {
                            ids.add(photo.serverId);
                        }
                        return ids;
                    }
                });
    }

    public Observable<List<Photo>> getPhotoById(String id) {
        return NetworkMng.getInstance().getFlickrApi().getPhotoSizeById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GetPhotoSizeByIdRsp, List<Photo>>() {
                    @Override
                    public List<Photo> call(GetPhotoSizeByIdRsp rsp) {
                        List<Photo> data = new ArrayList<>();
                        for (Size size : rsp.sizes.size) {
                            Photo photo = new Photo();
                            photo.url = size.source;
                            photo.width = size.width;
                            photo.height = size.height;
                            data.add(photo);
                        }
                        return data;
                    }
                });
    }
}
