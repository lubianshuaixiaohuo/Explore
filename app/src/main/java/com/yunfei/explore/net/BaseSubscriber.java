package com.yunfei.explore.net;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunfei.explore.bean.BaseResult;
import com.yunfei.explore.net.ExceptionHandle;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * author：zhouyunfei
 * date:2017/7/11 10:13
 * describe：HttpSubscriber
 */
public abstract class BaseSubscriber<T> extends Subscriber<ResponseBody> {

    protected Context context;

    public BaseSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        String json = null;
        try {
            json = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseResult<T> baseResult = new Gson().fromJson(json, new TypeToken<BaseResult<T>>() {
        }.getType());
        onSuccess(baseResult, baseResult.getData());
    }

    public abstract void onSuccess(BaseResult<T> baseResult, T object);

    public abstract void onError(ExceptionHandle.ResponeThrowable e);
}
