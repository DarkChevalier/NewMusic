package com.sunzhibin.newmusic.base.proxy;

import android.os.Bundle;
import android.util.Log;

import com.sunzhibin.newmusic.base.factory.IPresenterFactory;
import com.sunzhibin.newmusic.base.presenter.BasePresenter;
import com.sunzhibin.newmusic.base.view.IBaseView;

/**
 * Created by sunzhibin on 2018/1/2.
 */

public class BaseMvpProxy<V extends IBaseView, P extends BasePresenter<V>>
        implements IPresenterProxyInterface<V, P> {
    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";
    /**
     * Presenter工厂类
     */
    private IPresenterFactory<V, P> mPresenterFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttchView;

    public BaseMvpProxy(IPresenterFactory<V, P> mPresenterFactory) {
        this.mPresenterFactory = mPresenterFactory;
    }

    /**
     * 设置Presenter的工厂类,这个方法只能在创建Presenter之前调用,也就是调用getPresenter()之前，如果Presenter已经创建则不能再修改
     *
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(IPresenterFactory<V, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mPresenterFactory = presenterFactory;
    }

    @Override
    public IPresenterFactory<V, P> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    public P getPresenter() {
        if (mPresenterFactory != null) {
            if (mPresenter == null) {
                mPresenter = mPresenterFactory.createPresenter();
                mPresenter.onCreatePresenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        Log.e("perfect-mvp", "Proxy getMvpPresenter = " + mPresenter);
        return mPresenter;
    }

    /**
     * 绑定Presenter和view
     *
     * @param rootView
     */
    public void onResume(V rootView) {
        getPresenter();
        if (mPresenter != null && !mIsAttchView) {
            mPresenter.onAttachView(rootView);
            mIsAttchView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachView() {
        if (mPresenter != null && mIsAttchView) {
            mPresenter.onDetachView();
            mIsAttchView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        if (mPresenter != null) {
            onDetachView();
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        getPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mBundle = savedInstanceState;

    }


}
