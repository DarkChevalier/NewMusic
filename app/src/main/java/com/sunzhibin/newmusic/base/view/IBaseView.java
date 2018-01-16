package com.sunzhibin.newmusic.base.view;

/**
 * Created by sunzhibin on 2018/1/2.
 */

public interface IBaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
