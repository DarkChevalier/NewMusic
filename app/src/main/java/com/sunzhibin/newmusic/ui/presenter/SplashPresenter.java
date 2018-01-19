package com.sunzhibin.newmusic.ui.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sunzhibin.newmusic.base.presenter.BasePresenter;
import com.sunzhibin.newmusic.ui.constract.SplashConstract;
import com.sunzhibin.newmusic.ui.model.splash.SplashBean;
import com.sunzhibin.newmusic.ui.model.splash.SplashModel;
import com.sunzhibin.newmusic.utils.CommonSubscriber;
import com.sunzhibin.newmusic.utils.RxUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author
 * @date 2017/11/17
 * @description
 */
public class SplashPresenter extends BasePresenter<SplashConstract.ISplashView> {
    private String splashPic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/splash.png";

    public SplashPresenter() {
        mRequestMode = new SplashModel();
    }

    @Override
    public void onCreatePresenter(@Nullable Bundle savedState) {
        super.onCreatePresenter(savedState);

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
        if (readSplashBitmap() != null && getmRootView() != null) {
            getmRootView().requestFail(splashPic);
        }
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
                        if (getmRootView() != null)
                            getmRootView().requestSuccess(splashBean.getUrl());
                        updateSplashView(splashBean.getUrl());
                    }
                }));
    }

    //更新Splash界面
    private void updateSplashView(String url) {
        ((SplashModel) mRequestMode).downloadFile(url)
                .compose(new FlowableTransformer<ResponseBody, ResponseBody>() {
                    @Override
                    public Flowable<ResponseBody> apply(Flowable<ResponseBody> observable) {
                        return observable.subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io());
                    }
                })
                .map(new Function<ResponseBody, ResponseBody>() {
                    @Override
                    public ResponseBody apply(ResponseBody responseBody) throws Exception {
                        writeResponseBodyToDisk(responseBody);
                        return responseBody;
                    }
                })
                .subscribe();
    }

    private Bitmap readSplashBitmap() {
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeFile(splashPic);
        return bitmap;
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            File futureStudioIconFile = new File(splashPic);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Log.d("sunzhibin", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


}
