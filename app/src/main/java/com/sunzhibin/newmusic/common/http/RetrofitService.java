package com.sunzhibin.newmusic.common.http;


import com.sunzhibin.newmusic.ui.model.splash.SplashBean;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2016/10/6.
 */
public interface RetrofitService {
    @GET(Api.SPLASH_URL)
    Flowable<SplashBean> getSplashImageView();

    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Url String url);

}
