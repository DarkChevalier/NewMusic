package com.sunzhibin.newmusic.base;

import com.sunzhibin.newmusic.base.mode.BaseMode;
import com.sunzhibin.newmusic.base.view.IBaseView;

/**
 * @author: sunzhibin
 * @date: 2018/1/2.
 * @description:
 * @e-mail:
 */

public interface IRequestView extends IBaseView {
    void requestLoading();

    void resultSuccess(BaseMode result);

    void resultFailure(String result);
}
