package com.yunfei.explore.net;

import java.io.File;

/**
 * author：zhouyunfei
 * date:2017/7/12 15:20
 * describe：下载回调
 */
public abstract class DownLoadCallBack {
    public void onStart(){}

    public void onCompleted(){}

    abstract public void onError(Throwable e);

    public void onProgress(long fileSizeDownloaded){}

    abstract public void onSucess(File file, long fileSize);
}
