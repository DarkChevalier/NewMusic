package com.sunzhibin.newmusic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunzhibin.newmusic.base.presenter.IBasePresenter;
import com.sunzhibin.newmusic.component.RxBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author
 * @date 2017/11/17
 * @description
 */
public abstract class RequestPresenter extends IBasePresenter<IRequestView> {

    protected RequestMode mRequestMode;
    protected CompositeDisposable mCompositeDisposable;

    public RequestPresenter(RequestMode mRequestMode) {
        this.mRequestMode = mRequestMode;
    }

    @Override
    public void onCreatePersenter(@Nullable Bundle savedState) {
        super.onCreatePersenter(savedState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyPersenter() {
        super.onDestroyPersenter();
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
