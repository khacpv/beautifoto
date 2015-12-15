package com.oicmap.beautifoto.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oicmap.beautifoto.common.eventbus.api.OnEventBusListener;

import de.greenrobot.event.EventBus;

/**
 * Created by khacpham on 11/14/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    public void onEvent(OnEventBusListener event){

    }
}
