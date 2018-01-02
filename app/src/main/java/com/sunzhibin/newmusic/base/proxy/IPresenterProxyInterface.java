package com.sunzhibin.newmusic.base.proxy;

import com.sunzhibin.newmusic.base.factory.IPresenterFactory;
import com.sunzhibin.newmusic.base.presenter.IBasePresenter;
import com.sunzhibin.newmusic.base.view.IBaseView;

/**
 * Created by sunzhibin on 2018/1/2.
 */

public interface IPresenterProxyInterface<V extends IBaseView, P extends IBasePresenter<V>> {
    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(IPresenterFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    IPresenterFactory<V, P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getPresenter();

}
