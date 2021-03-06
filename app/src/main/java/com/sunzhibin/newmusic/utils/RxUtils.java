package com.sunzhibin.newmusic.utils;

import com.sunzhibin.newmusic.base.mode.BaseBean;
import com.sunzhibin.newmusic.common.exception.ApiException;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: sunzhibin
 * @date: 2018/1/3.
 * @description:
 * @e-mail:
 */

public class RxUtils {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseBean<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<BaseBean<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<BaseBean<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<BaseBean<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseBean<T> baseBean) {
                        if (baseBean.getError() == 0) {
                            return createData(baseBean.getResults());
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    private static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
