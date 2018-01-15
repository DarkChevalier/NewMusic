package com.sunzhibin.newmusic.base.mode;

/**
 * @author: sunzhibin
 * @date: 2018/1/2.
 * @description:
 * @e-mail:
 */

public class BaseBean<T> {
    private int error;
    private T results;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}

