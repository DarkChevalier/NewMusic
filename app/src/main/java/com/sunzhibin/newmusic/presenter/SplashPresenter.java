package com.sunzhibin.newmusic.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunzhibin.newmusic.base.RequestMode;
import com.sunzhibin.newmusic.base.RequestPresenter;


/**
 * @author: sunzhibin
 * @date: 2018/1/15.
 * @description:
 * @e-mail:
 */

public class SplashPresenter extends RequestPresenter {

    public SplashPresenter(RequestMode mRequestMode) {
        super(mRequestMode);
    }

    @Override
    public void onCreatePersenter(@Nullable Bundle savedState) {
        super.onCreatePersenter(savedState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyPersenter() {
        super.onDestroyPersenter();
    }

    //查询splash
    private void querySplashView() {

    }
}
