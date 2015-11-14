package com.oicmap.beautifoto;

import android.content.Context;
import android.content.Intent;

import com.google.android.gcm.GCMBaseIntentService;
import com.oicmap.beautifoto.config.Constant;

/**
 * Created by khacpham on 9/7/15.
 */
public class GCMIntentService extends GCMBaseIntentService {

    public GCMIntentService() {
        super(Constant.GCMSetting.APP_SENDER_ID);
    }

    @Override
    protected void onMessage(final Context context, Intent intent) {

    }

    @Override
    protected void onError(Context context, String s) {

    }

    @Override
    protected void onRegistered(Context context, String gcmToken) {

    }

    @Override
    protected void onUnregistered(Context context, String s) {

    }

}
