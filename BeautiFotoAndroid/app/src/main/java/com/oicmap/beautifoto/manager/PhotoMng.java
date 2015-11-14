package com.oicmap.beautifoto.manager;

import android.util.Log;

import com.oicmap.beautifoto.common.rx.subcriber.SubscriberSimple;
import com.oicmap.beautifoto.model.Photo;
import com.oicmap.beautifoto.network.NetworkMng;
import com.oicmap.beautifoto.network.response.GetPhotoSizeByIdRsp;
import com.oicmap.beautifoto.network.response.GetRandomPhotoRsp;

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
     * @return ids photos
     * */
    public Observable<List<String>> getRandomPhoto(){
        return NetworkMng.getInstance().getFlickrApi().getRandomPhotos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GetRandomPhotoRsp, List<GetRandomPhotoRsp.FlickrPhoto>>() {
                    @Override
                    public List<GetRandomPhotoRsp.FlickrPhoto> call(GetRandomPhotoRsp getRandomPhotoRsp) {
                        return getRandomPhotoRsp.photos.photo;
                    }
                })
                .flatMap(new Func1<List<GetRandomPhotoRsp.FlickrPhoto>, Observable<List<String>>>() {
                    @Override
                    public Observable<List<String>> call(List<GetRandomPhotoRsp.FlickrPhoto> getRandomPhotoRsp) {
                        List<String> data = new ArrayList<>();
                        for (GetRandomPhotoRsp.FlickrPhoto item : getRandomPhotoRsp) {
                            data.add(item.serverId);
                            getPhotoById(item.serverId).subscribe(new SubscriberSimple<List<Photo>>(){
                                @Override
                                public void onNext(List<Photo> photos) {
                                    super.onNext(photos);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    if(e!=null){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        return Observable.just(data);
                    }
                });
    }

    public Observable<List<Photo>> getPhotoById(String id){
        return NetworkMng.getInstance().getFlickrApi().getPhotoSizeById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GetPhotoSizeByIdRsp, List<Photo>>() {
                    @Override
                    public List<Photo> call(GetPhotoSizeByIdRsp rsp) {
                        List<Photo> data = new ArrayList<>();
                        for(GetPhotoSizeByIdRsp.Size size: rsp.sizes.size){
                            Photo photo = new Photo();
                            photo.url = size.source;
                            photo.width = size.width;
                            photo.height = size.height;
                            Log.e(PhotoMng.class.getName(), String.format("load photo:%s -%s.%s", photo.url,photo.width,photo.height));
                            data.add(photo);
                        }
                        return data;
                    }
                });
    }
}
