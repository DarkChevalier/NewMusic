package com.sunzhibin.newmusic.utils.loadimageview;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sunzhibin.newmusic.R;

import java.io.File;

public class GlideLoader implements ILoaderProxy {
    public GlideLoader() {

    }

    @Override
    public void loadImage(Context context, View view, String path, LoaderOptions options) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;

            loadImageView(context, path, options, imageView);
        }
    }

    @Override
    public void loadImage(Context context, View view, @DrawableRes int drawable, LoaderOptions options) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            loadImageView(context, drawable, options, imageView);
        }
    }

    @Override
    public void loadImage(Context context, View view, File file, LoaderOptions options) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            loadImageView(context, file, options, imageView);
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
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            Glide.get(context).clearDiskCache();
        }
    }

    @Override
    public void onTrimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);

    }

    @Override
    public void clearLoad(Context context, ImageView imageView) {

    }

    private void loadImageView(Context context,
                               String url,
                               LoaderOptions options,
                               ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(options == null ? createDefaultOptions() : createRequestOptions(options))
                .into(imageView);
    }

    private void loadImageView(Context context,
                               @DrawableRes int drawable,
                               LoaderOptions options,
                               ImageView imageView) {
        Glide.with(context)
                .load(drawable)
                .apply(options == null ? createDefaultOptions() : createRequestOptions(options))
                .into(imageView);
    }

    private void loadImageView(Context context, File file,
                               LoaderOptions options,
                               ImageView imageView) {
        Glide.with(context)
                .load(file)
                .apply(options == null ? createDefaultOptions() : createRequestOptions(options))
                .into(imageView);
    }

    private RequestOptions createDefaultOptions() {
        return new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
    }

    private RequestOptions createRequestOptions(LoaderOptions options) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(options.placeholderResId)
                .error(options.errorResId);
        if (options.isCenterCrop) {
            requestOptions = requestOptions.centerCrop();
        }
        if (options.isCenterInside) {
            requestOptions = requestOptions.centerInside();
        }
        if (options.targetHeight != 0 && options.targetWidth != 0) {
            requestOptions = requestOptions.override(options.targetWidth, options.targetHeight);
        }
        return requestOptions;
    }

}
