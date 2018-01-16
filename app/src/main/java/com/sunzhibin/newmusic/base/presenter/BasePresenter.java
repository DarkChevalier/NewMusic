package com.sunzhibin.newmusic.base.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunzhibin.newmusic.base.mode.IBaseModel;
import com.sunzhibin.newmusic.base.view.IBaseView;
import com.sunzhibin.newmusic.component.RxBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by sunzhibin on 2018/1/2.
 */

public class BasePresenter<V extends IBaseView> {

    protected IBaseModel mRequestMode;
    protected CompositeDisposable mCompositeDisposable;

    /**
     * View 层
     */
    private V mRootView;

    /**
     * 创建Presenter后调用
     *
     * @param savedState
     */
    public void onCreatePersenter(@Nullable Bundle savedState) {

    }

    /**
     * 绑定View
     */
    public void onAttachView(V rootView) {
        this.mRootView = rootView;
    }

    /**
     * 解除绑定View
     */
    public void onDetachView() {
        this.mRootView = null;
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
        unSubscribe();
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 获取V层接口View
     *
     * @return 返回当前MvpView
     */
    public V getmRootView() {
        return mRootView;
    }


    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxBus.getDefault().toDefaultFlowable(eventType, act));
    }

}
