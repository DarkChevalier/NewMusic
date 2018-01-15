package com.sunzhibin.newmusic.model.splash;

import com.sunzhibin.newmusic.base.RequestMode;
import com.sunzhibin.newmusic.base.mode.BaseBean;
import com.sunzhibin.newmusic.common.http.HttpHelper;

import io.reactivex.Flowable;

/**
 * @author: sunzhibin
 * @date: 2018/1/15.
 * @description:
 * @e-mail:
 */

public class SplashModel extends RequestMode<SplashBean> {

    public Flowable<SplashBean> querySplashView() {
        return HttpHelper.getInstance().getRetrofitServiceWwithoutBaseUrl().getSplashImageView();

    }

}
