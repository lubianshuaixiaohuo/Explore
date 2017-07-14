package com.yunfei.explore.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * author：zhouyunfei
 * date:2017/7/10 11:06
 * describe：mvp activity 基类
 */
public abstract class MVPBaseActivity<V extends BaseContact.IBaseView, T extends BaseContact.IBasePresenter<V>> extends BaseAcitvity implements BaseContact
        .IBaseView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }

    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    public Context getCurContext() {
        return super.getCurContext();
    }

    protected abstract T createPresenter();
}
