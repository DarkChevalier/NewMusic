package com.sunzhibin.newmusic.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ImageView;

import com.sunzhibin.newmusic.R;
import com.sunzhibin.newmusic.base.BaseAbstractActivity;
import com.sunzhibin.newmusic.base.factory.CreatePresenter;
import com.sunzhibin.newmusic.ui.constract.SplashConstract;
import com.sunzhibin.newmusic.ui.presenter.SplashPresenter;
import com.sunzhibin.newmusic.utils.PermissionReq;
import com.sunzhibin.newmusic.utils.loadimageview.ImageLoaderHelper;
import com.sunzhibin.newmusic.utils.loadimageview.LoaderOptions;

/**
 * @author: sunzhibin
 * @date: 2018/1/3.
 * @description:
 * @e-mail:
 */
@CreatePresenter(SplashPresenter.class)
public class SplashActivity extends BaseAbstractActivity<SplashConstract.ISplashView, SplashPresenter> implements SplashConstract.ISplashView {
    private ImageView iv_splash;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 3000);
    }

    @Override
    protected void initView() {
        iv_splash = findViewById(R.id.iv_splash);
    }

    @Override
    protected void initData() {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionReq.with(this).permissions(permissions).result(new PermissionReq.Result() {
            @Override
            public void onGranted() {
                getPresenter().querySplashView();
            }

            @Override
            public void onDenied() {

            }
        }).request();
        //查询封面
        getPresenter().querySplashView();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void requestSuccess(String result) {
        if (iv_splash.getBackground() == null) {
            ImageLoaderHelper.getInstance().loadImage(this, iv_splash, result, new LoaderOptions.Builder().build());
        }
    }

    @Override
    public void requestFail(String result) {
        if (!TextUtils.isEmpty(result)) {
            ImageLoaderHelper.getInstance().loadImage(this, iv_splash, result, new LoaderOptions.Builder().build());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
