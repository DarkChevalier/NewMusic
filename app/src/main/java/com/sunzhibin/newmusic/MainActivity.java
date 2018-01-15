package com.sunzhibin.newmusic;

import com.sunzhibin.newmusic.base.BaseAbstractActivity;
import com.sunzhibin.newmusic.base.IRequestView;
import com.sunzhibin.newmusic.base.factory.CreatePresenter;
import com.sunzhibin.newmusic.base.mode.BaseBean;
import com.sunzhibin.newmusic.base.presenter.IBasePresenter;

@CreatePresenter(IBasePresenter.class)
public class MainActivity extends BaseAbstractActivity implements IRequestView {
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

    @Override
    public void requestLoading() {

    }

    @Override
    public void resultSuccess(BaseBean result) {

    }

    @Override
    public void resultFailure(String result) {


    }

}
