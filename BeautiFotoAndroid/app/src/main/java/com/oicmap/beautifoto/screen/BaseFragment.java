package com.oicmap.beautifoto.screen;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oicmap.beautifoto.activity.BaseActivity;
import com.oicmap.beautifoto.common.eventbus.api.OnEventBusListener;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by khacpham on 11/14/15.
 */
public abstract class BaseFragment extends Fragment {

    //============= CONSTANTS ==================================

    //============= VARIABLES ==================================

    protected BaseActivity mainActivity;

    //============= VIEWS ======================================

    protected View rootView;

    public BaseFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = initView(inflater,container);
        ButterKnife.bind(this,rootView);

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAfterViewCreated();
    }

    protected abstract View initView(LayoutInflater inflater,ViewGroup container);

    protected void initAfterViewCreated(){

    }

    public void onEvent(OnEventBusListener event){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (BaseActivity)activity;
    }
}
