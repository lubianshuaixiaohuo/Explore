package com.yunfei.explore.base;

import android.content.Context;
import android.widget.Toast;

import com.yunfei.explore.net.BaseSubscriber;
import com.yunfei.explore.net.ExceptionHandle;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * author：zhouyunfei
 * date:2017/7/10 11:07
 * describe：presenter基类
 */
public abstract class BasePresenter<T extends BaseContact.IBaseView> implements BaseContact.IBasePresenter<T> {
    protected Reference<T> mViewRef;//使用弱引用避免内存泄露
    protected Context appContext;//为避免内存泄露使用applicationcontext，使用activity的context应在activity中实现


    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return null != mViewRef && null != mViewRef.get();
    }

    @Override
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);//建立关联
        appContext = getView().getCurContext().getApplicationContext();
    }


    @Override
    public void detachView() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


    /***
     * 展示对话框
     */
    @Override
    public void showProgress() {
        if (isViewAttached()) {
            getView().showProgress();
        }
    }

    /***
     * 关闭对话框
     */
    @Override
    public void hideProgress() {
        if (isViewAttached()) {
            getView().hideProgress();
        }
    }


    abstract class HttpSubscriber<T> extends BaseSubscriber<T> {

        public HttpSubscriber(Context context) {
            super(context);
        }

        @Override
        public void onStart() {
            super.onStart();
            showProgress();
        }

        @Override
        public void onCompleted() {
            hideProgress();
        }

        public void onError(ExceptionHandle.ResponeThrowable e) {
            hideProgress();
            if (null != context) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
