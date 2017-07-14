package com.yunfei.explore.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yunfei.explore.utils.ToastUtils;
import com.yunfei.explore.utils.ZTLUtils;

/**
 * author：zhouyunfei
 * date:2017/7/10 11:07
 * describe：activity基类
 */

public abstract class BaseAcitvity extends Activity {
    protected Context mContext;//上下文
    protected Dialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getCurContext();
        ActManager.getInstance().addActivity(this);
        new ZTLUtils(BaseAcitvity.this).setTranslucentStatus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActManager.getInstance().finishActivity(this);
    }

    /**
     * 展示等待对话框
     */
    protected void showProgress() {
        if (null != mProgressDialog) {
            mProgressDialog.show();
        }
    }

    /***
     * 隐藏等待对话框
     */
    protected void hideProgress() {
        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
        }
    }


    /**
     * 获取当前activity上下文
     *
     * @return
     */
    public Context getCurContext() {
        return this;
    }


    /***
     * 展示toast
     * @param content
     */
    protected void showToast(String content) {
        ToastUtils.showToast(this, content);
    }

}
