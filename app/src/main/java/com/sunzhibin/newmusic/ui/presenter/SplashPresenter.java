package com.sunzhibin.newmusic.ui.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunzhibin.newmusic.base.mode.IBaseModel;
import com.sunzhibin.newmusic.base.presenter.BasePresenter;
import com.sunzhibin.newmusic.ui.constract.SplashConstract;
import com.sunzhibin.newmusic.ui.model.splash.SplashBean;
import com.sunzhibin.newmusic.ui.model.splash.SplashModel;
import com.sunzhibin.newmusic.utils.CommonSubscriber;
import com.sunzhibin.newmusic.utils.RxUtils;

import io.reactivex.functions.Function;

/**
 * @author
 * @date 2017/11/17
 * @description
 */
public class SplashPresenter extends BasePresenter<SplashConstract.ISplashView> {
    public SplashPresenter(IBaseModel mRequestMode) {
        this.mRequestMode = mRequestMode;
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

    public void querySplashView() {
        addSubscribe(((SplashModel) mRequestMode).querySplashView()
                .compose(RxUtils.<SplashBean>rxSchedulerHelper())
                .map(new Function<SplashBean, SplashBean>() {
                    @Override
                    public SplashBean apply(SplashBean splashBean) throws Exception {
                        return splashBean;
                    }
                }).subscribeWith(new CommonSubscriber<SplashBean>(getmRootView()) {
                    @Override
                    public void onNext(SplashBean splashBean) {
                        getmRootView().requestSuccess(splashBean.getUrl());
                    }
                }));
    }
}
