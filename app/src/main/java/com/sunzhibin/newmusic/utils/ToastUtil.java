package com.sunzhibin.newmusic.utils;

import android.widget.Toast;

import com.sunzhibin.newmusic.MainApplication;

/**
 * Created by jingbin on 2016/12/14.
 * 单例Toast
 */

public class ToastUtil {

    private static Toast mToast;

    public static void shortShow(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(MainApplication.getInstance(), text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }
}
