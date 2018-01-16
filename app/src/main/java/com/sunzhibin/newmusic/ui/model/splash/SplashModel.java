package com.sunzhibin.newmusic.ui.model.splash;

import com.sunzhibin.newmusic.common.http.HttpHelper;
import com.sunzhibin.newmusic.ui.constract.SplashConstract;

import io.reactivex.Flowable;

/**
 * @author: sunzhibin
 * @date: 2018/1/15.
 * @description:
 * @e-mail:
 */

public class SplashModel implements SplashConstract.ISplashModel {
    @Override
    public Flowable<SplashBean> querySplashView() {
        return HttpHelper.getInstance().getRetrofitService().getSplashImageView();
    }

}
