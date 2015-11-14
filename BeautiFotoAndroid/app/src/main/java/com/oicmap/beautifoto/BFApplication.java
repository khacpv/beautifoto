package com.oicmap.beautifoto;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.view.Display;
import android.view.WindowManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.oicmap.beautifoto.common.activeandroid.ActiveAndroidConf;
import com.oicmap.beautifoto.config.GlobalStorage;
import com.oicmap.beautifoto.database.api.DbApi;

/**
 * Created by khacpham on 11/14/15.
 */
public class BFApplication extends Application{
    public static BFApplication instance;

    public static BFApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

        ActiveAndroidConf.initAaDb(this, DbApi.DB_NAME, DbApi.DB_VER);

        initScreenSize();

        initImageLoader();
    }

    /**
     * get device screen size to GlobalStorage
     * */
    private void initScreenSize(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
        } else {
            size.set(display.getWidth(), display.getHeight());
        }
        GlobalStorage.SCREEN = new Rect(0,0,size.x,size.y);
    }

    private void initImageLoader() {
        int size = GlobalStorage.SCREEN.width();
        int width = size * 3 / 4;
        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(getApplicationContext()).threadPriority(
                        Thread.NORM_PRIORITY - 2)
                        .threadPoolSize(3)
                        .denyCacheImageMultipleSizesInMemory()
                        .memoryCacheSizePercentage(5)
                        .diskCacheSize(50 * 1024 * 1024)
                        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        .diskCacheExtraOptions(width, size, null)
                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                        .build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
