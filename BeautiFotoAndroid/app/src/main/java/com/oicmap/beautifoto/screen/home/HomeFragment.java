package com.oicmap.beautifoto.screen.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.oicmap.beautifoto.R;
import com.oicmap.beautifoto.common.rx.subcriber.SubscriberSimple;
import com.oicmap.beautifoto.manager.PhotoMng;
import com.oicmap.beautifoto.model.Photo;
import com.oicmap.beautifoto.screen.BaseFragment;
import com.oicmap.beautifoto.screen.home.adapter.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class HomeFragment extends BaseFragment {

    //============= CONSTANTS ==================================

    //============= VARIABLES ==================================

    PhotoAdapter adapter;

    List<Photo> photos;

    int photoNumber;

    //============= VIEWS ======================================

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.loading)
    ProgressBar loading;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initAfterViewCreated() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false));

        photos = new ArrayList<>();
        adapter = new PhotoAdapter(mainActivity, photos);
        recyclerView.setAdapter(adapter);


        PhotoMng.getInstance().getRecentPhoto()
                .subscribe(new SubscriberSimple<List<String>>() {
                    @Override
                    public void onNext(List<String> photoList) {
                        super.onNext(photoList);
                        loading.setVisibility(View.VISIBLE);
                        for(String id: photoList){
                            PhotoMng.getInstance().getPhotoById(id)
                                .subscribe(new SubscriberSimple<List<Photo>>() {
                                    @Override
                                    public void onNext(List<Photo> photos) {
                                        super.onNext(photos);
                                        addPhoto(photos.get(photos.size()-1));
                                    }

                                    @Override
                                    public void onCompleted(boolean success, List<Photo> photos, Throwable e) {
                                        super.onCompleted(success, photos, e);
                                        if(HomeFragment.this.photos.size() == photoNumber){
                                            loading.setVisibility(View.GONE);
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        super.onError(e);
                                        photoNumber--;
                                    }
                                });
                        }

                        photoNumber = photoList.size();
                    }
                });
    }

    public synchronized void addPhoto(Photo photo){
        HomeFragment.this.photos.add(adapter.getItemCount(),photo);
        adapter.notifyDataSetChanged();
    }

    //============= INNER CLASS ==================================
}