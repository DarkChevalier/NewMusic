package com.sunzhibin.newmusic.ui.constract;

import com.sunzhibin.newmusic.base.mode.IBaseModel;
import com.sunzhibin.newmusic.base.view.IBaseView;
import com.sunzhibin.newmusic.ui.model.splash.SplashBean;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * @author: sunzhibin
 * @date: 2018/1/16.
 * @description:
 * @e-mail:
 */

public interface SplashConstract {
    interface ISplashView extends IBaseView {
        void requestSuccess(String result);
    }

    interface ISplashModel extends IBaseModel {
        Flowable<SplashBean> querySplashView();

        Flowable<ResponseBody> downloadFile(String url);

    }

}
