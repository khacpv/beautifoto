package com.oicmap.beautifoto.common.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;

    public static void showLongToast(Context context, String message) {
        if (null == context) return;
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showShortToast(Context context, String message) {
        if (null == context) return;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToastWithTime(Context context, String message, int time) {
        if (null == context) return;
        final Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
        Handler h = new Handler();
        h.postDelayed(new Runnable() {

            @Override
            public void run() {
                toast.cancel();
            }
        }, time);
    }
}
