package com.sunzhibin.newmusic.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunzhibin.newmusic.R;
import com.sunzhibin.newmusic.base.BaseAbstractActivity;
import com.sunzhibin.newmusic.base.factory.CreatePresenter;
import com.sunzhibin.newmusic.base.presenter.BasePresenter;
import com.sunzhibin.newmusic.base.view.IBaseView;

@CreatePresenter(BasePresenter.class)
public class MainActivity extends BaseAbstractActivity implements IBaseView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {

    }

}
