package com.sunzhibin.newmusic.common.http;


import com.sunzhibin.newmusic.model.splash.SplashBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/10/6.
 */
public interface RetrofitService {

    @GET(Api.SPLASH_URL)
    Flowable<SplashBean> getSplashImageView();

}
