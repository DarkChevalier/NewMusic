package com.sunzhibin.newmusic;

import android.app.Application;

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
    }


}
