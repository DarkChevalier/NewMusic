package com.sunzhibin.newmusic.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunzhibin.newmusic.base.factory.IPresenterFactory;
import com.sunzhibin.newmusic.base.factory.PresenterFactoryImpl;
import com.sunzhibin.newmusic.base.presenter.BasePresenter;
import com.sunzhibin.newmusic.base.proxy.BaseMvpProxy;
import com.sunzhibin.newmusic.base.proxy.IPresenterProxyInterface;
import com.sunzhibin.newmusic.base.view.IBaseView;

/**
 * @author: sunzhibin
 * @date: 2018/1/3.
 * @description:
 * @e-mail:
 */

public abstract class BaseAbstractFragment<V extends IBaseView, P extends BasePresenter<V>> extends Fragment implements IPresenterProxyInterface<V, P>, IBaseView {
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    protected View mRootView;
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterFactoryImpl.<V, P>createFactory(getClass()));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        initView();
        initData();
        initListener();
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        mProxy.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setPresenterFactory(IPresenterFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public IPresenterFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }

    protected abstract int getLayoutId();

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }


}
