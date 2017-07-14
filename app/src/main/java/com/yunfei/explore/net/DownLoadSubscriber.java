package com.yunfei.explore.net;

import java.io.File;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * author：zhouyunfei
 * date：2017/7/12 14:43
 * describe：
 */

public class DownLoadSubscriber extends Subscriber<ResponseBody> {
    private DownLoadCallBack callBack;
    private String filePath;
    private String fileName;
    private File mFile;

    public DownLoadSubscriber(DownLoadCallBack callBack, String path, String name) {
        this.callBack = callBack;
        this.filePath = path;
        this.fileName = name;
    }

    public DownLoadSubscriber(DownLoadCallBack callBack, File file) {
        this.callBack = callBack;
        this.mFile = file;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (callBack != null) {
            callBack.onStart();
        }
    }

    @Override
    public void onCompleted() {
        if (callBack != null) {
            callBack.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (callBack != null) {
            callBack.onError(e);
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        if (null != mFile) {
            new DownLoadHelper(callBack).writeResponseBodyToDisk(mFile, responseBody);
        } else {
            new DownLoadHelper(callBack).writeResponseBodyToDisk(filePath, fileName, responseBody);
        }

    }
}

