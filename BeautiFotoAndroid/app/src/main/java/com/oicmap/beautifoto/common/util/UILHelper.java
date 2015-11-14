package com.oicmap.beautifoto.common.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.oicmap.beautifoto.R;

public class UILHelper {
    private static final String PREFIX_DECO = "deco";
    private static final String PREFIX_HTTP = "http";
    private static UILHelper INSTANCE;
    private ImageLoader imageLoader = null;
    private DisplayImageOptions avatarOptions =
            new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)
//                    .showImageForEmptyUri(R.drawable.ic_fb_avatar_preload)
//                    .showImageOnFail(R.drawable.ic_fb_avatar_preload)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .resetViewBeforeLoading(false)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(false)
                    .build();

    private DisplayImageOptions avatarLoadingOptions =
            new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                    .resetViewBeforeLoading(false)
                    .cacheOnDisk(true)
                    .cacheInMemory(false)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(false)
                    .build();

    private DisplayImageOptions thumbnailOptions =
            new DisplayImageOptions.Builder()
//                    .showImageOnLoading(R.drawable.bg_stub_loading)
//                    .showImageForEmptyUri(R.drawable.bg_stub_error)
//                    .showImageOnFail(R.drawable.bg_stub_error)
                    .resetViewBeforeLoading(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    .considerExifParams(false)
                    .build();

    private DisplayImageOptions fileThumbnailOptions =
            new DisplayImageOptions.Builder().resetViewBeforeLoading(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .cacheOnDisk(false)
                    .cacheInMemory(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(false)
                    .build();

    private DisplayImageOptions photoOptions =
            new DisplayImageOptions.Builder()
//                    .showImageOnLoading(R.drawable.bg_stub_loading)
//                    .showImageForEmptyUri(R.drawable.bg_stub_error)
//                    .showImageOnFail(R.drawable.bg_stub_error)
                    .resetViewBeforeLoading(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(false)
                    .build();

    private DisplayImageOptions photoOverlayOptions =
            new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(false)
                    .build();

    private DisplayImageOptions photoOptionsWithoutCaching =
            new DisplayImageOptions.Builder()
//                    .showImageOnLoading(R.drawable.bg_stub_loading)
//                    .showImageForEmptyUri(R.drawable.bg_stub_error)
//                    .showImageOnFail(R.drawable.bg_stub_error)
                    .resetViewBeforeLoading(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .cacheOnDisk(true)
                    .cacheInMemory(false)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(false)
                    .build();

    private DisplayImageOptions iconOptions =
            new DisplayImageOptions.Builder().resetViewBeforeLoading(true)
                    .cacheOnDisk(false)
                    .cacheInMemory(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .considerExifParams(false)
                    .build();

    private UILHelper() {
    }

    public static UILHelper getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new UILHelper();
        }
        return INSTANCE;
    }

    public ImageLoader getImageLoader() {
        if (null == imageLoader) imageLoader = ImageLoader.getInstance();
        return imageLoader;
    }

    public void displayFacebookAvatar(String url, ImageView imvAvatar) {
        getImageLoader().displayImage(url, imvAvatar, avatarOptions);
    }

    public void displayThumbnail(String url, ImageView imvThumbnail) {
        getImageLoader().displayImage(url, imvThumbnail, thumbnailOptions);
    }

    public void displayThumbnailFile(String filePath, ImageView imvThumbnail) {
        getImageLoader().displayImage(String.format("file://%s", filePath), imvThumbnail,
                fileThumbnailOptions);
    }

    public void displayPhoto(String url, ImageView imvPhoto) {
        getImageLoader().displayImage(url, imvPhoto, photoOptions);
    }

    public void displayOverlay(String url, ImageView imvPhoto){
        getImageLoader().displayImage(url,imvPhoto,photoOverlayOptions);
    }

    public void loadImage(String url, DisplayImageOptions imageOptions,
                          ImageLoadingListener listener) {
        getImageLoader().loadImage(url, imageOptions, listener);
    }

    public void loadPhotoWithoutCaching(String url, ImageLoadingListener listener) {
        getImageLoader().loadImage(url, photoOptionsWithoutCaching, listener);
    }

    public void loadImage(String url, ImageLoadingListener listener) {
        getImageLoader().loadImage(url, avatarOptions, listener);
    }

    public void displayIcon(String url, ImageView imvIcon) {
        getImageLoader().displayImage(url, imvIcon, iconOptions);
    }

    public void displayIconFromAssets(String filePath, ImageView imvIcon) {
        getImageLoader().displayImage(String.format("assets://%s", filePath), imvIcon, iconOptions);
    }

    public void displayIconFromSDCard(String filePath, ImageView imvIcon) {
        getImageLoader().displayImage(String.format("file://%s", filePath), imvIcon, iconOptions);
    }

    public void clearMemoryCache() {
        getImageLoader().getMemoryCache().clear();
    }

    public void clearMemoryCache(String name) {
        MemoryCacheUtils.removeFromCache(name, getImageLoader().getMemoryCache());
    }

    public void cancelLoading(ImageView imageView) {
        getImageLoader().cancelDisplayTask(imageView);
    }

    public void displayPhoto(String url, ImageView imvPhoto, ImageLoadingListener listener) {
        getImageLoader().displayImage(url, imvPhoto, photoOptions, listener);
    }

    public void displayPhotoWithoutCaching(String url, ImageView imvPhoto,
                                           ImageLoadingListener listener) {
        if (null != url && url.startsWith("http")) {
            getImageLoader().displayImage(url, imvPhoto, photoOptionsWithoutCaching, listener);
        } else if (null != url) {
            getImageLoader().displayImage(String.format("file://%s", url), imvPhoto,
                    photoOptionsWithoutCaching, listener);
        }
    }

    public void loadAvatar(String url, ImageLoadingListener listener) {
        getImageLoader().loadImage(url, avatarLoadingOptions, listener);
    }

    public void loadAvatarForGroup(String url, ImageLoadingListener listener) {
        ImageSize targetSize = new ImageSize(120, 120);
        ImageAware imageAware = new NonViewAware(targetSize, ViewScaleType.FIT_INSIDE);

        getImageLoader().displayImage(url, imageAware, avatarLoadingOptions, listener);
    }

    public void displayFacebookAvatarFromAnyProtocol(String url, ImageView imvAvatar) {
        if (null != url && url.startsWith("http")) {
            getImageLoader().displayImage(url, imvAvatar, avatarOptions);
        } else {
            getImageLoader().displayImage(String.format("file://%s", url), imvAvatar,
                    avatarOptions);
        }
    }

    @Deprecated
    public void displayDecoThumbnail(String url, ImageView imvThumbnail) {
        if (null == url) return;
        if (url.startsWith(PREFIX_DECO)) {
            url = String.format("assets://%s", url);
        } else if (url.startsWith(PREFIX_HTTP)) {
            // do nothing
        } else {
            url = String.format("file://%s", url);
        }
        getImageLoader().displayImage(url, imvThumbnail, thumbnailOptions);
    }

    @Deprecated
    public void displayDecoThumbnail(String url, ImageView imvThumbnail,ImageLoadingListener listener) {
        if (null == url) return;
        if (url.startsWith(PREFIX_DECO)) {
            url = String.format("assets://%s", url);
        } else if (url.startsWith(PREFIX_HTTP)) {
            // do nothing
        } else {
            url = String.format("file://%s", url);
        }
        getImageLoader().displayImage(url, imvThumbnail, thumbnailOptions,listener);
    }
}
