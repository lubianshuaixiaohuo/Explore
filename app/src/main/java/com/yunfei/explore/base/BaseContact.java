package com.yunfei.explore.base;

import android.content.Context;

/**
 * author：zhouyunfei
 * date：2017/7/14 15:07
 * describe：IBaseContact基础接口
 */

public interface BaseContact {
    /**
     * V视图层
     */
    interface IBaseView {
        void hideProgress();

        void showProgress();

        Context getCurContext();
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IBasePresenter<T extends IBaseView> {
        void attachView(T view);

        void detachView();

        void hideProgress();

        void showProgress();
    }

    /**
     * 逻辑处理层
     */
    interface IBaseModle {

    }
}
