package com.sunzhibin.newmusic;

import android.app.Application;

import com.sunzhibin.newmusic.utils.loadimageview.GlideLoader;
import com.sunzhibin.newmusic.utils.loadimageview.ImageLoaderHelper;

/**
 * @author: sunzhibin
 * @date: 2018/1/3.
 * @description:
 * @e-mail:
 */

public class MainApplication extends Application {
    private static MainApplication instance;
    public static synchronized MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化图片框架
        ImageLoaderHelper.getInstance().setImageLoader(new GlideLoader());

    }


}
