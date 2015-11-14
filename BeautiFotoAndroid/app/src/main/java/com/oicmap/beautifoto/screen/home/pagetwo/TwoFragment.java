package com.oicmap.beautifoto.screen.home.pagetwo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oicmap.beautifoto.R;
import com.oicmap.beautifoto.common.rx.subcriber.SubscriberSimple;
import com.oicmap.beautifoto.manager.PhotoMng;

import java.util.List;

public class TwoFragment extends Fragment {

    public TwoFragment() {
        // Required empty public constructor
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PhotoMng.getInstance().getRandomPhoto()
        .subscribe(new SubscriberSimple<List<String>>(){
            @Override
            public void onNext(List<String> strings) {
                super.onNext(strings);
            }
        });
    }
}