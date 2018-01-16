package com.sunzhibin.newmusic;

import android.widget.ImageView;

import com.sunzhibin.newmusic.base.BaseAbstractActivity;
import com.sunzhibin.newmusic.base.factory.CreatePresenter;
import com.sunzhibin.newmusic.ui.constract.SplashConstract;
import com.sunzhibin.newmusic.ui.presenter.SplashPresenter;
import com.sunzhibin.newmusic.utils.bind.FieldView;

/**
 * @author: sunzhibin
 * @date: 2018/1/3.
 * @description:
 * @e-mail:
 */
@CreatePresenter(SplashPresenter.class)
public class SplashActivity extends BaseAbstractActivity<SplashConstract.ISplashView, SplashPresenter> implements SplashConstract.ISplashView {
    @FieldView(R.id.iv_splash)
    ImageView iv_splash;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //查询封面
        getPresenter().querySplashView();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void requestSuccess(String result) {


    }
}
