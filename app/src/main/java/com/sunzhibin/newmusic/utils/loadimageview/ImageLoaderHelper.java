package com.sunzhibin.newmusic.utils.loadimageview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * 图片管理类，提供对外接口。
 * 静态代理模式，开发者只需要关心ImageLoader + LoaderOptions
 * Created by MhListener on 2017/6/27.
 */

public class ImageLoaderHelper implements ILoaderProxy {
    private static ILoaderProxy sLoader;
    private static volatile ImageLoaderHelper sInstance;

    private ImageLoaderHelper() {
        sLoader = new GlideLoader();
    }

    //单例模式
    public static ImageLoaderHelper getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoaderHelper.class) {
                if (sInstance == null) {
                    //若切换其它默认图片加载框架，可以实现一键替换
                    sInstance = new ImageLoaderHelper();
                }
            }
        }
        return sInstance;
    }

    //可以随时替换图片加载框架
    public void setImageLoader(ILoaderProxy loader) {
        if (loader != null) {
            sLoader = loader;
        }
    }

    @Override
    public void loadImage(Context context, View view, String path, LoaderOptions options) {
        sLoader.loadImage(context, view, path, options);
    }

    @Override
    public void loadImage(Context context, View view, int drawable, LoaderOptions options) {
        sLoader.loadImage(context, view, drawable, options);
    }

    @Override
    public void loadImage(Context context, View view, File file, LoaderOptions options) {
        sLoader.loadImage(context, view, file, options);
    }

    @Override
    public boolean saveImage(Context context, String url, File destFile) {
        return sLoader.saveImage(context, url, destFile);
    }

    @Override
    public void clearMemoryCache(Context context) {
        sLoader.clearMemoryCache(context);
    }

    @Override
    public void clearDiskCache(Context context) {
        sLoader.clearDiskCache(context);
    }

    @Override
    public void onTrimMemory(Context context, int level) {
        sLoader.onTrimMemory(context, level);
    }

    @Override
    public void clearLoad(Context context, ImageView imageView) {
        sLoader.clearLoad(context, imageView);
    }


}