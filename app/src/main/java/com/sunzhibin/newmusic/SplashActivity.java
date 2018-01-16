package com.sunzhibin.newmusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.sunzhibin.newmusic.base.BaseAbstractActivity;
import com.sunzhibin.newmusic.base.factory.CreatePresenter;
import com.sunzhibin.newmusic.ui.constract.SplashConstract;
import com.sunzhibin.newmusic.ui.presenter.SplashPresenter;
import com.sunzhibin.newmusic.utils.bind.FieldView;
import com.sunzhibin.newmusic.utils.loadimageview.ImageLoaderHelper;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

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
        ImageLoaderHelper.getInstance().loadImage(this, iv_splash, result, null);
    }
}
