package com.sunzhibin.newmusic.common.http;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/10/6.
 * 网络请求帮助类
 */
public class HttpHelper {
    private static volatile HttpHelper singleton;
    private static String TAG = "HttpHelper";

    private HttpHelper() {
    }

    public static HttpHelper getInstance() {
        if (singleton == null) {
            synchronized (HttpHelper.class) {
                if (singleton == null) {
                    singleton = new HttpHelper();
                }
            }
        }
        return singleton;
    }

    public RetrofitService getRetrofitService() {
        return getRetrofit(true).create(RetrofitService.class);
    }

    public RetrofitService getRetrofitServiceWwithoutBaseUrl() {
        return getRetrofit(false).create(RetrofitService.class);
    }

    private OkHttpClient getOkhttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    @NonNull
    private Retrofit getRetrofit(boolean baseUrl) {
        return createBuilder(baseUrl)
//                //配置基础的url
                .baseUrl(Api.BASE_URL)
                //配置提交或者返回的参数的造型方式为gson
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                //返回值可以使用Obserable
                //使用https时需要配置
                .client(getOkhttpClient())
                .build();
    }

    private Retrofit.Builder createBuilder(boolean baseUrl) {
        if (baseUrl) {
            return new Retrofit
                    .Builder().baseUrl(Api.BASE_URL);
        } else {
            return new Retrofit
                    .Builder();
        }

    }

}
