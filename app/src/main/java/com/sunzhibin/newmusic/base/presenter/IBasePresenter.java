package com.sunzhibin.newmusic.base.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunzhibin.newmusic.base.view.IBaseView;

/**
 * Created by sunzhibin on 2018/1/2.
 */

public class IBasePresenter<V extends IBaseView> {
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
}
