package com.oicmap.beautifoto.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oicmap.beautifoto.network.api.FlickrApi;
import com.oicmap.beautifoto.network.handle.ErrorRetrofitHandlerException;
import com.squareup.okhttp.OkHttpClient;

import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by khacpham on 11/14/15.
 */
public class NetworkMng {
    private static NetworkMng instance;

    private FlickrApi flickrApi;

    public static NetworkMng getInstance() {
        if (instance == null) {
            instance = new NetworkMng();
        }
        return instance;
    }

    private NetworkMng() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(5, TimeUnit.MINUTES);
        okHttpClient.setConnectTimeout(5, TimeUnit.MINUTES);
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
        } catch (GeneralSecurityException e) {
            throw new AssertionError(); // The system has no TLS. Just give up.
        }
        okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());

        // FLICKR API
        RequestInterceptor requestInterceptorKoala = new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Accept", "application/json");

                request.addQueryParam("api_key", FlickrApi.KEY_API);
                request.addQueryParam("per_page",FlickrApi.PERPAGE+"");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setErrorHandler(new ErrorRetrofitHandlerException())
                .setEndpoint(FlickrApi.SV_PROD)
                .setRequestInterceptor(requestInterceptorKoala)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("RETROFIT:BF"))
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient))
                .build();
        flickrApi = restAdapter.create(FlickrApi.class);

    }

    public FlickrApi getFlickrApi(){
        return flickrApi;
    }
}
