package com.sunzhibin.newmusic;

import android.widget.ImageView;

import com.sunzhibin.newmusic.base.BaseAbstractActivity;
import com.sunzhibin.newmusic.base.IRequestView;
import com.sunzhibin.newmusic.base.factory.CreatePresenter;
import com.sunzhibin.newmusic.base.mode.BaseBean;
import com.sunzhibin.newmusic.base.presenter.IBasePresenter;
import com.sunzhibin.newmusic.utils.bind.FieldView;

/**
 * @author: sunzhibin
 * @date: 2018/1/3.
 * @description:
 * @e-mail:
 */
@CreatePresenter(IBasePresenter.class)
public class SplashActivity extends BaseAbstractActivity implements IRequestView {
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
