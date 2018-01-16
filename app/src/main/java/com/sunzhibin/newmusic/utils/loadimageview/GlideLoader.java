package com.sunzhibin.newmusic.utils.loadimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class GlideLoader implements ILoaderProxy {
    private final String PICASSO_CACHE = "picasso-cache";
//    private Context context = MainApplication.getInstance();

    public GlideLoader() {

    }

    @Override
    public void loadImage(Context context, View view, String path, LoaderOptions options) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            loaderOptions(context, imageView, path, options);
        }
    }

    @Override
    public void loadImage(Context context, View view, int drawable, LoaderOptions options) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            loaderOptions(context, imageView, drawable, options);
        }
    }

    @Override
    public void loadImage(Context context, View view, File file, LoaderOptions options) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            loaderOptions(context, imageView, file, options);
        }
    }

    @Override
    public boolean saveImage(Context context, String url, final File destFile) {

        return false;
    }

    @Override
    public void clearMemoryCache(Context context) {
        // 必须在UI线程中调用
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        // 必须在后台线程中调用，建议同时clearMemory()
//        Glide.get(context).clearDiskCache();
    }

    @Override
    public void onTrimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);

    }

    @Override
    public void clearLoad(Context context, ImageView imageView) {

    }

    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public static void loadIntoUseFitWidth(final Context context, final String imageUrl, final ImageView imageView) {
        LoaderOptions options = creatDetaultOptions();

    }

    private void loaderOptions(Context context, ImageView view, String path, LoaderOptions options) {
        if (options == null) {
            options = creatDetaultOptions();
        }

    }

    private void loaderOptions(Context context, ImageView view, int resId, LoaderOptions options) {
        if (options == null) {
            options = creatDetaultOptions();
        }

    }

    private void loaderOptions(Context context, ImageView view, File file, LoaderOptions options) {
        if (options == null) {
            options = creatDetaultOptions();
        }

    }

    private static LoaderOptions creatDetaultOptions() {
        LoaderOptions options = new LoaderOptions.Builder()
//                .angle()
                .centerCrop()
//                .config(Bitmap.Config.RGB_565)
//                .error()
//                .placeholder()
//                .resize()
                .build();


        return options;
    }

    /**
     * load normal  for  circle or round img
     *
     * @param url
     * @param erroImg
     * @param emptyImg
     * @param iv
     * @param tag
     */
    public static void glideCircleLoader(Context context, String url, int erroImg, int emptyImg, ImageView iv, int tag) {
        glideCircleLoader(context, url, erroImg, emptyImg, iv, tag, 10);
    }

    public static void glideCircleLoader(Context context, String url, int erroImg, int emptyImg, ImageView iv, int tag, int round) {
        if (0 == tag) {

        } else if (1 == tag) {

        }
    }
}
