package com.sunzhibin.newmusic.utils.loadimageview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public interface ILoaderProxy {

    void loadImage(Context context, View view, String path, LoaderOptions options);

    void loadImage(Context context, View view, int drawable, LoaderOptions options);

    void loadImage(Context context, View view, File file, LoaderOptions options);

    /**
     * 保存图片到本地相册
     *
     * @param url
     * @param destFile
     * @return
     */
    boolean saveImage(Context context, String url, File destFile);

    /**
     * 清理内存缓存
     */
    void clearMemoryCache(Context context);

    /**
     * 清理磁盘缓存
     */
    void clearDiskCache(Context context);

    void onTrimMemory(Context context, int level);

    void clearLoad(Context context, ImageView imageView);
}
