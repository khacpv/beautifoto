package com.oicmap.beautifoto.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {
    public static boolean isWifiConnect(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo wifi =
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkIsAvailable(context)) {
            if (wifi.isAvailable()
                    && wifi.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

    public static boolean networkIsAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isOnline(Context ct) {
        ConnectivityManager conMgr = (ConnectivityManager) ct
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()
                || !netInfo.isConnectedOrConnecting()) {
            return false;
        } else {
        }
        return true;
    }
}
